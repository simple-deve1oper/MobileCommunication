package dev.communication.mobile.data.parser;

import dev.communication.mobile.data.ListTariffs;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * Класс для описания методов для парсинга JSON файла
 * @version 1.0
 */
public class JsonParserTariffs {
    /**
     * Метод, который переводит Json-файл в объекты Java
     * @param filePath - путь к файлу JSON, который необходимо распарсить
     * @return возвращает объект класса-оболочки, который содержит (или нет) объекты тарифов
     */
    public Optional<ListTariffs> parse(String filePath) {
        ObjectMapper mapper = new ObjectMapper();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ListTariffs tariffs = mapper.readValue(reader, ListTariffs.class);
            return Optional.of(tariffs);
        } catch (FileNotFoundException ex) {
            System.err.println("Файл с данными о тарифах не найден: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Ошибка парсинга файла с данными о тарифах: " + ex.getMessage());
        }

        return Optional.empty();
    }
}
