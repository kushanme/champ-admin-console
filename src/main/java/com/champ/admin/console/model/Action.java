package com.champ.admin.console.model;


import com.champ.admin.console.exception.InvalidActionException;

public enum Action {
	
	START,STOP,RESTART,INSTALL;
	
	
	public static Action getAction(String actionName) throws InvalidActionException{
		Action action = null;
		try{
			action = Action.valueOf(actionName.toUpperCase());
		}catch(Exception ex){
			throw new InvalidActionException(actionName);
		}
		return action;
	}

}
