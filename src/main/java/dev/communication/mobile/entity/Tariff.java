package dev.communication.mobile.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.StringJoiner;

/**
 * Класс для описания абстрактного представления тарифа
 * @version 1.0
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Classic.class, name = "Classic"),
        @JsonSubTypes.Type(value = Online.class, name = "Online"),
        @JsonSubTypes.Type(value = Social.class, name = "Social"),
        @JsonSubTypes.Type(value = Enterprise.class, name = "Enterprise")
})
public abstract class Tariff implements Cloneable {
    /* Логический блок для автоматического повышения и установки id у вновь созданного объекта тарифа */
    {
        id = ++count;
    }

    /* Счётчик для установки нового значения id */
    private static long count = 0;
    /* Идентификатор */
    private long id;
    /* Наименование */
    private String name;
    /* Количество клиентов у тарифа */
    private int numberClients;

    /**
     * Конструктор для создания нового объекта типа Tariff
     * @param name - наименование
     * @param numberClients - количество клиентов
     */
    public Tariff(String name, int numberClients) {
        this.name = name;
        this.numberClients = numberClients;
    }

    /**
     * Метод для добавления прибывшего количества клиентов тарифа
     * @param quantity - количество
     */
    public void addClients(int quantity) {
        numberClients += quantity;
    }

    /**
     * Абстрактный метод для получения ежемесячной стоимости тарифа
     * @return возвращает ежемксячную стоимость тарифа
     */
    public abstract double findOutMonthlyCostOfTariff();
    /**
     * Абстрактный метод для описания тарифа
     */
    public abstract void descriptionTariff();

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumberClients() {
        return numberClients;
    }
    public void setNumberClients(int numberClients) {
        this.numberClients = numberClients;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Tariff tariff = (Tariff) obj;
        return (id == tariff.id) && (name == tariff.name) && (numberClients == tariff.numberClients);
    }
    @Override
    public int hashCode() {
        return (int)(31 * 1 + id + (name == null ? 0 : name.hashCode()) + numberClients);
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Tariff{", "}")
                .add("id=" + id).add("name=" + name)
                .add("numberClients=" + numberClients).toString();
    }
}
