package lesson11;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Задание: Перевести одну из предыдущих работ на использование стримов и лямбда-выражений там, где это уместно (возможно, жертвуя производительностью)
 *
 *  Мне захотелось взять lesson06.task01 потому что с помощью стримов там все сильно упрощается
 *  distinct сам выкидывает дубликаты, sorted сортирует, никакой работы вручную
 */

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> strFileList = new ArrayList<>();

        String path = "C:\\tmp_inn\\lesson06";
        strFileList = readFromFile(path + "\\input.txt");
        writeToFile(strFileList, path + "\\output.txt");
    }

    /** читаем файл
     * @param txtFileName - имя файла
     * @return - возвращаем ArrayList из строчек
     */
    public static List<String> readFromFile(String txtFileName) {
        List<String> result = new ArrayList<>();

        /**  вот так просто все делается стримом! одна строчка буквально
         */
        try (Stream<String> streamFromFiles = Files.lines(Paths.get(txtFileName))) {
            result = streamFromFiles.distinct().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** пишем в файл
     * @param listString - что пишем (лист строк)
     * @param fileName - куда пишем (имя файла)
     */
    public static void writeToFile(List<String> listString, String fileName) {
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

