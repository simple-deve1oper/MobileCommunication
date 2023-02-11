package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.Unlimited;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EnterpriseTest {
    private Enterprise stableTariff;

    @BeforeAll
    public void init() {
        Unlimited unlimited = new Unlimited(true);
        stableTariff = new Enterprise("Stable Tariff", 1200, 578.78, unlimited);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Unlimited copyUnlimited = new Unlimited(true);

        Assertions.assertTrue((stableTariff.getName().equals("Stable Tariff") &&
                (stableTariff.getNumberClients() == 1200) && stableTariff.getUnlimited().equals(copyUnlimited)));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Unlimited copyUnlimited = new Unlimited(true);
        Enterprise copyStableTariff = new Enterprise("Stable Tariff", 1200, 578.78, copyUnlimited);
        // они не будут равны, т.к. при создании нового объекта счетчик идентификатора увеличивается на 1
        // с помощью логического блока, поэтому у идентификатора нового объекта будет значение на 1 больше
        // предыдущего объекта
        Assertions.assertFalse(stableTariff.equals(copyStableTariff));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Unlimited otherUnlimited = new Unlimited(false);
        Enterprise safeTariff = new Enterprise("Safe Tariff", 478, 345.6, otherUnlimited);

        Assertions.assertFalse(stableTariff.equals(safeTariff));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Enterprise cloneStableTariff = stableTariff.clone();
            Assertions.assertTrue(stableTariff.equals(cloneStableTariff));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Enterprise: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Попытка добавить несколько новых клиентов к тарифу")
    public void tryAddNewClients() {
        Unlimited moreCopyUnlimited = new Unlimited(true);
        Enterprise moreCopyStableTariff = new Enterprise("Stable Tariff", 1200, 578.78, moreCopyUnlimited);
        Assertions.assertEquals(1200, moreCopyStableTariff.getNumberClients());
        moreCopyStableTariff.addClients(115);
        Assertions.assertEquals(1315, moreCopyStableTariff.getNumberClients());
    }

    @Test
    @DisplayName("Верна ли абонентская плата за тариф")
    public void tryFindOutSubscriptionFee() {
        double cost = stableTariff.getFixedPrice() * 3;

        Assertions.assertEquals(cost, stableTariff.findOutMonthlyCostOfTariff());
        Assertions.assertEquals(1736.34, stableTariff.findOutMonthlyCostOfTariff());
    }
}
