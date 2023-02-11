package dev.communication.mobile.util;

import dev.communication.mobile.entity.Tariff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс для описания методов для поиска тарифов
 * @version 1.0
 */
public class FindTariffs {
    /**
     * Метод поиска тарифа по наименованию
     * @param name - имя тарифа
     * @param tariffs - список тарифов, в котором производится поиск
     * @return возвращает класс-оболочку с найденным (или нет) тарифом
     */
    public Optional<Tariff> findTariffByName(String name, List<Tariff> tariffs) {
        Optional<Tariff> optionalTariff;
        for (Tariff tariff : tariffs) {
            if (tariff.getName().equals(name)) {
                return Optional.of(tariff);
            }
        }
        return Optional.empty();
    }

    /**
     * Метод поиска тарифа в определенном ценовом диапазоне
     * @param from - начальный диапазон цены абонентской стоимости
     * @param to - конечный диапазон цены абонентской стоимости
     * @param tariffs - список тарифов, в котором производится поиск
     * @return - возвращает список тарифов с найденными (или нет) тарифами
     */
    public List<Tariff> findTariffsByCost(double from, double to, List<Tariff> tariffs) {
        List<Tariff> requiredTariffs = new ArrayList<>();
        for (Tariff tariff : tariffs) {
            if ((tariff.findOutMonthlyCostOfTariff() >= from) && (tariff.findOutMonthlyCostOfTariff() <= to)) {
                requiredTariffs.add(tariff);
            }
        }
        return requiredTariffs;
    }
}
