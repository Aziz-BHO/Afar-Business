package com.formalab.niw.entities.validator;

public interface FieldValueExists {

	boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
