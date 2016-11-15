package org.datasays

import java.io.File
import java.util.HashSet
import java.util.Iterator
import java.util.Set
import jodd.io.FileUtil
import org.datasays.util.FindFileUtil
import org.datasays.util.JsonObjGetter
import org.datasays.util.WJsonUtils
import org.datasays.es2.EsHelper2
import org.datasays.es2.SearchQuery2
import org.datasays.es2.WSearchResultHit2
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class EsDataMaintain2 {
	static final Logger LOG = LoggerFactory.getLogger(EsDataMaintain2);
	val allIndexs = #['watano'];
	val BackupDir = '''e:\work\projects\myprojects\webfetch3\down\es\''';
	var static EsHelper2 helper = null;
	private static var EsDataMaintain2 _instance = null;

	@Before
	def void setUp() throws Exception {
		helper = EsHelper2.getInstance();
	}

	@After
	def void tearDown() throws Exception {
	}

	public static def EsDataMaintain2 getInstance() {
		if(_instance == null) {
			_instance = new EsDataMaintain2();
			_instance.init();
		}
		helper = EsHelper2.getInstance();
		return _instance;
	}

	def void init() {
	}

	/**
	 * 删除所有es数据
	 */
	@Test
	def void rmAllData() {
		for (String index : allIndexs) {
			if(helper.hasIndex(index)) {
				helper.delIndex(index);
			}
		}
	}

//	/**
//	 * 删除指定索引下指定类型的es数据
//	 */
//		static final  String spe_index = "zhonglinex";
//		static final  String spe_type = "BusinessLog_TradeTrends";
//	@Test
//	def void rmSigData() {
//			try {
//				var EsSearchResultIterator result = new EsSearchResultIterator(new EsSearchBuilder()) {
//					override WSearchResult search() {
//						return helper.search2(spe_index, spe_type, (pager as EsSearchBuilder).build);
//					}
//				};
//				var i = 0;
//				while(result.hasNext) {
//					var WSearchResultHit hit = result.next;
//					helper.del(spe_index, spe_type, hit.id);
//					i++;
//					LOG.info("删除数据：序号："+ i +" 索引："+spe_index+" 类型 ："+spe_type+" id:"+hit.id);
//				}
//			} catch(Exception e) {
//				LOG.error(e.message, e);
//			}
//	}
//
	@Test
	def void backupEs() {
		for (String index : allIndexs) {
			// 是否删除之前的备份数据
			if(FileUtil.isExistingFolder(new File(BackupDir + index + '/'))) {
				FileUtil.deleteDir(BackupDir + index + '/');
			}
			FileUtil.mkdirs(BackupDir + index + '/');

			var mappings = new JsonObjGetter(helper.getMapping(index));
			var Set<String> types = new HashSet<String>();
			for (Object o : mappings.obj(index).map('mappings').keySet) {
				val type = o as String;
				types.add(type);
				// 备份type的mapping
				var mapping = mappings.obj(index).obj('mappings').map(type);
				WJsonUtils.writeJson(BackupDir + index + '/' + type + '.json', mapping);

				// 备份type数据
				var squery = new SearchQuery2();
				var result = helper.search2(index, type, squery);
				while(result.hasNext) {
					var WSearchResultHit2 hit = result.next;
					FileUtil.mkdirs(BackupDir + index + '/' + type + '/');
					WJsonUtils.writeJson(BackupDir + index + '/' + type + '/' + hit.id + '.json', hit.source.map);
				}
			}
		}
	}

	def void reCreateIndex(String index) {
		// 重建index,并设置mappings
		if(helper.hasIndex(index)) {
			helper.delIndex(index);
		}
		helper.createIndex(index);
		var Iterator<File> iterator = FindFileUtil.search(false, false, BackupDir + index + '/');
		while(iterator.hasNext) {
			var f = iterator.next;
			if(f.name.endsWith('.json')) {
				var type = f.name.trim;
				type = type.substring(0, type.length - '.json'.length);
				helper.putMapping(index, type, WJsonUtils.fromJson(f, Object));
			}
		}
		helper.syncedFlushIndex(index);
	}

	@Test
	def void recoveryEs() {
		for (String index : allIndexs) {
			for (String type : helper.getIndexTypes(index)) {
				var Iterator<File> iterator = FindFileUtil.search(true, false, BackupDir + index + '/' + type + '/');
				while(iterator.hasNext) {
					var f = iterator.next;
					if(f.name.endsWith('.json')) {
						var id = f.name.substring(0, f.name.length - 5);
						try {
							helper.index(index, type, id, WJsonUtils.fromJson(f, Object));
						} catch(Throwable e) {
							LOG.error(e.message, e);
						}
					}
				}
			}
			helper.syncedFlushIndex(index);
		}
	}

	/**
	 * 重建es数据
	 */
	@Test
	def void rebuildData() {
		try {
			// println('备份数据');
			// backupEs();
			println('删除数据');
			rmAllData();
			for (String index : allIndexs) {
				reCreateIndex(index);
			}
			recoveryEs();
		} catch(Exception e) {
			LOG.error(e.message, e);
		}
	}
}
