package dev.communication.mobile.print;

import dev.communication.mobile.util.ClientStatistics;
import dev.communication.mobile.util.FindTariffs;
import dev.communication.mobile.util.SortTariffs;
import dev.communication.mobile.util.comparator.CostTariffComparator;
import dev.communication.mobile.entity.Tariff;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Класс описания методов для вывода информации о тарифах
 * @version 1.0
 */
public class PrintingInfo {
    /* Объект для сортировки тарифов */
    private SortTariffs sortTariffs;
    /* Объект для получения статистики о клиентах */
    private ClientStatistics clientStatistics;
    /* Объект для нахождения тарифов по заданным параметрам */
    private FindTariffs findTariffs;

    /**
     * Конструктор для создания нового объекта типа PrintingInfo
     * @param sortTariffs - объект для сортировки тарифов
     */
    public PrintingInfo(SortTariffs sortTariffs) {
        Comparator comparatorCost = new CostTariffComparator();
        this.sortTariffs = new SortTariffs(comparatorCost);
        this.clientStatistics = new ClientStatistics();
        this.findTariffs = new FindTariffs();
    }

    /**
     * Метод для вывода списка тарифов
     * @param tariffs - список тарифов
     */
    public void outputAllTariffs(List<Tariff> tariffs) {
        System.out.println("-----------------------");
        for (Tariff tariff : tariffs) {
            tariff.descriptionTariff();
            System.out.println("-----------------------");
        }
    }

    /**
     * Метод для вывода отсортрованного списка тарифов
     * @param tariffs - список тарифов без сортировки
     */
    public void outputSortTariffs(List<Tariff> tariffs) {
        System.out.println("Отсортированный список тарифов по цене:");
        List<Tariff> sortList = sortTariffs.getSortTariffs(tariffs);
        outputAllTariffs(tariffs);
    }

    /**
     * Метод для нахождения тарифа по наименованию в списке тарифов
     * @param name - наименование тарифа, который необходимо найти
     * @param tariffs - список тарифов
     */
    public void outputFindTariffByName(String name, List<Tariff> tariffs) {
        Optional<Tariff> optionalTariff = findTariffs.findTariffByName(name, tariffs);
        if (optionalTariff.isPresent()) {
            System.out.println("Тариф с наименованием " + name + " найден:");
            System.out.println("-----------------------");
            optionalTariff.get().descriptionTariff();
            System.out.println("-----------------------");
        } else {
            System.out.println("Тариф с наименованием " + name + " не найдено");
            System.out.println("-----------------------");
        }
    }

    /**
     * Метод для нахождения тарифов в заданном диапазоне цен
     * @param from - цена, с которой начинается диапазон
     * @param to - цена, на которой заканчивается диапазон
     * @param tariffs - список тарифов
     */
    public void outputFindTariffByCost(double from, double to, List<Tariff> tariffs) {
        List<Tariff> listFindTariffs = findTariffs.findTariffsByCost(from, to, tariffs);
        if (listFindTariffs.size() == 0) {
            System.out.println("Тарифов в диапазоне с ценой начиная с " + from + " руб. по " + to + " руб. не найдено");
            System.out.println("-----------------------");
        } else {
            System.out.println("Тарифы в диапазоне с ценой начиная с " + from + " руб. по " + to + " руб.:");
            listFindTariffs = sortTariffs.getSortTariffs(listFindTariffs);
            outputAllTariffs(listFindTariffs);
        }
    }

    /**
     * Метод для вывода общей численности клиентов тарифа из списка тарифов
     * @param tariffs - список тарифов
     */
    public void outputTotalNumberOfClients(List<Tariff> tariffs) {
        int totalNumber = clientStatistics.totalNumberOfCustomers(tariffs);
        System.out.println("Общая численность клиентов тарифов компании равна: " + totalNumber + " человек(-а)");
        System.out.println("-----------------------");
    }
}
