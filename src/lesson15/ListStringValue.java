package lesson15;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * класс для формирования списка значений для таблицы
 */
public class ListStringValue {

    static final Random r = new Random();
    public static final Integer intnumber = 100;    // диапазон случайного числа
    public static final Integer countchar = 15;     // длина случайного слова
    public static final Integer yearstart = 1950;   // год рождения, диапазон начала
    public static final Integer yearend = 2000;     // год рождения, диапазон конца

    /**
     * функция создания листа значений для таблицы
     *
     * @param stringType
     * @return
     */
    public static List<String> listStringValue(List<String> stringType, String table) {
        List<String> stringValue = new ArrayList<>();

        for (int i = 0; i < stringType.size(); i++) {
            if (stringType.get(i).equals("int")) {
                stringValue.add(Integer.toString(r.nextInt(intnumber)));
            }

            if (stringType.get(i).equals("str")) {
                stringValue.add(randomCreateWord(r.nextInt(countchar)));
            }

            if (stringType.get(i).equals("dat")) {
                stringValue.add(randomCreateDate(yearstart, yearend));
            }
        }

        return stringValue;
    }

    /**
     * создаем случайное слово
     *
     * @param wordlength - длина слова
     * @return
     */
    public static String randomCreateWord(int wordlength) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder word = new StringBuilder();

        word.setLength(0);
        word.append(Character.toUpperCase(alphabet.charAt(r.nextInt(alphabet.length()))));

        for (int i = 0; i < wordlength + 1; i++) {
            word.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return word.toString();
    }

    /**
     * создаем случайную дату рождения
     *
     * @param start
     * @param end
     * @return
     */
    public static String randomCreateDate(int start, int end) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(start, end);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return gc.get(gc.YEAR) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.DAY_OF_MONTH);
    }

    /**
     * случайное число в диапазоне
     *
     * @param start
     * @param end
     * @return
     */
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}