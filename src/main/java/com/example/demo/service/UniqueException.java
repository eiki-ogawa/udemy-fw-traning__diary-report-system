package com.example.demo.service;

public class UniqueException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public UniqueException(String message) {
		super(message);
	}

}
