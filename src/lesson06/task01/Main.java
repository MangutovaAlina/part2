package lesson06.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Задание 1. Написать программу, читающую текстовый файл.
 *
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 */

public class Main {
    public static void main(String[] args) {
        List<String> strFileList = new ArrayList<>();

        strFileList = readFromFile("src\\lesson06\\task01\\input.txt");

        /**
         * удаляем повторяющиеся слова
         */
        Set<String> setString = new LinkedHashSet<>();
        setString.addAll(strFileList);
        strFileList.clear();
        strFileList.addAll(setString);

        /**
         * сортируем
         */
        strFileList.sort(String::compareToIgnoreCase);

        writeToFile(strFileList, "src\\lesson06\\task01\\output.txt");
    }

    /**
     * читаем файл
     *
     * @param txtFileName - имя файла
     * @return - возвращаем ArrayList из строчек
     */
    private static List<String> readFromFile(String txtFileName) {
        List<String> result = new ArrayList<>();

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFileName)))) {
            String lineFromFile = null;

            while ((lineFromFile = buffReader.readLine()) != null) {
                for (String string : lineFromFile.split("[\\p{P} \\t\\n\\r]")) {
                    result.add(string);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    /**
     * пишем в файл
     *
     * @param listString - что пишем (лист слов)
     * @param fileName   - куда пишем (файл)
     */
    private static void writeToFile(List<String> listString, String fileName) {
        try (BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {

            String upperWord = "";
            for (int i = 0; i < listString.size(); i++) {
                /**
                 * если разница только в регистре, не берем элемент
                 */
                if (!upperWord.equalsIgnoreCase(listString.get(i))) {
                    buffWriter.write(listString.get(i));
                    buffWriter.newLine();
                    buffWriter.flush();
                }
                upperWord = listString.get(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

