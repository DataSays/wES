package org.datasays.wes.demo.vo;

import java.util.ArrayList;
import java.util.List;

public class BaseEsProductItem extends BaseEsProduct {
	private static final long serialVersionUID = -1368904389537502405L;
	private Double price;// 价格
	private Double quantity;// 数量
	private Double minquantity;// 起订量
	private String esProductId; // BaseEsProduct Id
	private String dbItemId; // ProductItem Id
	private List<EsProductSKU> esSkus = new ArrayList<>();

	public BaseEsProductItem() {
		super();
		setType("BaseEsProductItem");
	}

	public BaseEsProductItem(String id) {
		super(id);
		setType("BaseEsProductItem");
	}

	public BaseEsProductItem(BaseEsProduct product) {
		super();
		setId(null);
		setType("BaseEsProductItem");
		updateProduct(product);
		setProps(product.getProps());
	}

	public BaseEsProductItem(String index, String type, String id, String name) {
		super(index, type, id, name);
	}

	public EsProductSKU getSku(String dbSkuId, String name) {
		if (esSkus != null && (dbSkuId != null || name != null)) {
			for (EsProductSKU sku : esSkus) {
				if ((dbSkuId != null && dbSkuId.equals(sku.getDbPSKUId()))
						|| (name != null && name.equals(sku.getName()))) {
					return sku;
				}
			}
		}
		return null;
	}

	public void updateProduct(BaseEsProduct product) {
		esProductId = product.getId();
		setName(product.getName());
		setCategories(product.getCategories());
		setCategoriesId(product.getCategoriesId());
		setTags(product.getTags());
		setDescription(product.getDescription());
	}

	public String getDbItemId() {
		return dbItemId;
	}

	public void setDbItemId(String dbItemId) {
		this.dbItemId = dbItemId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getMinquantity() {
		return minquantity;
	}

	public void setMinquantity(Double minquantity) {
		this.minquantity = minquantity;
	}
	
	public String getEsProductId() {
		return esProductId;
	}

	public void setEsProductId(String esProductId) {
		this.esProductId = esProductId;
	}

	public List<EsProductSKU> getEsSkus() {
		return esSkus;
	}

	public void setEsSkus(List<EsProductSKU> esSkus) {
		this.esSkus = esSkus;
	}
}
