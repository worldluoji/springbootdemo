package com.onlinecoffe.springonlinecoffee.support;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

/*
* 类型转换，用于将Json请求的字符串中的Money转化为Money对象
*
* */
@Component
public class MoneyFormatter implements Formatter<Money> {
    @Override
    public Money parse(String text, Locale locale) throws ParseException {
        if (NumberUtils.isParsable(text)) {
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(text));
        } else if (StringUtils.isNotEmpty(text)) {
            String[] info = StringUtils.split(" ");
            if (info != null && info.length == 2 && NumberUtils.isParsable(info[1])) {
                return Money.of(CurrencyUnit.of(info[0]), NumberUtils.createBigDecimal(info[1]));
            } else {
                throw new ParseException(text, 0);
            }
        }
        throw new ParseException(text, 1);
    }

    @Override
    public String print(Money money, Locale locale) {
        if (money == null) {
            return null;
        }
        return money.getCurrencyUnit().getCode() + " " + money.getAmount();
    }
}
