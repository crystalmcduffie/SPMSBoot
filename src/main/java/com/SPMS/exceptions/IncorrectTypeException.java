package com.SPMS.exceptions;

@SuppressWarnings("serial")
public class IncorrectTypeException extends Exception {
	public IncorrectTypeException () {
		super("The role or task type is incorrect for this operation.");
	}
}
