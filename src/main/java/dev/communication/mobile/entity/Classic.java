package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.Price;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Класс для описания тарифов без абонентской (ежемесячной) платы
 * @version 1.0
 */
public class Classic extends Tariff {
    /* Цена за одну смс, минуту звонка и Мб интернета */
    private Price price;

    /**
     * Конструктор для создания нового объекта типа Classic
     * @param name - наименование
     * @param numberClients - количество клиентов
     * @param price - цена за одну смс, минуту звонка и Мб интернета
     */
    @JsonCreator
    public Classic(
            @JsonProperty("name") String name,
            @JsonProperty("numberClients") int numberClients,
            @JsonProperty("price") Price price
    ) {
        super(name, numberClients);
        this.price = price;
    }

    @Override
    public double findOutMonthlyCostOfTariff() {
        return 0;
    }

    @Override
    public void descriptionTariff() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Тариф без абонентской платы\n");
        sb.append("Наименование: " + getName() + "\n");
        sb.append("Количество клиентов: " + getNumberClients() + " человек(-а)\n");
        sb.append("Цена за одну смс: " + price.getPerOneSms() + " руб.\n");
        sb.append("Цена за одну минуту звонка: " + price.getPerOneMinuteCall() + " руб.\n");
        sb.append("Цена за один МБ интернета: " + price.getPerOneMbInternet() + " руб.\n");
        System.out.print(sb);
    }

    public Price getPrice() {
        return price;
    }
    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public Classic clone() throws CloneNotSupportedException {
        Classic classic = (Classic) super.clone();
        classic.price = (Price) price.clone();
        return classic;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Classic classic = (Classic) obj;
        return super.equals(classic) && (price == classic.price || (price != null && price.equals(classic.price)));
    }
    @Override
    public int hashCode() {
        return super.hashCode() + (price == null ? 0 : price.hashCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Classic{", "}")
                .add(super.toString()).add("price=" + price.toString()).toString();
    }
}
