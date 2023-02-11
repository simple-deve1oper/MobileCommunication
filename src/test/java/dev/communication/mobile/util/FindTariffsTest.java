package dev.communication.mobile.util;

import dev.communication.mobile.entity.*;
import dev.communication.mobile.entity.component.MediaService;
import dev.communication.mobile.entity.component.Price;
import dev.communication.mobile.entity.component.Quantity;
import dev.communication.mobile.entity.component.Unlimited;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import dev.communication.mobile.data.ListTariffs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FindTariffsTest {
    private FindTariffs findTariffs;
    private ListTariffs listTariffs;

    @BeforeEach
    public void init() {
        findTariffs = new FindTariffs();

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

    @Test
    @DisplayName("Нахождение тарифа по названию")
    public void tryFindTariffByName() {
        List<Tariff> copyList = listTariffs.getTariffs();

        Optional<Tariff> optionalTariff = findTariffs.findTariffByName("Online Tariff", copyList);
        Assertions.assertTrue(optionalTariff.isPresent());
        optionalTariff = findTariffs.findTariffByName("Hard Tariff", copyList);
        Assertions.assertTrue(optionalTariff.isEmpty());
    }

    @Test
    @DisplayName("Нахождение тарифа в пределах диапазона цены")
    public void tryFindTariffByCost() {
        List<Tariff> copyList = listTariffs.getTariffs();

        List<Tariff> listWithFindTariffs = findTariffs.findTariffsByCost(200.0, 400.0, copyList);
        Assertions.assertEquals(2, listWithFindTariffs.size());
        listWithFindTariffs = findTariffs.findTariffsByCost(678.5, 985.1, copyList);
        Assertions.assertEquals(0, listWithFindTariffs.size());
    }
}
