package dev.communication.mobile.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.communication.mobile.entity.component.Unlimited;

import java.util.StringJoiner;

/**
 * Класс для описания корпоративных тарифов с абонентской платой и доступностью безлимита либо за смс, звонки и интернт,
 * либо только за смс и звонки
 * @version 1.0
 */
public class Enterprise extends Tariff {
    /* Фиксированная цена за один модуль (смс, минут или интернета) */
    private final double fixedPrice;
    /* Безлимит для смс, звонков и интернета (если у интернета нет безлимита, то он недоступен в тарифе совсем) */
    private Unlimited unlimited;

    /**
     * Конструктор для создания нового объекта типа Enterprise
     * @param name - наименование
     * @param numberClients - количество клиентов
     * @param unlimited - безлимит для смс, звонков и интернета
     */
    @JsonCreator
    public Enterprise(
            @JsonProperty("name") String name,
            @JsonProperty("numberClients") int numberClients,
            @JsonProperty("fixedPrice") double fixedPrice,
            @JsonProperty("unlimited") Unlimited unlimited
    ) {
        super(name, numberClients);
        this.fixedPrice = fixedPrice;
        this.unlimited = unlimited;
    }

    @Override
    public double findOutMonthlyCostOfTariff() {
        double cost = fixedPrice * 2;
        if (unlimited.isInternet()) {
            cost += fixedPrice;
        }
        return cost;
    }

    @Override
    public void descriptionTariff() {
        final StringBuilder sb = new StringBuilder();
        sb.append(String.format("Тариф с абонентской платой и фиксированным количеством смс, звонков%s%n",
                (unlimited.isInternet() ? " и гигабайт" : "")));
        sb.append("Наименование: " + getName() + "\n");
        sb.append("Количество клиентов: " + getNumberClients() + " человек(-а)\n");
        sb.append("Безлимитные смс: доступны\n");
        sb.append("Безлимитные звонки: доступны\n");
        if (unlimited.isInternet()) {
            sb.append("Безлимитный интернет: доступен\n");
        }
        sb.append("Стоимость абонентской платы: " + findOutMonthlyCostOfTariff() + " руб.\n");
        System.out.print(sb);
    }

    public Unlimited getUnlimited() {
        return unlimited;
    }
    public void setUnlimited(Unlimited unlimited) {
        this.unlimited = unlimited;
    }
    public double getFixedPrice() {
        return fixedPrice;
    }

    @Override
    public Enterprise clone() throws CloneNotSupportedException {
        Enterprise enterprise = (Enterprise) super.clone();
        enterprise.unlimited = unlimited.clone();
        return enterprise;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Enterprise enterprise = (Enterprise) obj;
        return super.equals(enterprise) && (fixedPrice == enterprise.fixedPrice) &&
                (unlimited == enterprise.unlimited || (unlimited != null && unlimited.equals(enterprise.unlimited)));
    }
    @Override
    public int hashCode() {
        return (int)(super.hashCode() + fixedPrice + (unlimited == null ? 0 : unlimited.hashCode()));
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Enterprise{", "}")
                .add(super.toString()).add("fixedPrice=" + fixedPrice)
                .add("unlimited" + unlimited).toString();
    }
}
