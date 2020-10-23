package com.store.resources.exceptions.handlers;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
	public StandardError(int status, String message, Long timeStamp, String error, String path) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.setError(error);
		this.message = message;
		this.setPath(path);
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	

}
