package com.champ.admin.console.exception;

public class InvalidActionException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4381043786852360162L;
	
	public InvalidActionException(){
		super();
	}
	
	public InvalidActionException(String message){
		super("Invalid Action : " + message);
	}
	

}
