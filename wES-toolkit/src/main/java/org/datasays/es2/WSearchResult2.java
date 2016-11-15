package org.datasays.es2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.datasays.util.JsonObjGetter;
import org.datasays.util.WPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSearchResult2 extends WPage{
	private static final Logger	LOG	= LoggerFactory.getLogger(WSearchResult2.class);
	private JsonObjGetter jsonObj;

	public WSearchResult2() {
		super();
	}

	public WSearchResult2(Object obj, WPage query) {
		super();
		try {
			jsonObj = new JsonObjGetter(obj);
			setPage(query);
			super.setTotal(jsonObj.obj("hits").num("total").intValue());
		} catch (Exception e) {
		}
	}

	protected List<WSearchResultHit2> getHits(boolean returnSingle) {
		List<WSearchResultHit2> sourceList = new ArrayList<WSearchResultHit2>();
		if (jsonObj != null) {
			for (Object hitObj : jsonObj.obj("hits").list("hits")) {
				try {
					sourceList.add(extractHit(hitObj, null));
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
				if (returnSingle)
					break;
			}
			
		}
		return sourceList;
	}

	protected WSearchResultHit2 extractHit(Object hitElement, String sourceKey) throws Exception {
		WSearchResultHit2 hit = null;
		JsonObjGetter hitObject = new JsonObjGetter(hitElement);
		if (hitElement != null) {
			String id = hitObject.str("_id");
			String index = hitObject.str("_index");
			String type = hitObject.str("_type");
			Double score = hitObject.num("_score").doubleValue();
			if (hitObject.obj("_source") != null) {
				Map<String, List<String>> highlight = extractHighlight(hitObject.obj("highlight"));
				List<String> sort = extractSort(hitObject.obj("sort"));

				//if (id != null) source.add(ES_METADATA_ID, id);
				hit = new WSearchResultHit2(hitObject.obj("_source"), hitObject.obj("_explanation"), highlight, sort, id, index, type, score);
			}
		}

		return hit;
	}

	protected List<String> extractSort(JsonObjGetter sort) throws Exception {
		if (sort == null) {
			return null;
		}

		List<String> retval = new ArrayList<String>(sort.list().size());
		//for (Object sortValue : sort.list()) {
		//retval.add(sortValue.isJsonNull() ? "" : sortValue.getAsString());
		//}
		return retval;
	}

	protected Map<String, List<String>> extractHighlight(JsonObjGetter highlight) throws Exception {
		Map<String, List<String>> retval = null;

		//        if (highlight != null) {
		//            Set<Map.Entry<String, JsonElement>> highlightSet = highlight.entrySet();
		//            retval = new HashMap<String, List<String>>(highlightSet.size());
		//
		//            for (Map.Entry<String, JsonElement> entry : highlightSet) {
		//                List<String> fragments = new ArrayList<String>();
		//                for (JsonElement element : entry.getValue().getAsJsonArray()) {
		//                    fragments.add(element.getAsString());
		//                }
		//                retval.put(entry.getKey(), fragments);
		//            }
		//        }

		return retval;
	}

	public Float getMaxScore() {
		try {
			Float maxScore = jsonObj.obj("hits").num("max_score").floatValue();
			return maxScore;
		} catch (Exception e) {
			return 0f;
		}
	}

	//    public MetricAggregation getAggregations() {
	//        final String rootAggrgationName = "aggs";
	////        if (jsonObject == null) return new RootAggregation(rootAggrgationName, new JsonObject());
	////        if (jsonObject.has("aggregations"))
	////            return new RootAggregation(rootAggrgationName, jsonObject.getAsJsonObject("aggregations"));
	////        if (jsonObject.has("aggs")) return new RootAggregation(rootAggrgationName, jsonObject.getAsJsonObject("aggs"));
	//
	//        return new RootAggregation(rootAggrgationName, new JsonObject());
	//    }
}
