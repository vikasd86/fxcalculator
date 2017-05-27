package com.anz.fxcalculator.model;

import java.util.HashMap;
import java.util.Map;

public enum Currency {

	AUD, CAD, CNY, CZK, DKK, EUR, GBP, JPY, NOK, NZD, USD;
	
	private static Map<String, Currency> map = new HashMap<>();

	static {
		for (Currency currencyEnum : Currency.values()) {
			map.put(currencyEnum.toString(), currencyEnum);
		}
	}

	public static Currency getValueOf(String currency) {
		return map.get(currency);
	}
}
