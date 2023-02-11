package dev.communication.mobile.entity;

import dev.communication.mobile.entity.component.MediaService;
import dev.communication.mobile.entity.component.Quantity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * Класс для описания тарифов с абонентской платы и с фиксированным количеством доступных
 * смс, минут и гигабайт. Есть возможность делиться доступными гигабайтами с другими пользователями.
 * Ключевой возможностью этого тарифа является возможность подключения медиасервисов - вместо полной стоимости цена за
 * подписку каждого сервиса может доходить до цены со скидкой в 75%. Поэтому этот тип тарифа является по своей сути
 * динамическим, позволяя настраивать необходимые подписки.
 * @version 1.0
 */
public class Social extends Online {
    /* Список подключенных медиасервисов к тарифу */
    private List<MediaService> services;

    /**
     * Конструктор для создания нового объекта типа Social
     * @param name - наименование
     * @param numberClients - количество клиентов
     * @param quantity - количество доступных смс, минут и гигабайт
     * @param shareInternet - возможность делиться трафиком с другими пользователями
     * @param services - список подключенных медиасервисов к тарифу
     */
    @JsonCreator
    public Social(
            @JsonProperty("name") String name,
            @JsonProperty("numberClients") int numberClients,
            @JsonProperty("quantity") Quantity quantity,
            @JsonProperty("shareInternet") boolean shareInternet,
            @JsonProperty("services") List<MediaService> services
    ) {
        super(name, numberClients, quantity, shareInternet);
        this.services = services;
    }

    /**
     * Метод для добавлния медиасервиса в тариф
     * @param service - медиасервис
     */
    public void addService(MediaService service) {
        if (checkService(service)) {
            System.out.println("Сервис " + service.getName() + " уже подключен к вашему тарифу");
        } else {
            services.add(service);
            System.out.println("Сервис " + service.getName() + " подключен к вашему тарифу");
        }
    }

    /**
     * Метод для удаления медиасервиса из тарифа
     * @param service - медиасервис
     */
    public void removeService(MediaService service) {
        if (services.size() == 0) {
            System.out.println("У вас нет никаких подключенных сервисов, чтобы их удалять");
        } else if (checkService(service)) {
            services.remove(service);
            System.out.println("Сервис " + service.getName() + " удалён из вашего тарифа");
        } else {
            System.out.println("Сервис " + service.getName() + " не найден в списке подключенных, чтобы его удалять");
        }
    }

    /**
     * Метод для проверки нахождения медиасервиса в списке подключенных
     * @param findService - медиасервис, который необходимо найти
     * @return возвращает ответ о том, найден ли сервис в списке подключенных или нет
     */
    private boolean checkService(MediaService findService) {
        if (services.size() == 0) {
            return true;
        }

        for(MediaService service : services) {
            if (service.equals(findService)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public double findOutMonthlyCostOfTariff() {
        double cost = super.findOutMonthlyCostOfTariff();
        if ((services.size() < 2) || (services.size() == 2)) {
            for (MediaService service : services) {
                cost += (service.getPrice() / 2);
            }
        } else if (services.size() > 2) {
            for (MediaService service : services) {
                double discount = (service.getPrice() / 100) * 75;
                cost += (service.getPrice() - discount);
            }
        }
        return cost;
    }

    @Override
    public void descriptionTariff() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Тариф с абонентской платой и фиксированным количеством смс, звонков и гигабайт.\nА также главным " +
                "плюсом является то, что можно подключать желаемые медиасервисы к тарифу и платить за них не полную " +
                "сумму, а цену со скидкой до 75% в зависимости от количества подключенных сервисов\n");
        sb.append("Наименование: " + getName() + "\n");
        sb.append("Количество клиентов: " + getNumberClients() + " человек(-а)\n");
        sb.append("Количество доступных смс: " + getQuantity().getSms() + "\n");
        sb.append("Количество доступных минут: " + getQuantity().getMinutesCall() + "\n");
        sb.append("Количество доступных Гб: " + getQuantity().getGigabytesInternet() + "\n");
        sb.append("Доступные подписки на медиасервисы для вас: ");
        if (services.size() == 0) {
            sb.append("Ваш список медиасервисов пуст, скорее подключите их");
        } else {
            sb.append(formattedServices() + "\n");
        }
        sb.append("Стоимость абонентской платы: " + findOutMonthlyCostOfTariff() + " руб.\n");
        System.out.print(sb);
    }

    public List<MediaService> getServices() {
        // в этом методе всегда возвращается копия (а не оригинал) списка, чтобы никто не смог изменить
        // список без контроля
        List<MediaService> copy = new ArrayList<>();
        copy.addAll(services);
        return copy;
    }
    public void setServices(List<MediaService> services) {
        this.services = services;
    }

    /**
     * Метод для глубокого клонирования списка
     * @param services - исходный массив с с медиасервисами
     * @return возвращает копию массива с сервисами
     */
    private List<MediaService> deepCloneList(List<MediaService> services) {
        List<MediaService> clone = new ArrayList<>(services.size());
        for (MediaService service : services) {
            clone.add(service);
        }
        return clone;
    }

    /**
     * Метод, который форматирует названия Медиасервисов в одну строку
     * @return возвращает отформатированную строку с названиями доступных медиасервисов
     */
    private String formattedServices() {
        StringBuilder formattedText = new StringBuilder();
        for (int i = 0; i < services.size(); i++) {
            formattedText.append(services.get(i).getName());

            if (i != (services.size() - 1)) {
                formattedText.append(", ");
            }
        }
        return formattedText.toString();
    }

    @Override
    public Social clone() throws CloneNotSupportedException {
        Social social = (Social) super.clone();
        if (services.size() == 0) {
            social.services = new ArrayList<>();
        } else {
            social.services = deepCloneList(services);
        }
        return social;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Social social = (Social) obj;
        return super.equals(social) &&
                (services == social.services || (services != null && services.equals(social.services)));
    }
    @Override
    public int hashCode() {
        return super.hashCode() + (services == null ? 0 : services.hashCode());
    }

    @Override
    public String toString() {
        return new StringJoiner(",", "Social{", "}")
                .add(super.toString()).add("services=" + services).toString();
    }
}
