package dev.communication.mobile.util;

import dev.communication.mobile.entity.*;
import dev.communication.mobile.entity.component.MediaService;
import dev.communication.mobile.entity.component.Price;
import dev.communication.mobile.entity.component.Quantity;
import dev.communication.mobile.entity.component.Unlimited;
import org.junit.jupiter.api.*;
import dev.communication.mobile.data.ListTariffs;

import java.util.ArrayList;
import java.util.List;

public class ClientStatisticsTest {
    private ListTariffs listTariffs;

    @BeforeEach
    public void init() {
        Price price = new Price(1.80, 2.45, 8.0);
        Classic classicTariff = new Classic("Classic Tariff", 356, price);
        Unlimited unlimited = new Unlimited(true);
        Enterprise stableTariff = new Enterprise("Stable Tariff", 1200, 578.78, unlimited);
        Quantity quantityAlwaysOnline = new Quantity(50, 500, 50);
        Online onlineTariff = new Online("Online Tariff", 765, quantityAlwaysOnline, true);
        Quantity quantitySuperNetwork = new Quantity(50, 500, 50);
        List<MediaService> servicesSuperNetwork = new ArrayList<>();
        servicesSuperNetwork.add(MediaService.FRESH_SOUND);
        Social superTariff = new Social("Super Tariff", 878, quantitySuperNetwork, true, servicesSuperNetwork);

        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(classicTariff);
        tariffs.add(stableTariff);
        tariffs.add(onlineTariff);
        tariffs.add(superTariff);

        listTariffs = new ListTariffs(tariffs);
    }

    @DisplayName("Получить несколько раз общую численность клиентов тарифа из списка")
    @RepeatedTest(value = 10,
            name = "Повторяющийся тест получения общего количества численности клиентов " +
                    "{currentRepetition} из {totalRepetitions}")
    public void getMultipleTimesTotalNumberOfClients() {
        ClientStatistics statistic = new ClientStatistics();

        List<Tariff> list = listTariffs.getTariffs();
        int total = 0;
        for (Tariff tariff : list) {
            total += tariff.getNumberClients();
        }

        Assertions.assertEquals(total, statistic.totalNumberOfCustomers(list));
        Assertions.assertEquals(3199, statistic.totalNumberOfCustomers(list));
    }
}
