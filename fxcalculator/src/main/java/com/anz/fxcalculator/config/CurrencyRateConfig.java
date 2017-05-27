package com.anz.fxcalculator.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.anz.fxcalculator.io.FileReader;

public class CurrencyRateConfig {

	private static CurrencyRateConfig currencyRateConfig = new CurrencyRateConfig();
	private Map<String, Double> configMap = new HashMap<>();

	private CurrencyRateConfig() {
		initializeConfig();
	}

	public static CurrencyRateConfig getInstance()
	{
		return currencyRateConfig;
	}

	public double getRate(String baseCurrency, String termCurrency)
	{
		return configMap.get(baseCurrency+termCurrency);
	}

	private void initializeConfig() {
		List<String> readLines = FileReader.read("C:\\Users\\VDewan\\workspace\\fxcalculator\\src\\main\\resources\\exchange-rate.config");

		for (String line : readLines) {
			if (null != line && !line.isEmpty()) {
				String []tempArr = line.split("=");
				if(tempArr.length == 2)
				{
					String baseTerm = tempArr[0];
					double exchangeRate = Double.valueOf(tempArr[1]);
					configMap.put(baseTerm, exchangeRate);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(CurrencyRateConfig.getInstance().getRate("AUD", "USD"));
	}
}
