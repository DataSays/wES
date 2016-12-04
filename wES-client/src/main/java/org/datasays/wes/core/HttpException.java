package org.datasays.wes.core;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by watano on 2016/11/21.
 */
public class HttpException extends Exception {
	private Request request;
	private Response respone;
	private String responeBody;

	public HttpException(String message) {
		super(message);
	}

	public HttpException(Throwable cause) {
		super(cause);
	}

	public HttpException(Request request, Response respone, Throwable cause) {
		super(cause);
		this.request = request;
		this.respone = respone;
	}

	public HttpException(Request request, Response respone) {
		super();
		this.request = request;
		this.respone = respone;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getRespone() {
		return respone;
	}

	public void setRespone(Response respone) {
		this.respone = respone;
	}

	public HttpException setResponeBody(String body) {
		this.responeBody = body;
		return this;
	}

	public boolean checkCode(int code) {
		if (respone != null && respone.code() == code) {
			return true;
		}
		return false;
	}

	public String toText() {
		StringBuffer sb = new StringBuffer();
		if (request != null) {
			sb.append(request.method());
			sb.append(":");
			sb.append(request.url().toString());
			sb.append("\n");
			RequestBody body = request.body();
			if (body != null) {
				try {
					if ("application/octet-stream".equalsIgnoreCase(body.contentType().type())) {
					} else {
						Buffer buff = new Buffer();
						request.body().writeTo(buff);
						sb.append(buff.readUtf8());
						sb.append("\n");
					}
				} catch (Exception e) {
				}
			}
		}
		if (respone != null) {
			if (respone.code() != 200) {
				sb.append("respone code:");
				sb.append(respone.code());
				sb.append("\n");
			}
			if (respone.message() != null) {
				sb.append("respone message:");
				sb.append(respone.message());
				sb.append("\n");
			}
			if (responeBody != null) {
				sb.append("respone body:\n");
				sb.append(responeBody);
				sb.append("\n");
			}
		}
		return sb.toString();
	}
}
