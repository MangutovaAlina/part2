package lesson06.task01;

import java.io.*;
import java.util.ArrayList;

/**
 * Задание 1. Написать программу, читающую текстовый файл.
 *
 * Программа должна составлять отсортированный по алфавиту список слов, найденных в файле и сохранять его в файл-результат.
 * Найденные слова не должны повторяться, регистр не должен учитываться. Одно слово в разных падежах – это разные слова.
 */

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strFileList = new ArrayList<>();

        String path = "C:\\tmp_inn\\lesson06";
        strFileList = readFromFile(path + "\\input.txt");

        /** сортируем лист строчек
         */
        strFileList.sort(String::compareToIgnoreCase);

        /** удаляем повторяющиеся элементы из листа
         */
        for (int i = 0; i < strFileList.size() - 1; i++) {
            if (strFileList.get(i).equalsIgnoreCase(strFileList.get(i + 1))) {
                strFileList.remove(i + 1);
            }
        }
        writeToFile(strFileList, path + "\\output.txt");
    }

    /**
     * читаем файл
     *
     * @param txtFileName - имя файла
     * @return - возвращаем ArrayList из строчек
     */
    public static ArrayList<String> readFromFile(String txtFileName) {
        ArrayList<String> result = new ArrayList<>();

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(txtFileName)))) {
            String lineFromFile = null;

            while ((lineFromFile = buffReader.readLine()) != null) {
                result.add(lineFromFile);
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
     * @param listString - что пишем (лист строк)
     * @param fileName   - куда пишем (имя файла)
     */
    public static void writeToFile(ArrayList<String> listString, String fileName) {
        try (BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))) {

            for (int i = 0; i < listString.size(); i++) {
                buffWriter.write(listString.get(i));
                buffWriter.newLine();
                buffWriter.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

