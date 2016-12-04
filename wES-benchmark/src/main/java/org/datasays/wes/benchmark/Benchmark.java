package org.datasays.wes.benchmark;

import org.datasays.util.WPage;
import org.datasays.wes.benchmark.impl.JestClient;
import org.datasays.wes.benchmark.impl.WESClient;
import org.datasays.wes.benchmark.vo.TestDoc;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.vo.IEsItem;
import org.datasays.wes.vo.Query;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WSearchResult;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by watano on 2016/11/28.
 */
public class Benchmark {
	private static Logger LOG = LoggerFactory.getLogger(Benchmark.class);
	private IESClient client = null;
	private String group = null;
	private List<BenchmarkData> benchmarkData = new ArrayList<>();

	public Benchmark() {
	}

	public void init(IESClient client) throws Exception {
		this.client = client;
		this.client.init();
	}

	public void close() {
		try {
			if (client != null) {
				client.close();
				client = null;
			}
			benchmarkData.clear();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public void addBenchmarkNode(String task) {
		BenchmarkData bd = new BenchmarkData(group, task);
		if (benchmarkData.size() > 0) {
			BenchmarkData parentBd = benchmarkData.get(benchmarkData.size() - 1);
			LOG.info(bd.toText(parentBd));
		}
		benchmarkData.add(bd);
	}

	//新建一个Index,插入大量同一Type的数据,Search所有数据并完整遍历数据, 根据查询条件Search并遍历部分数据, Get某些指定数据, Update某些指定数据, 根据条件DeleteByQuery部分数据, Delete一个Type的数据
	public void testCase1(String index, String type) {
		try {
			setGroup("testCase1@" + client.getClass().getSimpleName());
			addBenchmarkNode("start");
			client.delIndex(index);

			addBenchmarkNode("delIndex");

			//load test data
			List<TestDoc> allData = new ArrayList<>();
			List<String> allDocId = new ArrayList<>();
			int[] idIndexs = new int[]{0, 5, 7, 9, 10, 49, 50, 51, 99, 100, 101, 500, 501, 589, 599, 600, 601,
					798, 799, 800, 801, 802, 805, 809, 810, 890, 891, 895, 899,
					900, 901, 905, 909, 910, 911, 980, 981, 985, 988, 989, 990, 991, 995, 998, 999};
			for (int i = 0; i < 1000; i++) {
				TestDoc tdoc = new TestDoc(index, type);
				tdoc.tint = i;
				tdoc.tdouble = i * 0.2;
				tdoc.tfloat = i * 0.2f;
				tdoc.tbool = i % 2 == 0;
				tdoc.tdate = new Date();
				tdoc.tString = "abc中文测试乒乓球" + i * 0.2;
				tdoc.obj = new TestDoc(index, type);
				tdoc.obj.tint = i;
				tdoc.obj.tString = "父类" + i;
				allData.add(tdoc);
			}
			addBenchmarkNode("load test data");
			//create index
			client.createIndex(index);
			addBenchmarkNode("createIndex");

			//index all doc
			int i = 0;
			for (TestDoc d : allData) {
				IEsItem newDoc = client.saveIndex(d);
				if (Arrays.binarySearch(idIndexs, i) >= 0) {
					allDocId.add(newDoc.getId());
				}
			}
			addBenchmarkNode("index all doc");

			//search all doc
			WPage page = new WPage();
			SearchQuery searchQuery = new SearchQuery(page);
			WSearchResult<TestDoc> result = null;
			i = 0;
			do {
				if (i % 20 == 0) {
					page.setFrom(i / 20);
				}
				result = client.searchObj(index, type, searchQuery, TestDoc.class);
				for (TestDoc d : result.getData()) {
					int x = d.tint;
					Assert.assertEquals(x * 0.2, d.tdouble, 2);
					Assert.assertEquals(x * 0.2f, d.tfloat, 2);
					Assert.assertEquals("abc中文测试乒乓球" + x * 0.2, d.tString);
					Assert.assertNotNull(d.obj);
					Assert.assertEquals(x, d.obj.tint);
					Assert.assertEquals(0, d.obj.tdouble, 2);
					Assert.assertEquals("父类" + x, d.obj.tString);
				}
			} while (result.getTotal() > i++);
			addBenchmarkNode("search all doc");

			//search doc by query
			page = new WPage();
			searchQuery = new SearchQuery(page);
			searchQuery.setQuery(Query.bool(Query.term("tbool", "true"), null, null));
			i = 0;
			do {
				if (i % 20 == 0) {
					page.setFrom(i / 20);
				}
				result = client.searchObj(index, type, searchQuery, TestDoc.class);
				Assert.assertEquals(500, result.getTotal().intValue());
				for (TestDoc d : result.getData()) {
					Assert.assertTrue(d.tbool);
				}
			} while (result.getTotal() > i++);
			addBenchmarkNode("search doc by query");

			//get doc
			List<TestDoc> updateDocs = new ArrayList<>();
			for (String id : allDocId) {
				TestDoc doc = new TestDoc(index, type, id);
				IEsItem newDoc = client.get(doc);
				Assert.assertNotNull(newDoc);
				Assert.assertTrue(newDoc instanceof TestDoc);
				updateDocs.add((TestDoc) newDoc);
			}
			addBenchmarkNode("get doc");

			//update doc
			for (TestDoc d : updateDocs) {
				i = (int) System.currentTimeMillis() % 100;
				d.tint = i;
				d.tdouble = i * 0.3;
				d.tfloat = i * 0.3f;
				d.tbool = i % 3 == 0;
				d.tString = "abc中文测试乒乓球" + i * 0.3;
				IEsItem newDoc = client.saveIndex(d);
				Assert.assertNotNull(newDoc);
				Assert.assertTrue(newDoc instanceof TestDoc);
			}
			addBenchmarkNode("update doc");

			//delete doc by query
			try {
				Query query = Query.bool(Query.term("tbool", "false"), null, null);
				boolean isDeleted = client.deleteByQuery(index, type, query);
			} catch (HttpException ex) {
				LOG.error(ex.toText());
				LOG.error(ex.getMessage(), ex);
			}
			addBenchmarkNode("delete doc by query");

			//delete all type doc
			client.delIndex(index);
			addBenchmarkNode("delIndex");
		} catch (HttpException ex) {
			LOG.error(ex.toText());
			LOG.error(ex.getMessage(), ex);
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
		}
	}

	public static void main(String[] args) {
		Benchmark benchmark = new Benchmark();
		try {
			benchmark.init(new WESClient());
			benchmark.testCase1("wesbenchmark", "testCase1");
			benchmark.close();

			benchmark.init(new JestClient());
			benchmark.testCase1("wesbenchmark", "testCase1");
			benchmark.close();
		} catch (Throwable ex) {
			LOG.error(ex.getMessage(), ex);
		} finally {
			benchmark.close();
		}
	}
}
