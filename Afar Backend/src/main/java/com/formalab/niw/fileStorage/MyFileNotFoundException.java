package com.formalab.niw.fileStorage;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
	 public MyFileNotFoundException(String message) {
	        super(message);
	    }

	    public MyFileNotFoundException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
