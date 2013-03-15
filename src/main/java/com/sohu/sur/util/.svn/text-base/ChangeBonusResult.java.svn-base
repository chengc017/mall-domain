package com.sohu.sur.util;

public class ChangeBonusResult {
	
	public static final ChangeBonusResult OK = new ChangeBonusResult(0, "OK");
    public static final ChangeBonusResult BANNED_ACCOUNT = new ChangeBonusResult(7, "BANNED_ACCOUNT");
    public static final ChangeBonusResult INSUFFICIENT_BONUS = new ChangeBonusResult(8, "INSUFFICIENT_BONUS");
	public static final ChangeBonusResult ERROR = new ChangeBonusResult(-1, "ERROR");
    
    private int result; 
    private String msg;
    private int bonus;
    
    
	public ChangeBonusResult() {
		super();
	}

	public ChangeBonusResult(int result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
    
	public ChangeBonusResult(int result, String msg, int bonus) {
		super();
		this.result = result;
		this.msg = msg;
		this.bonus = bonus;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getBonus() {
		return bonus;
	}
	
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
        
}
