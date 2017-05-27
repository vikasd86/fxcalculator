package com.anz.fxcalculator.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anz.fxcalculator.io.FileReader;
import com.anz.fxcalculator.model.Currency;

public class CurrencyConversionConfigReader {

	private Map<Currency, Map<Currency, String>> configMap = new HashMap<>();
	private static final CurrencyConversionConfigReader INSTANCE = new CurrencyConversionConfigReader();

	private CurrencyConversionConfigReader() {
		super();
		initializeConfig();
	}

	public static CurrencyConversionConfigReader getInstance() {
		return INSTANCE;
	}

	public String conversionType(Currency baseCurrency, Currency termCurrency)
	{
		if(configMap.get(baseCurrency) == null || configMap.get(baseCurrency).get(termCurrency) == null)
		{
			return null;
		}
		
		return configMap.get(baseCurrency).get(termCurrency);
	}

	private void initializeConfig() {
		List<String> readLines = FileReader.read("C:\\Users\\VDewan\\workspace\\fxcalculator\\src\\main\\resources\\currency-conversion.config");

		for (String line : readLines) {
			if (null != line && !line.isEmpty()) {
				String[] tempArr = line.split(",");
				if (tempArr.length == 3) {
					String baseCurrency = tempArr[0];
					String termCurrency = tempArr[1];
					String conversionType = tempArr[2];
					setConfig(Currency.getValueOf(baseCurrency), Currency.getValueOf(termCurrency), conversionType);
				}
			}
		}
	}

	public String getConversionType(String baseCurrency, String termCurrency)
	{
		return configMap.get(Currency.getValueOf(baseCurrency)).get(Currency.getValueOf(termCurrency));
	}
	private void setConfig(Currency baseCurrency, Currency termCurrency, String conversionType) {
		if (configMap.get(baseCurrency) != null && baseCurrency != null
				&& termCurrency != null) {
			configMap.get(baseCurrency).put(termCurrency, conversionType);
		
		} else if(configMap.get(baseCurrency) == null && baseCurrency != null
				&& termCurrency != null)
		{
			Map<Currency, String> tempMap = new HashMap<>();
			tempMap.put(termCurrency, conversionType);
			configMap.put(baseCurrency, tempMap);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Currency Config ");
		System.out.println(CurrencyConversionConfigReader.getInstance().getConversionType("AUD", "CAD"));
	}
}
