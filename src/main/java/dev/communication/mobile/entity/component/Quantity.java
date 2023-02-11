package dev.communication.mobile.entity.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Класс для описания доступных смс, минут и пакет трафика в гигабайтах за стоимость абонентской платы
 * @version 1.0
 */
public class Quantity implements Cloneable {
    /* Количество смс */
    private int sms;
    /* Количество минут */
    private int minutesCall;
    /* Количество гигабайт */
    private int gigabytesInternet;

    /**
     * Конструктор для создания нового объекта типа Quantity
     * @param sms - количество смс
     * @param minutesCall - количество минут
     * @param gigabytesInternet - количество гигабайт
     */
    @JsonCreator
    public Quantity(
            @JsonProperty("sms") int sms,
            @JsonProperty("minutesCall") int minutesCall,
            @JsonProperty("gigabytesInternet") int gigabytesInternet
    ) {
        this.sms = sms;
        this.minutesCall = minutesCall;
        this.gigabytesInternet = gigabytesInternet;
    }

    public int getSms() {
        return sms;
    }
    public void setSms(int sms) {
        this.sms = sms;
    }
    public int getMinutesCall() {
        return minutesCall;
    }
    public void setMinutesCall(int minutesCall) {
        this.minutesCall = minutesCall;
    }
    public int getGigabytesInternet() {
        return gigabytesInternet;
    }
    public void setGigabytesInternet(int gigabytesInternet) {
        this.gigabytesInternet = gigabytesInternet;
    }

    @Override
    public Quantity clone() throws CloneNotSupportedException {
        Quantity quantity = (Quantity) super.clone();
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Quantity quantity = (Quantity) obj;
        return (sms == quantity.sms) && (minutesCall == quantity.minutesCall) &&
                (gigabytesInternet == quantity.gigabytesInternet);
    }
    @Override
    public int hashCode() {
        return 31 * 1 + sms + minutesCall + gigabytesInternet;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Quantity{", "}")
                .add("sms=" + sms).add("minutesCall=" + minutesCall)
                .add("gigabytesInternet=" + gigabytesInternet).toString();
    }
}
