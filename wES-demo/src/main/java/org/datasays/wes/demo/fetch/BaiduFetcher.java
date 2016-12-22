package org.datasays.wes.demo.fetch;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import org.datasays.util.JsonObjGetter;
import org.datasays.util.WCfg;
import org.datasays.util.WPage;
import org.datasays.util.WPageIterator;
import org.datasays.wes.EsBaseService;
import org.datasays.wes.core.HttpException;
import org.datasays.wes.core.JsonObj;
import org.datasays.wes.core.RequestInfo;
import org.datasays.wes.core.WHttpClient;
import org.datasays.wes.toolkit.WGsonConvert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by watano on 2016/12/5.
 */
public class BaiduFetcher {
	private static Logger LOG = LoggerFactory.getLogger(BaiduFetcher.class);
	private final int FetchInterval = 1;//间隔时间,单位s
	public final String index = "fetchdata";
	public final String type = "BaiduMap";
	private WHttpClient baiduClient;
	private EsBaseService esHelper;
	public String appKey;
	public String appSecret;

	public BaiduFetcher(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
	}

	public void init() {
		OkHttpClient.Builder cBuilder = new OkHttpClient.Builder();
		cBuilder.cache(new Cache(new File("./build/baidu"), 10000));
		baiduClient = new WHttpClient(cBuilder.build(), new WGsonConvert());
		baiduClient.setLogFlag(false, false, true);
		esHelper = new EsBaseService();
		esHelper.init(WCfg.getValue("ES.server"), null, null);
	}

	private RequestInfo create() {
		RequestInfo action = new RequestInfo("http://api.map.baidu.com/place/v2/");
		action.addParams("ak", appKey);
		return action;
	}

	public JsonObjGetter searchPOIByRegion(WPage page, String query, String region) throws HttpException {
		RequestInfo action = create();
		action.setUrl("search");
		action.addParams("scope", 2);
		action.addParams("output", "json");
		action.addParams("query", query);
		action.addParams("region", region);
		action.addParams("page_size", page.getSize());
		if (page.getPageNo() > 0) {
			action.addParams("page_num", page.getPageNo()-1);
		}
		LOG.info(page.toText());
		JsonObjGetter result = new JsonObjGetter(baiduClient.get(action, Object.class));
		if ((result.num("status") != null && result.num("status").intValue() == 0) || "ok".equalsIgnoreCase(result.str("message"))) {
			if (result.num("total") != null && result.num("total").intValue() > 0) {
				page.setTotal(result.num("total").intValue());
			}
		}
		return result;
	}

	public void fetchALLPOIByRegion(String query, String region){
		WPage page = new WPage();
		WPageIterator<Object> pageIterator = new WPageIterator<Object>(page) {
			@Override
			public void doSearch() {
				try {
					JsonObjGetter result = searchPOIByRegion(getPage(), query, region);
					if ((result.num("status") != null && result.num("status").intValue() == 0) || "ok".equalsIgnoreCase(result.str("message"))) {
						int total = 0;
						if (result.num("total") != null && result.num("total").intValue() > 0) {
							total = result.num("total").intValue();
						}
						List<Object> items = new ArrayList<>();
						if (result.list("results") != null) {
							items.addAll(result.list("results"));
						}
						update(items, total);
						Thread.sleep(FetchInterval*1000);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		while (pageIterator.hasNext()) {
			Object item = pageIterator.next();
			save(item);
		}
	}

	public void save(Object item) {
		try {
			esHelper.index(index, type, null, item);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void main(String[] args) {
		JsonObj allIndustry = new JsonObj();
		allIndustry.lstAdd("美食", "中餐厅", "外国餐厅", "小吃快餐店", "蛋糕甜品店", "咖啡厅", "茶座", "酒吧");
		allIndustry.lstAdd("酒店", "星级酒店", "快捷酒店", "公寓式酒店");
		allIndustry.lstAdd("购物", "购物中心", "超市", "便利店", "家居建材", "家电数码", "商铺", "集市");
		allIndustry.lstAdd("生活服务", "通讯营业厅", "邮局", "物流公司", "售票处", "洗衣店", "图文快印店", "照相馆", "房产中介机构", "公用事业", "维修点", "家政服务", "殡葬服务", "彩票销售点", "宠物服务", "报刊亭", "公共厕所");
		allIndustry.lstAdd("丽人", "美容", "美发", "美甲", "美体");
		allIndustry.lstAdd("旅游景点", "公园", "动物园", "植物园", "游乐园", "博物馆", "水族馆", "海滨浴场", "文物古迹", "教堂", "风景区");
		allIndustry.lstAdd("休闲娱乐", "度假村", "农家院", "电影院", "KTV", "剧院", "歌舞厅", "网吧", "游戏场所", "洗浴按摩", "休闲广场");
		allIndustry.lstAdd("运动健身", "体育场馆", "极限运动场所", "健身中心");
		allIndustry.lstAdd("教育培训", "高等院校", "中学", "小学", "幼儿园", "成人教育", "亲子教育", "特殊教育学校", "留学中介机构", "科研机构", "培训机构", "图书馆", "科技馆");
		allIndustry.lstAdd("文化传媒", "新闻出版", "广播电视", "艺术团体", "美术馆", "展览馆", "文化宫");
		allIndustry.lstAdd("医疗", "综合医院", "专科医院", "诊所", "药店", "体检机构", "疗养院", "急救中心", "疾控中心");
		allIndustry.lstAdd("汽车服务", "汽车销售", "汽车维修", "汽车美容", "汽车配件", "汽车租赁", "汽车检测场");
		allIndustry.lstAdd("交通设施", "飞机场", "火车站", "地铁站", "长途汽车站", "公交车站", "港口", "停车场", "加油加气站", "服务区", "收费站", "桥");
		allIndustry.lstAdd("金融", "银行", "ATM", "信用社", "投资理财", "典当行");
		allIndustry.lstAdd("房地产", "写字楼", "住宅区", "宿舍");
		allIndustry.lstAdd("公司企业", "公司", "园区", "农林园艺", "厂矿");
		allIndustry.lstAdd("政府机构", "中央机构", "各级政府", "行政单位", "公检法机构", "涉外机构", "党派团体", "福利机构", "政治教育机构");

		BaiduFetcher fetcher = new BaiduFetcher(args[0], args[1]);
		fetcher.init();
		String region = "玉溪";
		for(String mainIndustry: new String[]{"美食", "酒店", "购物", "丽人", "休闲娱乐"}){
			for(Object industry: (Object[]) allIndustry.get(mainIndustry)){
				fetcher.fetchALLPOIByRegion(industry.toString(), region);
			}
		}
	}
}
