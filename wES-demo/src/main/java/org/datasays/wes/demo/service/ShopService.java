package org.datasays.wes.demo.service;

import org.datasays.es2.EsHelper2;
import org.datasays.es2.vo.SearchQuery;
import org.datasays.es2.vo.WSearchResult;
import org.datasays.util.WPageIterator;
import org.datasays.wes.demo.vo.BaseEsProduct;
import org.datasays.wes.demo.vo.BaseEsProductItem;

public class ShopService {
	public String index = "zhonglinex";	
	private EsHelper2 esHelper = new EsHelper2();
	
	public WSearchResult<BaseEsProduct> searchProduct(SearchQuery queryDSL) throws Exception {
		return esHelper.searchObj(index, BaseEsProduct.class.getSimpleName(), queryDSL, BaseEsProduct.class);
	}
	
	public WPageIterator<BaseEsProduct> searchProductIterator(SearchQuery queryDSL) throws Exception {
		return esHelper.search(index, BaseEsProduct.class.getSimpleName(), queryDSL, BaseEsProduct.class);
	}
	
	public WSearchResult<BaseEsProductItem> searchProductItem(SearchQuery queryDSL) throws Exception {
		return esHelper.searchObj(index, BaseEsProductItem.class.getSimpleName(), queryDSL, BaseEsProductItem.class);
	}	
}
