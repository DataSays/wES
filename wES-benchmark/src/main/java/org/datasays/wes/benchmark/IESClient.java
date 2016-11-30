package org.datasays.wes.benchmark;

import org.datasays.wes.core.HttpException;
import org.datasays.wes.vo.IEsItem;
import org.datasays.wes.vo.Query;
import org.datasays.wes.vo.SearchQuery;
import org.datasays.wes.vo.WSearchResult;

/**
 * Created by watano on 2016/11/28.
 */
public interface IESClient {
		public void init()throws Exception;
		public void close()throws Exception;

		public boolean createIndex(String index)throws Exception;
		public boolean delIndex(String index) throws Exception;

		public IEsItem saveIndex(IEsItem doc)throws Exception;

		public <T> WSearchResult<T> searchObj(String index, String type, SearchQuery query, Class<T> cls) throws Exception;

		public IEsItem get(IEsItem doc) throws Exception;

		public boolean delete(IEsItem doc) throws Exception;

		public boolean deleteByQuery(String index, String type, Query query) throws Exception;
}
