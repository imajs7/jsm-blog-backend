package com.jsmblog.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Integer fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue){
		super( String.format("%s with %s = %d Not found", resourceName, fieldName, fieldValue) );
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
