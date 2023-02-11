package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.Quantity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

/**
 * Класс для описания тарифов с абонентской платы и с фиксированным количеством доступных
 * смс, минут и гигабайт. Также можно установить возможность, чтобы пользователь мог делиться
 * доступными гигабайтами с другими пользователями
 * @version 1.0
 */
public class Online extends Tariff {
    /* Количество доступных смс, минут и гигабайт */
    private Quantity quantity;
    /* Возможности делиться трафиком с другими пользователями */
    private boolean shareInternet;

    /**
     * Конструктор для создания нового объекта типа Online
     * @param name - наименование
     * @param numberClients - количество клиентов
     * @param quantity - количество доступных смс, минут и гигабайт
     */
    @JsonCreator
    public Online(
            @JsonProperty("name") String name,
            @JsonProperty("numberClients") int numberClients,
            @JsonProperty("quantity") Quantity quantity
    ) {
        super(name, numberClients);
        this.quantity = quantity;
    }
    /**
     * Конструктор для создания нового объекта типа Online
     * @param name - наименование
     * @param numberClients - количество клиентов
     * @param quantity - количество доступных смс, минут и гигабайт
     * @param shareInternet - возможность делиться трафиком с другими пользователями
     * @see Online#Online(String, int, Quantity)
     */
    public Online(String name, int numberClients, Quantity quantity, boolean shareInternet) {
        this(name, numberClients, quantity);
        this.shareInternet = shareInternet;
    }

    @Override
    public double findOutMonthlyCostOfTariff() {
        double cost = (quantity.getSms() + quantity.getMinutesCall() + quantity.getGigabytesInternet()) / 3;
        if (shareInternet) {
            cost += 50;
        }
        return cost;
    }

    @Override
    public void descriptionTariff() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Тариф с абонентской платой и фиксированным количеством смс, звонков и гигабайт. ");
        sb.append("Есть возможность делиться доступными гигабайтами с другими пользователями\n");
        sb.append("Наименование: " + getName() + "\n");
        sb.append("Количество клиентов: " + getNumberClients() + " человек(-а)\n");
        sb.append("Количество доступных смс: " + quantity.getSms() + "\n");
        sb.append("Количество доступных минут: " + quantity.getMinutesCall() + "\n");
        sb.append("Количество доступных Гб: " + quantity.getGigabytesInternet() + "\n");
        sb.append("Возможность делиться доступными гигабайтами с другими пользователями: ");
        if (isShareInternet()) {
            sb.append("Да\n");
        } else {
            sb.append("Нет\n");
        }
        sb.append("Стоимость абонентской платы: " + findOutMonthlyCostOfTariff() + " руб.\n");
        System.out.print(sb);
    }

    public Quantity getQuantity() {
        return quantity;
    }
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
    public boolean isShareInternet() {
        return shareInternet;
    }
    public void setShareInternet(boolean shareInternet) {
        this.shareInternet = shareInternet;
    }

    @Override
    public Online clone() throws CloneNotSupportedException {
        Online online = (Online) super.clone();
        online.quantity = (Quantity) quantity.clone();
        return online;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Online online = (Online) obj;
        return super.equals(online) && (quantity == online.quantity || (quantity != null && quantity.equals(online.quantity))) &&
                (shareInternet == online.shareInternet);
    }
    @Override
    public int hashCode() {
        return super.hashCode() + (quantity == null ? 0 : quantity.hashCode()) + (shareInternet ? 1 : 0);
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Online{", "}")
                .add(super.toString()).add("quantity=" + quantity)
                .add("shareInternet=" + shareInternet).toString();
    }
}
