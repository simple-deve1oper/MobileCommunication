package dev.communication.mobile.entity.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UnlimitedTest {
    private Unlimited unlimited;

    @BeforeEach
    public void init() {
        unlimited = new Unlimited(false);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Assertions.assertTrue((unlimited.isSms() == true) && (unlimited.isCalls() == true) &&
                (unlimited.isInternet() == false));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Unlimited copyUnlimited = new Unlimited(false);

        Assertions.assertTrue(unlimited.equals(copyUnlimited));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Unlimited otherUnlimited = new Unlimited(true);

        Assertions.assertFalse(unlimited.equals(otherUnlimited));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Unlimited cloneUnlimited = unlimited.clone();
            Assertions.assertTrue(unlimited.equals(cloneUnlimited));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Unlimited: " + ex.getMessage());
        }
    }
}
