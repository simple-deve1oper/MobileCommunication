package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.Quantity;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OnlineTest {
    private Online onlineTariff;

    @BeforeAll
    public void init() {
        Quantity quantity = new Quantity(50, 500, 50);
        onlineTariff = new Online("Online Tariff", 765, quantity, true);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Quantity copyQuantity = new Quantity(50, 500, 50);

        Assertions.assertTrue(onlineTariff.getName().equals("Online Tariff") &&
                (onlineTariff.getNumberClients() == 765) && onlineTariff.getQuantity().equals(copyQuantity) &&
                onlineTariff.isShareInternet() == true);
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Quantity copyQuantity = new Quantity(50, 500, 50);
        Online copyOnlineTariff = new Online("Online Tariff", 765, copyQuantity, true);
        // они не будут равны, т.к. при создании нового объекта счетчик идентификатора увеличивается на 1
        // с помощью логического блока, поэтому у идентификатора нового объекта будет значение на 1 больше
        // предыдущего объекта
        Assertions.assertFalse(onlineTariff.equals(copyOnlineTariff));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Quantity otherQuantity = new Quantity(30, 350, 15);
        Online startTariff = new Online("Start Tariff", 127, otherQuantity);

        Assertions.assertFalse(onlineTariff.equals(startTariff));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Online cloneOnlineTariff = onlineTariff.clone();
            Assertions.assertTrue(onlineTariff.equals(cloneOnlineTariff));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Online: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Попытка добавить несколько новых клиентов к тарифу")
    public void tryAddNewClients() {
        Quantity moreCopyQuantity = new Quantity(50, 500, 50);
        Online moreCopyOnlineTariff = new Online("Online Tariff", 765, moreCopyQuantity, true);
        Assertions.assertEquals(765, moreCopyOnlineTariff.getNumberClients());
        moreCopyOnlineTariff.addClients(23);
        Assertions.assertEquals(788, moreCopyOnlineTariff.getNumberClients());
    }

    @Test
    @DisplayName("Верна ли абонентская плата за тариф")
    public void tryFindOutSubscriptionFee() {
        double cost = (onlineTariff.getQuantity().getSms() + onlineTariff.getQuantity().getMinutesCall() +
                onlineTariff.getQuantity().getGigabytesInternet()) / 3;
        if (onlineTariff.isShareInternet()) {
            cost += 50;
        }

        Assertions.assertEquals(cost, onlineTariff.findOutMonthlyCostOfTariff());
        Assertions.assertEquals(250, onlineTariff.findOutMonthlyCostOfTariff());
    }
}
