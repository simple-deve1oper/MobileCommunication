package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.Price;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClassicTest {
    private Classic classicTariff;

    @BeforeAll
    public void init() {
        Price price = new Price(1.80, 2.45, 8.0);
        classicTariff = new Classic("Classic Tariff", 356, price);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Price copyPrice = new Price(1.80, 2.45, 8.0);

        Assertions.assertTrue((classicTariff.getName().equals("Classic Tariff") &&
                (classicTariff.getNumberClients() == 356) && classicTariff.getPrice().equals(copyPrice)));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Price copyPrice = new Price(1.80, 2.45, 8.0);
        Classic copyClassicTariff = new Classic("Classic Tariff", 356, copyPrice);
        // они не будут равны, т.к. при создании нового объекта счетчик идентификатора увеличивается на 1
        // с помощью логического блока, поэтому у идентификатора нового объекта будет значение на 1 больше
        // предыдущего объекта
        Assertions.assertFalse(classicTariff.equals(copyClassicTariff));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Price priceSimpleTariff = new Price(2, 2, 5);
        Classic simpleTariff = new Classic("Simple Tariff", 478, priceSimpleTariff);

        Assertions.assertFalse(classicTariff.equals(simpleTariff));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Classic cloneClassicTariff = classicTariff.clone();
            Assertions.assertTrue(classicTariff.equals(cloneClassicTariff));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Classic: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Попытка добавить несколько новых клиентов к тарифу")
    public void tryAddNewClients() {
        Price copyPrice = new Price(1.80, 2.45, 8.0);
        Classic moreCopyClassicTariff = new Classic("Classic Tariff", 356, copyPrice);
        Assertions.assertEquals(356, moreCopyClassicTariff.getNumberClients());
        moreCopyClassicTariff.addClients(75);
        Assertions.assertEquals(431, moreCopyClassicTariff.getNumberClients());
    }

    @Test
    @DisplayName("Верна ли абонентская плата за тариф")
    public void tryFindOutSubscriptionFee() {
        Assertions.assertEquals(0, classicTariff.findOutMonthlyCostOfTariff());
    }
}
