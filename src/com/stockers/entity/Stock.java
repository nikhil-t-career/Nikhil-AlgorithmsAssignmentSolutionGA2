package com.stockers.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Stock {
	String nameId;
	float closingPrice;
	float[] prices = new float[5]; // last 5 days price
	float[] upDownPercentage = new float[5]; // last 5 days growth

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId.trim().toUpperCase();
	}

	public float getClosingPrice() {
		return closingPrice;
	}

	public void setClosingPrice(float closeingPrice) {
		this.closingPrice = closeingPrice;
	}

	public float[] getPrices() {
		return prices;
	}

	public void setPrices(float[] prices) {
		this.prices = prices;

	}

	public float[] getUpDownPercentage() {
		return upDownPercentage;
	}

	public void calcUpDownPercentage() {
		BigDecimal bd;
		float percentage;
		for (int i = 0; i < 5; i++) {
			percentage = ((closingPrice - prices[i]) / prices[i]) * 100;
			bd = new BigDecimal(percentage).setScale(3, RoundingMode.HALF_UP);
			upDownPercentage[i] = bd.floatValue();

			// [-0.1996008, -0.3984064, -0.5964215, -0.79365087, -0.990099]
			// [-0.2, -0.4, -0.6, -0.79, -0.99]
		}
	}

	@Override
	public String toString() {

//		System.out.println(nameId);
//		System.out.println(closingPrice);
//		System.out.println(Arrays.toString(prices));
//		System.out.println(Arrays.toString(upDownPercentage));

		return nameId + " \t\t: " + closingPrice;
	}

}
