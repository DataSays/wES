package org.datasays.wes.demo.vo;

import java.io.Serializable;

import org.datasays.util.collection.StrMap;


public class EsProductSKU implements Serializable{
	private static final long serialVersionUID = 8863701060166897354L;
	private int code;//SKU编号
	private String name;//名称
	private Double price;//零售价
	private Double wholesalePrice;//批发价
	private Double quantity;//库存量
	private Double minquantity;//起定量
	private StrMap props = new StrMap();//sku属性
	private String propsText = "";//sku属性字符串
	private String dbPSKUId;
	private String dbPItemId;
	private String dbSourceSkuId;
	private boolean telBargaining;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public StrMap getProps() {
		return props;
	}
	public void setProps(StrMap props) {
		this.props = props;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Double getWholesalePrice() {
		return wholesalePrice;
	}
	public void setWholesalePrice(Double wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}
	public String getPropsText() {
		return propsText;
	}
	public void setPropsTxt(String propsText) {
		this.propsText = propsText;
	}
	public String getDbPSKUId() {
		return dbPSKUId;
	}
	public void setDbPSKUId(String dbPSKUId) {
		this.dbPSKUId = dbPSKUId;
	}
	public String getDbPItemId() {
		return dbPItemId;
	}
	public void setDbPItemId(String dbPItemId) {
		this.dbPItemId = dbPItemId;
	}
	public String getDbSourceSkuId() {
		return dbSourceSkuId;
	}
	public void setDbSourceSkuId(String dbSourceSkuId) {
		this.dbSourceSkuId = dbSourceSkuId;
	}
	public boolean isTelBargaining() {
		return telBargaining;
	}
	public void setTelBargaining(boolean telBargaining) {
		this.telBargaining = telBargaining;
	}
	public void setPropsText(String propsText) {
		this.propsText = propsText;
	}	
}
