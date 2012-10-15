package cl.utfsm.aemf.token;

import java.util.ArrayList;

public class Token {
	private String tokenId;
	private ArrayList<String> valuesAccepted;
	private String tokenType;
	
	/**
	 * Constructor
	 */
	public Token(){
		valuesAccepted = new ArrayList<String>();
	}
	
	/**
	 * Add a new accepted value by the token
	 * @param value
	 */
	public void addAcceptedValue(String value){
		valuesAccepted.add(value);
	}
	
	/*
	 * Getters and setters
	 */
	public void setTokenId(String tknId){
		this.tokenId = tknId;
	}
	
	public String getTokenId(){
		return this.tokenId;
	}
	
	public ArrayList<String> getValuesAcceptedList(){
		return valuesAccepted;
	}

	public void setTokenType(String value) {
		this.tokenType = value;	
	}
	
	public String getTokenType(){
		return tokenType;
	}
}
