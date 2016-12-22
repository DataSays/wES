package org.datasays.wes.demo.model;

import org.datasays.wes.core.HttpException;

/**
 * Created by watano on 2016/12/22.
 */
public interface IActionResult<T> {
	public int getCode();

	public String getMessage();

	public void setCode(int code);

	public void setMessage(String msg);

	public void setData(T data);

	public T getData();

	public default void msg(int code, String msg) {
		this.setCode(code);
		this.setMessage(msg);
	}

	public default void errorMsg(String msg) {
		this.msg(-1, msg);
	}

	public default void okMsg(String msg) {
		this.msg(1, msg);
	}

	public default void ok(T data){
		this.setData(data);
		okMsg("");
	}

	public default void error(Throwable t) {
		if(t instanceof HttpException){
			HttpException httpex = (HttpException)t;
			if(httpex.getRespone() != null){
				errorMsg(httpex.getRespone().message());
			}else{
				errorMsg(httpex.toText());
			}
		}else {
			this.msg(-100, t.getMessage());
		}
	}
}
