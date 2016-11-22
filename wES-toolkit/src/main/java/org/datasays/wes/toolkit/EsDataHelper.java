package org.datasays.wes.toolkit;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.datasays.wes.EsHelper2;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.util.FindFileUtil;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WJsonUtils;
import org.datasays.util.WPageIterator;
import org.datasays.util.collection.StrObjMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jodd.io.FileUtil;

public class EsDataHelper extends EsHelper2 {
	private static final Logger LOG = LoggerFactory.getLogger(EsDataHelper.class);

	/**
	 * 删除所有es数据
	 * @param index
	 */
	public boolean rmAllData(String index) {
		try {
			if (hasIndex(index)) {
				delIndex(index);
			}
			return true;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * 删除所有es数据
	 * @param index
	 * @param type
	 */
	public boolean rmAllData(String index, String type) {
		JsonObjGetter result = exec(esService.delete_by_query(index, type, SearchQuery.MatchAll()));
		if (result != null) {

		}
		return true;
	}

	/**
	 * 备份es数据到backupDir目录
	 * @param index
	 * @param backupDir
	 */
	public void backupData(String index, String backupDir) {
		try {
			Set<String> types = getIndexTypes(index);
			for (String type : types) {
				backupData(index, type, backupDir);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 备份es数据到backupDir目录
	 * @param index
	 * @param type
	 * @param backupDir
	 */
	@SuppressWarnings("unchecked")
	public void backupData(String index, String type, String backupDir) {
		try {
			String path = path(backupDir, index, type);
			FileUtil.mkdirs(path);
			WPageIterator<Object> result = search(index, type, SearchQuery.MatchAll(), Object.class);
			while (result.hasNext()) {
				Object vo = result.next();
				WJsonUtils.writeJson(path + ((Map<Object, Object>) vo).get("id") + ".json", vo);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 从backupDir目录恢复所有es数据
	 * @param index
	 * @param type
	 * @param backupDir
	 */
	public void recoveryData(String index, String type, String backupDir) {
		String path = path(backupDir, index, type);
		Iterator<File> iterator = FindFileUtil.search(false, false, path);
		//		List<Object> docs = new ArrayList<>();
		while (iterator.hasNext()) {
			try {
				Object vo = WJsonUtils.fromJson(iterator.next(), Object.class);
				//				docs.add(vo);
				index(index, type, vo);
				//				if (docs.size() >= 20) {
				//					bulkUpdate(index, type, docs, 3);
				//					docs.clear();
				//				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
		//		if (docs.size() > 0) {
		//			bulkUpdate(index, type, docs, 3);
		//		}
	}

	/**
	 * 重建Index
	 * @param index
	 */
	public void reCreateIndex(String index, String type, String backupDir) {
		try {
			String path = path(backupDir, index, type + ".json");
			String json = FileUtil.readString(path, "utf-8");
			if (json != null && json.trim().length() > 0) {
				rmAllData(index, type);
				putMapping(index, type, WJsonUtils.fromJson(json, Object.class));
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 备份Index的mapping
	 * @param index
	 * @param type
	 * @param backupDir
	 */
	public void backupIndexMapping(String index, String type, String backupDir) {
		try {
			JsonObjGetter json = new JsonObjGetter(getMapping(index, type));
			WJsonUtils.writeJson(path(backupDir, index, type + ".json"), json.obj(index).obj("mappings").obj(type).map());
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	private static String path(String... names) {
		String fullPath = "";
		for (String name : names) {
			if (name.startsWith(File.separator)) {
				name = name.substring(1);
			}
			if (name.endsWith(File.separator)) {
				fullPath += name;
			} else {
				fullPath += name + File.separator;
			}
		}
		return fullPath;
	}

	/**
	 * 备份es数据和mappings
	 * @param backupDir
	 * @param allIndex
	 */
	public void backupAll(String backupDir, String... allIndex) {
		for (String index : allIndex) {
			try {
				backupData(index, backupDir);
				Set<String> types = getIndexTypes(index);
				backupAllMapping(backupDir, index, types.toArray(new String[]{}));
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}
	
	public void backupAllMapping(String backupDir, String index, String... allTypes){
		for (String type : allTypes) {
			backupIndexMapping(index, type, backupDir);
		}
	}
	
	public void recoveryAllMapping(String backupDir, String index, String... allTypes) throws Exception{
		for (String type : allTypes) {
			putMapping(index, type, WJsonUtils.fromJson(new File(path(backupDir, index, type + ".json")), Object.class));
		}
	}

	/**
	 * 恢复es数据和mappings
	 * @param backupDir
	 * @param allIndex
	 */
	public void recoveryAll(String backupDir, String... allIndex) {
		for (String index : allIndex) {
			try {
				String path = path(backupDir, index);
				Iterator<File> iterator = FindFileUtil.search(false, false, path);
				Set<String> allType = new HashSet<>();
				while (iterator.hasNext()) {
					File f = iterator.next();
					if (f.getName().endsWith(".json")) {
						String type = f.getName().substring(0, f.getName().length() - 5);
						putMapping(index, type, WJsonUtils.fromJson(f, Object.class));
						allType.add(type);
					}
				}
				for (String type : allType) {
					Iterator<File> iterator2 = FindFileUtil.search(false, false, path(backupDir, index, type));
					while (iterator2.hasNext()) {
						File f = iterator2.next();
						if (f.getName().endsWith(".json")) {
							Object vo = WJsonUtils.fromJson(f, Object.class);
							index(index, type, vo);
						}
					}
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * 根据mappingFile中的json mapping重新建索引
	 * @param index
	 * @param mappingFile
	 * @param version
	 */
	public void resetMapping(String index, String type, String mappingFile, int version, String backupDir) {
		try {
			if (hasIndexAlias(index) || hasIndex(index)) {//检查目标index是否存在
				String oldIndex = index + (version - 1);
				Set<String> types = getIndexTypes(oldIndex);
				//备份所有type的mapping
				for (String type1 : types) {
					backupIndexMapping(oldIndex, type1, backupDir);
				}
				String newIndex = index + version;
				if (!hasIndex(newIndex) && !hasIndexAlias(newIndex)) {
					//新建newIndex
					createIndex(newIndex, 3, 3);
					if (type != null && mappingFile != null) {
						//重新设置mapping
						putMapping(newIndex, type, WJsonUtils.fromJson(new File(mappingFile), Object.class));
					}
					//恢复其他type的mapping
					for (String type1 : types) {
						if (type == null || !type.equalsIgnoreCase(type1)) {
							putMapping(newIndex, type1, WJsonUtils.fromJson(new File(path(backupDir, index, type1 + ".json")), Object.class));
						}
					}
					//把index的数据reindex到newIndex上
					reIndex(oldIndex, newIndex, true);
					//删除index
					if (hasIndex(index)) {
						rmAllData(oldIndex);
					} else if (hasIndexAlias(index)) {
						exec(esService.indices_delete_alias(oldIndex, index));
					}
					//为newIndex创建一个index的别名
					exec(esService.indices_put_alias(newIndex, index, new StrObjMap()));
				} else {
					LOG.error("目标index已经存在,请重新设置version");
				}
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	/**
	 * 使用reindex备份数据,并重新设置mapping
	 * @param index
	 * @param backupIndex
	 * @param backupDir
	 */
	public void resetMapping2(String index, String backupIndex, String backupDir) {
		try {
			if (hasIndex(index)) {//检查目标index是否存在
				Set<String> types = getIndexTypes(index);
				//把index的数据reindex到backupIndex上备份
				reIndex(index, backupIndex, true);
				//删除index
				delIndex(index);
				//新建newIndex
				createIndex(index, 3, 3);
				//恢复其他type的mapping
				recoveryAllMapping(backupDir, index, types.toArray(new String[]{}));
				//使用reindex把backupIndex的数据恢复到index
				reIndex(backupIndex, index, true);
				//删除backupIndex
				delIndex(backupIndex);
			} else {
				LOG.error("目标index已经存在");
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		String backupDir = ".\\build\\tmp\\es";
		EsDataHelper esDataHelper = new EsDataHelper();
		String[] allIndex = new String[] { "zhonglinex" };
		//备份所有数据
		esDataHelper.backupAll(backupDir, allIndex);

		//恢复所有数据
		//esDataHelper.recoveryAll(backupDir, allIndex);

		//重建mapping
		for (String index : allIndex) {
			//esDataHelper.resetMapping(index, null, null, 2, backupDir);
			esDataHelper.resetMapping2(index, index+"_tmp", backupDir);
		}
	}
}
