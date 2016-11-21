package org.datasays.wes.demo.vo;

import java.util.ArrayList;
import java.util.List;

import org.datasays.es2.EsItem;
import org.datasays.util.collection.StrMap;

public class BaseEsProduct extends EsItem implements IPropVO{
	private static final long serialVersionUID = 1624191251006111385L;
	private String categories;//分类
	private String categoriesId;//分类id
	private String tags;//标签
	private String name;//名称
	private StrMap props = new StrMap();//属性
	private String description;//详细描述
	private List<EsProductSKU> skus = new ArrayList<>();

	public BaseEsProduct() {
		super();
		setType("BaseEsProduct");
	}

	public BaseEsProduct(String id) {
		super(id);
		setType("BaseEsProduct");
	}
	
	public BaseEsProduct(String index, String type, String id, String name) {
		super(index, type, id);
		setName(name);
		setType("BaseEsProduct");
	}
	
	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getCategoriesId() {
		return categoriesId;
	}


	public void setCategoriesId(String categoriesId) {
		this.categoriesId = categoriesId;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EsProductSKU> getSkus() {
		return skus;
	}

	public void setSkus(List<EsProductSKU> skus) {
		this.skus = skus;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public StrMap getProps() {
		if(props == null){
			props = new StrMap();
		}
		return props;
	}

	public void setProps(StrMap props) {
		this.props = props;
	}
}