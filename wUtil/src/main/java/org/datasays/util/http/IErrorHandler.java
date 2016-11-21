package org.datasays.util.http;

import retrofit2.Response;

public interface IErrorHandler {	
	public void handleError(Response<?> response) throws Exception;
	public void handleError(Exception e);
}
