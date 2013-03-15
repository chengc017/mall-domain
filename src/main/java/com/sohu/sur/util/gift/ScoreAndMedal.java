package com.sohu.sur.util.gift;

import com.sohu.sur.util.lottery.UseMedal;

/**
 * 用户某买某商品的单价和最高勋章
 * 
 * @author xuewuhao
 * 
 */
public class ScoreAndMedal {

	private UseMedal useMedal;
	private int score;
	private String medalName;

	public UseMedal getUseMedal() {
		return useMedal;
	}

	public void setUseMedal(UseMedal useMedal) {
		this.useMedal = useMedal;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setMedalName(String medalName) {
		this.medalName = medalName;
	}

	public String getMedalName() {
		return medalName;
	}
}
