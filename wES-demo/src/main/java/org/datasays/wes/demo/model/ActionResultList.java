package org.datasays.wes.demo.model;

import org.datasays.wes.core.JsonObj;

import java.util.List;

/**
 * Created by watano on 2016/12/22.
 */
public class ActionResultList implements IActionResult<List<JsonObj>> {
	private int code;
	private String message;
	private List<JsonObj> data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<JsonObj> getData() {
		return data;
	}

	public void setData(List<JsonObj> data) {
		this.data = data;
	}
}
