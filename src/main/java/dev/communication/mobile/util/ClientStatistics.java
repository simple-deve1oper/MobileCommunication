package dev.communication.mobile.util;

import dev.communication.mobile.entity.Tariff;

import java.util.List;

/**
 * Класс для описания методов, которые собирают данные по пользователям тарифа
 * @version 1.0
 */
public class ClientStatistics {
    /**
     * Метод, который возвращает общую численность клиентов
     * @param tariffs - список тарифов, по которому будет считаться общая численность клиентов
     * @return возвращает число клиентов тарифов из списка
     */
    public int totalNumberOfCustomers(List<Tariff> tariffs) {
        int totalNumber = 0;
        for (Tariff tariff : tariffs) {
            totalNumber += tariff.getNumberClients();
        }
        return totalNumber;
    }
}
