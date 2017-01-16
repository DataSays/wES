package org.datasays.wes.demo.es.actions;

import org.datasays.util.WPage;
import org.datasays.wes.core.JsonObj;
import org.datasays.wes.demo.es.actions.es.model.SearchQueryJS;
import org.datasays.wes.demo.es.service.EsService;
import org.datasays.wes.demo.model.ActionResult;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WSearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by watano on 2016/12/22.
 */
@RestController
public class EsController {
	private static Logger LOG = LoggerFactory.getLogger(EsController.class);
	@Autowired
	private EsService esService;

	@RequestMapping("/es/esData/{index}/{type}/{id}")
	public ActionResult esData(@PathVariable("index") String index, @PathVariable("type") String type, @PathVariable("id") String id) {
		ActionResult result = new ActionResult();
		try {
			Object doc = esService.get(index, type, id);
			result.setData(doc);
			result.ok(doc);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.error(e);
		}
		return result;
	}

	@RequestMapping(value = "/es/esData/{index}/{type}/{id}", method = RequestMethod.DELETE)
	public ActionResult delEsData(@PathVariable("index") String index, @PathVariable("type") String type, @PathVariable("id") String id) {
		ActionResult result = new ActionResult();
		try {
			Object doc = esService.delete(index, type, id);
			result.ok(doc);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.error(e);
		}
		return result;
	}

	@RequestMapping("/es/saveEsData/{index}/{type}/{id}")
	public ActionResult saveEsData(@PathVariable("index") String index, @PathVariable("type") String type, @PathVariable(value = "id", required = false) String id, @RequestBody Object doc) {
		ActionResult result = new ActionResult();
		try {
			Object newdoc = esService.index(index, type, id, doc);
			result.ok(newdoc);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.error(e);
		}
		return result;
	}

	@RequestMapping("/es/allSchemeData")
	public ActionResult allSchemeData() {
		ActionResult result = new ActionResult();
		try {
			JsonObj data = new JsonObj();
			Set<String> indices = esService.getAllIndex();
			if (indices != null) {
				for (String index : indices) {
					Set<String> types = esService.getIndexTypes(index);
					if (types != null) {
						for (String type : types) {
							data.lstAdd(index, type);
						}
					} else {
						data.lstAdd(index);
					}
				}
			}
			result.ok(data);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.error(e);
		}
		return result;
	}

	@RequestMapping("/es/searchEsData")
	public ActionResult searchEsData(@RequestBody SearchQueryJS queryJS) {
		ActionResult result = new ActionResult();
		try {
			WPage page = queryJS.getPage();
			String index = null;
			String type = null;
			JsonObj query = queryJS.getQuery();
			if (query != null && query.get("currIndexId") != null) {
				index = query.get("currIndexId").toString();
				if (query.get("currTypeId") != null) {
					type = query.get("currTypeId").toString();
				}
			}
			SearchQuery squery = null;
			if (query != null && query.get("queryText") != null && query.get("queryText").toString().trim().length() > 0) {
				squery = SearchQuery.MatchAll(query.get("queryText").toString());
			} else {
				squery = SearchQuery.MatchAll();
			}
			squery.setPage(page);
			WSearchResult<Object> docs = esService.searchObj(index, type, squery, Object.class);
			page.setTotal(docs.getTotal());
			JsonObj data = new JsonObj();
			data.put("page", page);
			data.put("data", docs.getData());
			result.ok(data);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			result.error(e);
		}
		return result;
	}
}
