package dev.communication.mobile.entity.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuantityTest {
    private Quantity quantity;

    @BeforeEach
    public void init() {
        quantity = new Quantity(100, 500, 50);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Assertions.assertTrue((quantity.getSms() == 100) && (quantity.getMinutesCall() == 500) &&
                (quantity.getGigabytesInternet() == 50));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Quantity copyQuantity = new Quantity(100, 500, 50);

        Assertions.assertTrue(quantity.equals(copyQuantity));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Quantity otherQuantity = new Quantity(45, 700, 12);

        Assertions.assertFalse(quantity.equals(otherQuantity));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Quantity cloneQuantity = quantity.clone();
            Assertions.assertTrue(quantity.equals(cloneQuantity));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Quantity: " + ex.getMessage());
        }
    }
}
