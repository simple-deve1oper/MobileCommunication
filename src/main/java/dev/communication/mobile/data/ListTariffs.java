package dev.communication.mobile.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.communication.mobile.entity.Tariff;

import java.util.ArrayList;
import java.util.List;

/**
 * Неизменяемый класс для хранения списка тарифов компании
 * @version 1.0
 */
public final class ListTariffs {
    /* Список тарифов компании */
    private final List<Tariff> tariffs;

    /**
     * Конструктор для создания нового объекта типа ListTariffs
     * @param tariffs - список тарифов компании
     */
    @JsonCreator
    public ListTariffs(@JsonProperty("tariffs") List<Tariff> tariffs) {
        this.tariffs = tariffs;
    }

    public List<Tariff> getTariffs() {
        // в этом методе всегда возвращается копия (а не оригинал) списка, чтобы никто не смог изменить
        // список без контроля
        List<Tariff> copy = new ArrayList<>();
        copy.addAll(tariffs);
        return copy;
    }
}
