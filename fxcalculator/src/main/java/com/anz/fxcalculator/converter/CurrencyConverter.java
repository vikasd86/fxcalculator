package com.anz.fxcalculator.converter;

import com.anz.fxcalculator.config.CurrencyConversionConfigReader;
import com.anz.fxcalculator.config.CurrencyRateConfig;
import com.anz.fxcalculator.model.Currency;

public class CurrencyConverter {

	public static double convert(Currency fromCurrency, Currency toCurrency, int units) {
		String conversionType = CurrencyConversionConfigReader.getInstance().conversionType(fromCurrency, toCurrency);

		if (conversionType.equalsIgnoreCase("1")) {
			return units;
		} else if (conversionType.equalsIgnoreCase("D")) {
			return CurrencyRateConfig.getInstance().getRate(fromCurrency.toString(), toCurrency.toString());
		} else if (conversionType.equalsIgnoreCase("inv")) {
			return 1 / CurrencyRateConfig.getInstance().getRate(toCurrency.toString(), fromCurrency.toString());
		} else {
				double baseRateInCCY = convert(fromCurrency, Currency.getValueOf(conversionType), 1);
				double toCurrencyRateInCCY = convert(Currency.getValueOf(conversionType), toCurrency, 1);
				return baseRateInCCY*toCurrencyRateInCCY;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(convert(Currency.JPY, Currency.AUD,1));
	}
}
