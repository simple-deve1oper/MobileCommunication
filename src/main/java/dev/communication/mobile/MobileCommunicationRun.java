package dev.communication.mobile;

import dev.communication.mobile.console.ConsoleApplication;
import dev.communication.mobile.data.ListTariffs;
import dev.communication.mobile.data.parser.JsonParserTariffs;

import java.util.Optional;

/**
 * Класс для запуска приложения
 * @version 1.0
 */
public class MobileCommunicationRun {
    public static void main(String[] args) {
        JsonParserTariffs jsonParser = new JsonParserTariffs();
        String pathFile = "src/main/resources/tariffs.json";
        Optional<ListTariffs> optionalListTariffs = jsonParser.parse(pathFile);
        if (optionalListTariffs.isPresent()) {
            ListTariffs listTariffs = optionalListTariffs.get();
            ConsoleApplication application = new ConsoleApplication(listTariffs);
            application.run();
        } else {
            System.out.println("Произошла внутренняя ошибка, вернитесь к приложению позже пока наши " +
                    "специалисты разбираются в проблеме");
        }
    }
}
