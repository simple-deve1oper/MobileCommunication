package dev.communication.mobile.entity.component;

import java.util.StringJoiner;

/**
 * Перечисление для выбора медиасервиса
 * @version 1.0
 */
public enum MediaService {
    /* Константы */
    FRESH_SOUND("Свежий звук", 129), WORLD_MOVIES("Мировое кино", 179),
    GAMES_NOW("Игры сейчас", 199), BOOKS_FOR_ALL("Книги для всех", 299);

    /* Наименование */
    private String name;
    /* Цена - абонентская плата */
    private double price;

    /**
     * Конструктор для перечисления типа MediaService
     * @param name - наименование
     * @param price - цена
     */
    MediaService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /* Методы получения значения */
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "MediaService{", "}")
                .add("name=" + name).add("price=" + price).toString();
    }
}
