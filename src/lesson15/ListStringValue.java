package lesson15;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** класс для формирования списка значений для таблицы
 */
public class ListStringValue {

    static final Random r = new Random();

    /** функция создания листа значений для таблицы
     *  @param stringType
     *  @return
     */
    public static List<String> listStringValue(List<String> stringType, String table) {
        List<String> dat = new ArrayList<>();
        List<String> stringValue = new ArrayList<>();

        for (int i = 0; i < 63 ; i++) {
            dat.add(Integer.toString(1956 + i) + "-01-01");
        }

        for (int i = 0; i < stringType.size(); i++) {
            if (stringType.get(i).equals("int")) {
                stringValue.add(Integer.toString(r.nextInt(100)));
            }

            if (stringType.get(i).equals("str"))  {
                stringValue.add(randomCreateWord(r.nextInt(15)));
            }

            if (stringType.get(i).equals("dat"))  {
                stringValue.add(dat.get(r.nextInt(62)));
            }
        }

        return stringValue;
    }

    /** создаем случайное слово
     *  @param wordlength - длина слова
     *  @return
     */
    public static String randomCreateWord(int wordlength) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < wordlength+1; i++) {
            word.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return word.toString();
    }

}

