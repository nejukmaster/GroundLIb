package com.nejukmaster.groundlib.exceptions;

public class NO_MPP_EXIST_EXCEPTION extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NO_MPP_EXIST_EXCEPTION() {
		System.out.println("NO_MPP_EXIST_EXCEPTION: You can't load model with out model_props.mpp.");
	}

}
