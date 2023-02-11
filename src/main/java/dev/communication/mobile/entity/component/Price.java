package dev.communication.mobile.entity.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Класс для описания цены за одно смс, за одну минуту звонка и за 1 Мб интернета
 * @version 1.0
 */
public class Price implements Cloneable {
    /* Цена за 1 смс */
    private double perOneSms;
    /* Цена за 1 минуту звонка */
    private double perOneMinuteCall;
    /* Цена за 1 Мб интернета */
    private double perOneMbInternet;

    /**
     * Конструктор для создания нового объекта типа Price
     * @param perOneSms - цена за 1 смс
     * @param perOneMinuteCall - цена за 1 минуту разговора
     * @param perOneMbInternet - цена за 1 Мб интернета
     */
    @JsonCreator
    public Price(
            @JsonProperty("perOneSms") double perOneSms,
            @JsonProperty("perOneMinuteCall") double perOneMinuteCall,
            @JsonProperty("perOneMbInternet") double perOneMbInternet
    ) {
        this.perOneSms = perOneSms;
        this.perOneMinuteCall = perOneMinuteCall;
        this.perOneMbInternet = perOneMbInternet;
    }

    public double getPerOneSms() {
        return perOneSms;
    }
    public void setPerOneSms(double perOneSms) {
        this.perOneSms = perOneSms;
    }
    public double getPerOneMinuteCall() {
        return perOneMinuteCall;
    }
    public void setPerOneMinuteCall(double perOneMinuteCall) {
        this.perOneMinuteCall = perOneMinuteCall;
    }
    public double getPerOneMbInternet() {
        return perOneMbInternet;
    }
    public void setPerOneMbInternet(double perOneMbInternet) {
        this.perOneMbInternet = perOneMbInternet;
    }

    @Override
    public Price clone() throws CloneNotSupportedException {
        Price price = (Price) super.clone();
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Price price = (Price) obj;
        return (perOneSms == price.perOneSms) && (perOneMinuteCall == price.perOneMinuteCall) &&
                (perOneMbInternet == price.perOneMbInternet);
    }
    @Override
    public int hashCode() {
        return (int)(31 + 1 + perOneSms + perOneMinuteCall + perOneMbInternet);
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Price{", "}")
                .add("perOneSms=" + perOneSms).add("perOneMinuteCall=" + perOneMinuteCall)
                .add("perOneMbInternet=" + perOneMbInternet).toString();
    }
}
