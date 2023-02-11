package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.MediaService;
import dev.communication.mobile.entity.component.Quantity;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SocialTest {
    private Social superTariff;

    @BeforeAll
    public void init() {
        Quantity quantity = new Quantity(50, 500, 50);
        List<MediaService> services = new ArrayList<>();
        services.add(MediaService.FRESH_SOUND);
        superTariff = new Social("Super Tariff", 878, quantity, true, services);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Quantity copyQuantity = new Quantity(50, 500, 50);
        List<MediaService> copyServices = new ArrayList<>();
        copyServices.add(MediaService.FRESH_SOUND);

        Assertions.assertTrue((superTariff.getName().equals("Super Tariff") &&
                (superTariff.getNumberClients() == 878) && superTariff.getQuantity().equals(copyQuantity) &&
                (superTariff.isShareInternet() == true) && superTariff.getServices().equals(copyServices)));
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        Quantity copyQuantity = new Quantity(50, 500, 50);
        List<MediaService> copyServices = new ArrayList<>();
        copyServices.add(MediaService.FRESH_SOUND);
        Social copySuperTariff = new Social("Super Tariff", 878, copyQuantity, true, copyServices);
        // они не будут равны, т.к. при создании нового объекта счетчик идентификатора увеличивается на 1
        // с помощью логического блока, поэтому у идентификатора нового объекта будет значение на 1 больше
        // предыдущего объекта
        Assertions.assertFalse(superTariff.equals(copySuperTariff));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsAuthors() {
        Quantity otherQuantity = new Quantity(30, 350, 15);
        List<MediaService> services = new ArrayList<>();
        services.add(MediaService.FRESH_SOUND);
        services.add(MediaService.GAMES_NOW);
        services.add(MediaService.BOOKS_FOR_ALL);
        Social otherTariff = new Social("Other Tariff", 295, otherQuantity, false, services);

        Assertions.assertFalse(superTariff.equals(otherTariff));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Social cloneSuperTariff = superTariff.clone();
            Assertions.assertTrue(superTariff.equals(cloneSuperTariff));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Social: " + ex.getMessage());
        }
    }

    @Test
    @DisplayName("Попытка добавить несколько новых клиентов к тарифу")
    public void tryAddNewClients() {
        Quantity moreCopyQuantity = new Quantity(50, 500, 50);
        List<MediaService> moreCopyServices = new ArrayList<>();
        moreCopyServices.add(MediaService.FRESH_SOUND);
        Social moreSuperTariff = new Social("Super Tariff", 878, moreCopyQuantity, true, moreCopyServices);

        Assertions.assertEquals(878, moreSuperTariff.getNumberClients());
        moreSuperTariff.addClients(30);
        Assertions.assertEquals(908, moreSuperTariff.getNumberClients());
    }

    @Test
    @DisplayName("Попытка добавить и удалить сервисы")
    public void tryAddAndRemoveServices() {
        Quantity otherCopyQuantity = new Quantity(50, 500, 50);
        List<MediaService> otherCopyServices = new ArrayList<>();
        otherCopyServices.add(MediaService.FRESH_SOUND);
        Social otherCopySuperTariff = new Social("Super Tariff", 878, otherCopyQuantity, true, otherCopyServices);

        Assertions.assertNotNull(otherCopySuperTariff.getServices());
        Assertions.assertEquals(1, otherCopySuperTariff.getServices().size());
        otherCopySuperTariff.addService(MediaService.FRESH_SOUND);
        Assertions.assertEquals(1, otherCopySuperTariff.getServices().size());
        otherCopySuperTariff.addService(MediaService.GAMES_NOW);
        otherCopySuperTariff.addService(MediaService.BOOKS_FOR_ALL);
        Assertions.assertEquals(3, otherCopySuperTariff.getServices().size());
        otherCopySuperTariff.removeService(MediaService.WORLD_MOVIES);
        Assertions.assertEquals(3, otherCopySuperTariff.getServices().size());
        otherCopySuperTariff.removeService(MediaService.FRESH_SOUND);
        Assertions.assertEquals(2, otherCopySuperTariff.getServices().size());
    }

    @Test
    @DisplayName("Проверка на то, изменится ли исходный список в объекте, если в его копии добавить новый элемент")
    public void tryChangeOriginalList() {
        Quantity copyQuantity = new Quantity(50, 500, 50);
        List<MediaService> copyServices = new ArrayList<>();
        copyServices.add(MediaService.FRESH_SOUND);
        Social copySuperTariff = new Social("Super Tariff", 878, copyQuantity, true, copyServices);

        List<MediaService> copy = copySuperTariff.getServices();
        Assertions.assertEquals(1, copy.size());
        Assertions.assertTrue(copySuperTariff.getServices().equals(copy));
        copy.add(MediaService.BOOKS_FOR_ALL);
        Assertions.assertFalse(copySuperTariff.getServices().equals(copy));
    }

    @Test
    @DisplayName("Верна ли абонентская плата за тариф")
    public void tryFindOutSubscriptionFee() {
        double cost = (superTariff.getQuantity().getSms() + superTariff.getQuantity().getMinutesCall() +
                superTariff.getQuantity().getGigabytesInternet()) / 3;
        if (superTariff.isShareInternet()) {
            cost += 50;
        }
        if ((superTariff.getServices().size() < 2) || (superTariff.getServices().size() == 2)) {
            for (MediaService service : superTariff.getServices()) {
                cost += (service.getPrice() / 2);
            }
        } else if (superTariff.getServices().size() > 2) {
            for (MediaService service : superTariff.getServices()) {
                double discount = (service.getPrice() / 100) * 75;
                cost += (service.getPrice() - discount);
            }
        }

        Assertions.assertEquals(cost, superTariff.findOutMonthlyCostOfTariff());
        Assertions.assertEquals(314.5, superTariff.findOutMonthlyCostOfTariff());
    }
}
