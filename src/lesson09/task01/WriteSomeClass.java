package lesson09.task01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для создания финальной версии файла SomeClass
 * содержит методы для считывания с консоли пользовательских строк
 * и записи их в файл
 */
public class WriteSomeClass {
    /**
     * функция считывания с клавиатуры текста метода doWork()
     * и занесения в файл
     *
     * @param path - путь до файла
     */
    public void readWriteSomeClass(Path path) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            /** создаем List, в который заносим код
             */
            List<String> lines = new ArrayList<>();

            lines.add("public class SomeClass implements lesson09.task01.Worker {");
            lines.add("");
            lines.add("   @Override");
            lines.add("   public void doWork() {");

            String line;

            System.out.println("Введите строку кода метода doWork:");

            while (!(line = bufferedReader.readLine()).equals("")) {
                lines.add(line);
                //lines.add("System.out.println("пишем строку");");
            }

            lines.add("   }");
            lines.add("}");

            Files.write(path, lines);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}





