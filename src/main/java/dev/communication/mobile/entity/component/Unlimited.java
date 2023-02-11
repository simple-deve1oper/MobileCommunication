package dev.communication.mobile.entity.component;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Класс для описания безлимита для смс, звонков и интернета
 * @version 1.0
 */
public class Unlimited implements Cloneable {
    /* Доступность безлимитных смс */
    private boolean sms;
    /* Доступность безлимитных звонков */
    private boolean calls;
    /* Доступность безлимитного интернета */
    private boolean internet;

    /**
     * Конструктор для создания нового объекта типа Unlimited
     * @param internet - доступность безлимитного интернета
     */
    @JsonCreator
    public Unlimited(@JsonProperty("internet") boolean internet) {
        this.internet = internet;
        this.sms = true;
        this.calls = true;
    }

    public boolean isSms() {
        return sms;
    }
    public boolean isCalls() {
        return calls;
    }
    public boolean isInternet() {
        return internet;
    }
    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    @Override
    public Unlimited clone() throws CloneNotSupportedException {
        Unlimited unlimited = (Unlimited) super.clone();
        return unlimited;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Unlimited unlimited = (Unlimited) obj;
        return (sms == unlimited.sms) && (calls == unlimited.calls) &&
                (internet == unlimited.internet);
    }
    @Override
    public int hashCode() {
        return 31 * 1 + (sms ? 1 : 0) + (calls ? 1 : 0) + (internet ? 1 : 0);
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Unlimited{", "}")
                .add("sms=" + sms).add("calls=" + calls)
                .add("internet=" + internet).toString();
    }
}
