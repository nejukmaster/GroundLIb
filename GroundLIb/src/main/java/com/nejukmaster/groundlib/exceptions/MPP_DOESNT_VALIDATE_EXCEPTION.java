package com.nejukmaster.groundlib.exceptions;

public class MPP_DOESNT_VALIDATE_EXCEPTION extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public MPP_DOESNT_VALIDATE_EXCEPTION() {
		System.out.println("MPP_DOESNT_VAILD_EXCEPTION: this MPP file is not vaild. Do you Check the mpp has \"input\", \"output\" and \"output_nods\" keys?");
	}

}
