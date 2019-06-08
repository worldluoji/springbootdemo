package com.mongo.simplemongo.convert;

import org.bson.Document;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;


/**
* 只要遇到Money的变量就转化为：
 * "price" : { "money" : { "currency" : { "code" : "CNY", "numericCode" : 156, "decimalPlaces" : 2 }, "amount" : "30.00" } }
* */
public class MoneyReadConvert implements Converter<Document, Money> {
    @Override
    public Money convert(Document source) {
        Document money = (Document)source.get("money");
        double amount = Double.parseDouble(money.getString("amount"));
        String currency = ((Document)money.get("currency")).getString("code");
        return Money.of(CurrencyUnit.of(currency), amount);
    }
}
