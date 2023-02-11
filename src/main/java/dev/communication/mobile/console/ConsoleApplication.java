package dev.communication.mobile.console;

import dev.communication.mobile.data.ListTariffs;
import dev.communication.mobile.print.PrintingInfo;
import dev.communication.mobile.util.SortTariffs;
import dev.communication.mobile.util.comparator.CostTariffComparator;

import java.util.Comparator;
import java.util.Scanner;

/**
 * Класс для описания консольного приложения
 * @version 1.0
 */
public class ConsoleApplication {
    /* Объект со списком тарифом */
    private ListTariffs listTariffs;
    /* Объект для ввода */
    private Scanner scanner;
    /* Имя пользователя */
    private String name;
    /* Объект для вывода информации о тарифах */
    private PrintingInfo printingInfo;

    /**
     * Конструктор для создания нового объекта типа ConsoleApplication
     * @param listTariffs - объект со списком тарифов
     */
    public ConsoleApplication(ListTariffs listTariffs) {
        this.listTariffs = listTariffs;
        scanner = new Scanner(System.in);

        Comparator comparatorCost = new CostTariffComparator();
        SortTariffs sortTariffs = new SortTariffs(comparatorCost);
        printingInfo = new PrintingInfo(sortTariffs);
    }

    /**
     * Метод для запуска приложения
     */
    public void run() {
        hello();
        int number = 0;
        while (number != 6) {
            infoButton();
            int inputNum = inputNumber();
            number = buttonSelection(inputNum);
            System.out.println();
        }
        System.out.println("Работа приложения завершена");
    }

    /**
     * Метод для приветствия
     */
    private void hello() {
        System.out.println("Привет! Как тебя зовут?");
        this.name = scanner.nextLine();
        System.out.println("Приятно познакомиться, " + name + "!");
    }

    /**
     * Метод с информацией возможных действий для взаимодействия пользователя с приложением
     */
    private void infoButton() {
        System.out.println("Для взаимодействия системой тебе необходимо ввести одну из цифр описанных ниже:");
        System.out.println("1 - Показать все тарифы и их описание");
        System.out.println("2 - Подсчитать общую численность клиентов всех тарифов компании");
        System.out.println("3 - Отсортировать все тарифы по мере возрастания цены абонентской платы за месяц");
        System.out.println("4 - Найти необходимый тариф по названию");
        System.out.println("5 - Найти необходимый тариф по диапазону цен");
        System.out.println("6 - Выйти из приложения");
    }

    /**
     * Ввод числа с клавиатуры для взаимодействия с системой
     * @return возвращает введенное пользователем число
     */
    private int inputNumber() {
        while (true) {
            System.out.println("Введите число:");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                return number;
            } else {
                scanner = new Scanner(System.in);
                System.out.println("Вы ввели не число. Попробуйте ещё раз");
                infoButton();
            }
        }
    }

    /**
     * Метод для выбора кнопки и выполнение действий для этой кнопки
     * @return возвращает введенное ранее и переданное в параметре число
     */
    private int buttonSelection(int number) {
        switch (number) {
            case 1:
                System.out.println("Все тарифы компании (без сортировки):");
                printingInfo.outputAllTariffs(listTariffs.getTariffs());
                return 1;
            case 2:
                printingInfo.outputTotalNumberOfClients(listTariffs.getTariffs());
                return 2;
            case 3:
                printingInfo.outputSortTariffs(listTariffs.getTariffs());
                return 3;
            case 4:
                findTariffByName();
                return 4;
            case 5:
                findTariffByCost();
                return 5;
            case 6:
                System.out.println("Пока, " + this.name + "!");
                return 6;
            default:
                System.out.println("Такой цифры нет в списке предложенных для взаимодействия. Попробуйте ещё раз");
                return 0;
        }
    }

    /**
     * Метод для поиска тарифа по наименованию - case 4 в методе buttonSelection(int number)
     */
    private void findTariffByName() {
        System.out.print("Введите наименование тарифа, по которому будет производится поиск:");
        scanner = new Scanner(System.in);
        String nameTariff = scanner.nextLine();
        printingInfo.outputFindTariffByName(nameTariff, listTariffs.getTariffs());
    }

    /**
     * Метод для поиска тарифов в диапазоне цен - case 5 в методе buttonSelection(int number)
     */
    private void findTariffByCost() {
        System.out.println("Введите диапазон сумм, по которым будет осуществляться поиск");
        System.out.print("С: ");
        double from = 0;
        if (scanner.hasNextDouble()) {
            from = scanner.nextDouble();
        } else {
            scanner = new Scanner(System.in);
            System.out.println("Вы ввели не число для диапазона 'С', поиск далее не возможен");
            return;
        }
        System.out.print("По: ");
        double to = 0;
        if (scanner.hasNextDouble()) {
            to = scanner.nextDouble();
            if (from > to) {
                System.out.println("Диапазон 'По' не может быть меньше 'С', поэтому поиск с такими значениями " +
                        "далее не возможен");
                scanner = new Scanner(System.in);
                return;
            }
        } else {
            scanner = new Scanner(System.in);
            System.out.println("Вы ввели не число для диапазона 'По', поиск далее не возможен");
            return;
        }
        printingInfo.outputFindTariffByCost(from, to, listTariffs.getTariffs());
    }
}
