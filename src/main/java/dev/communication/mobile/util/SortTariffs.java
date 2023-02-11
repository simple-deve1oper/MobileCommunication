package dev.communication.mobile.util;

import dev.communication.mobile.entity.Tariff;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Класс для описания сортировки списка тарифов
 * @version 1.0
 */
public class SortTariffs {
    /* Компаратор, по которому будет происходить сортировка */
    private Comparator comparator;

    /**
     * Конструктор для создания нового объекта типа Online
     * @param comparator - компаратор, по которому будет происходить сортировка
     */
    public SortTariffs(Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * Метод, который возвращает отсортированный список
     * @param tariffs - список тарфифов, который необходимо отсортировать
     * @return возвращает отсортированный список тарифов
     */
    public List<Tariff> getSortTariffs(List<Tariff> tariffs) {
        Collections.sort(tariffs, comparator);
        return tariffs;
    }
}
