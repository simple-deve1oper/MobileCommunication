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
import dev.communication.mobile.util.comparator.CostTariffComparator;

import java.util.ArrayList;
import java.util.List;

public class SortTariffsTest {
    private SortTariffs sortTariffs;
    private ListTariffs listTariffs;

    @BeforeEach
    public void init() {
        CostTariffComparator comparator = new CostTariffComparator();
        sortTariffs = new SortTariffs(comparator);

        Price priceClassicTariff = new Price(1.80, 2.45, 8.0);
        Classic classicTariff = new Classic("Classic Tariff", 356, priceClassicTariff);
        Unlimited unlimitedStableTariff = new Unlimited(true);
        Enterprise stableTariff = new Enterprise("Stable Tariff", 1200, 578.78,
                unlimitedStableTariff);
        Quantity quantityOnlineTariff = new Quantity(50, 500, 50);
        Online onlineTariff = new Online("Online Tariff", 765, quantityOnlineTariff, true);
        Quantity quantitySuperTariff = new Quantity(50, 500, 50);
        List<MediaService> servicesSuperTariff = new ArrayList<>();
        servicesSuperTariff.add(MediaService.FRESH_SOUND);
        Social superTariff = new Social("Super Tariff", 878, quantitySuperTariff, true,
                servicesSuperTariff);

        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(onlineTariff);
        tariffs.add(classicTariff);
        tariffs.add(stableTariff);
        tariffs.add(superTariff);

        listTariffs = new ListTariffs(tariffs);
    }

    @Test
    @DisplayName("Сортровка тарифов по ежемесячной абонентской плате")
    public void doSortTariffs() {
        List<Tariff> list = listTariffs.getTariffs();

        try {
            Online otherOnlineTariff = (Online) list.get(0).clone();
            Classic otherClassicTariff = (Classic) list.get(1).clone();
            Enterprise otherStableTariff = (Enterprise) list.get(2).clone();
            Social otherSuperTariff = (Social) list.get(3).clone();

            Assertions.assertEquals(otherOnlineTariff, list.get(0));
            Assertions.assertEquals(otherClassicTariff, list.get(1));
            Assertions.assertEquals(otherStableTariff, list.get(2));
            Assertions.assertEquals(otherSuperTariff, list.get(3));

            list = sortTariffs.getSortTariffs(list);

            Assertions.assertEquals(otherClassicTariff, list.get(0));
            Assertions.assertEquals(otherOnlineTariff, list.get(1));
            Assertions.assertEquals(otherSuperTariff, list.get(2));
            Assertions.assertEquals(otherStableTariff, list.get(3));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования тарифа: " + ex.getMessage());
        }
    }
}
