package dev.communication.mobile.entity.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceTest {
    private Price price;

    @BeforeEach
    public void init() {
        price = new Price(1.80, 2.45, 8.0);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Assertions.assertTrue((price.getPerOneSms() == 1.80) && (price.getPerOneMinuteCall() == 2.45) &&
                (price.getPerOneMbInternet() == 8.0));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Price copyPrice = new Price(1.80, 2.45, 8.0);

        Assertions.assertTrue(price.equals(copyPrice));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Price otherPrice = new Price(2, 2, 5);

        Assertions.assertFalse(price.equals(otherPrice));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Price clonePrice = price.clone();
            Assertions.assertTrue(price.equals(clonePrice));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Price: " + ex.getMessage());
        }
    }
}
