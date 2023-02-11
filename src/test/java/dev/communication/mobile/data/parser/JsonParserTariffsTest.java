package dev.communication.mobile.data.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.communication.mobile.entity.Online;
import org.junit.jupiter.api.*;
import dev.communication.mobile.data.ListTariffs;
import dev.communication.mobile.entity.Classic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonParserTariffsTest {
    private JsonParserTariffs jsonParserTariffs;

    @BeforeEach
    public void init() {
        jsonParserTariffs = new JsonParserTariffs();
    }

    @Test
    @DisplayName("Проверка, что переданные в JSON-файле объекты типа Classic и Online десериализовались")
    public void test1() {
        ObjectMapper mapper = new ObjectMapper();
        String pathFile = "src/test/resources/tariffsForTest.json";
        try(BufferedReader reader = new BufferedReader(new FileReader(pathFile))) {
            ListTariffs listTariffs = mapper.readValue(reader, ListTariffs.class);

            Assertions.assertTrue(listTariffs.getTariffs().get(0) instanceof Classic);
            Assertions.assertTrue(listTariffs.getTariffs().get(1) instanceof Online);
        } catch (FileNotFoundException ex) {
            System.err.println("Файл с данными о тарифах не найден: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Ошибка парсинга файла с данными о тарифах: " + ex.getMessage());
        }
    }
}
