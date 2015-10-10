package com.bobo.code.web.controller.technology.httpcomponents;

public class TransferObj {
	private int statusCode;
	private String body;

	
	
	public TransferObj() {
		super();
	}

	public TransferObj(String body) {
		super();
		this.body = body;
	}


	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "ResponseTransferObj [statusCode=" + statusCode + ", body="
				+ body + "]";
	}

	
}
