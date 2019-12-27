package lesson06.task02;

import java.io.*;
import java.lang.Exception;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 * <p>
 * Предложение состоит из 1<=n1<=15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1<=n2<=15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1<=n3<=20 предложений. В конце абзаца стоит разрыв строки и перенос каретки.
 * Есть массив слов 1<=n4<=1000. Есть вероятность probability вхождения одного из слов этого массива в следующее предложение (1/probability).
 * <p>
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 */

public class Main {
    static final Random r = new Random();

    public static void main(String[] args) throws Exception {
        final int quantityCharInWord = 15;         // предельное количество символов в слове

        String[] words = new String[1000];
        String path = "src\\lesson06\\task02\\";   // путь до файлов
        int probability = 3;                       // вероятность попадания слова
        int countfile = 10;                        // кол-во файлов
        int size = 5000;                           // размер файла

        /** формируем массив слов 1000 длины от 1 до 15
         */
        for (int i = 0; i < 1000; i++) {
            words[i] = randomCreateWord(r.nextInt(quantityCharInWord));
        }
        getFiles(path, countfile, size, words, probability);
    }

    /**
     * создание файла
     *
     * @param path        путь
     * @param n           кол-во файлов
     * @param size        max размер файла
     * @param words       массив слов
     * @param probability вероятность вхождения последнего слова
     * @throws IOException
     */
    private static void getFiles(String path, int n, int size, String[] words, int probability) throws IOException {
        final int quantityWordInSentence = 15;     // предельное количество словв предложении
        final int quantitySentenceInParagraf = 20; // предельное количество предложений в абзаце

        String filename = "examp";
        Path dirpath = Paths.get(path);

        /** проверяем путь, есть ли директория
         *  если нет, создаем
         */
        if (!Files.isDirectory(dirpath)) {
            Files.createDirectory(dirpath);
        }

        /** генерим файлы
         */
        for (int j = 0; j < n; j++) {
            /** создаем файл c именем exampНомер.txt
             */
            try (OutputStream outputfile = Files.newOutputStream(dirpath.resolve(filename + j + ".txt"))) {

                /** заносим в него абзац
                 */
                int sizecurrent = 0;
                boolean sentprob = false;
                String strtofile = " ";

                /** пишем абзацы в файл, пока не переполнится
                 *  sizecurrent - текущий приблизительный размер
                 */
                while (sizecurrent < size) {
                    outputfile.write('\t');

                    for (int i = 1; i <= r.nextInt(quantitySentenceInParagraf); i++) {
                        sentprob = false;
                        if ((i % probability) == 0) {
                            sentprob = true;
                        }

                        /** заносим в абзац предложение
                         *  strtofile - предложение
                         */
                        strtofile = randomCreateSentences(r.nextInt(quantityWordInSentence), sentprob, words).toString();
                        byte[] bytefile = strtofile.getBytes();
                        outputfile.write(bytefile);

                        sizecurrent += bytefile.length;  // увеличиваем размер

                    }
                    outputfile.write('\r');
                    outputfile.write('\n');
                }
            }
        }

    }

    /**
     * создаем случайное слово
     *
     * @param wordlength - длина слова
     * @return - возвращается слово заданной длинны
     */
    private static String randomCreateWord(int wordlength) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < wordlength + 1; i++) {
            word.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }

        return word.toString();
    }

    /**
     * создаем предложение из случайных слов
     *
     * @param sentlength - длина предложения
     * @param sentprob   - флаг, нужно ли включать слово, возьмем - word[999]
     * @param words      - массив слов
     * @return - возвращаем предложение
     */
    private static StringBuilder randomCreateSentences(int sentlength, boolean sentprob, String[] words) {
        StringBuilder sentences = new StringBuilder();
        String sign = ".!?";

        if (!sentprob) {
            /** если вероятность вхождения слова в это предложение words[999] нулевая, то выбираем любое до него                        *
             */
            sentences.append(firstUpperCase(words[r.nextInt(998)]));
        } else {
            /** если по вероятности слово words[999] в предложение входит, то выбираем его                        *
             */
            sentences.append(firstUpperCase(words[999]));
        }

        for (int i = 1; i <= sentlength; i++) {
            if (r.nextInt(998)%6 == 0){ sentences.append(","); }
            sentences.append(" ");
            sentences.append(words[r.nextInt(998)]);
        }

        sentences.append(sign.charAt(r.nextInt(sign.length())) + " ");
        return sentences;
    }

    /**
     * функция, которая делает первую букву у слова заглавной
     *
     * @param word - слово
     * @return - слово с заглавной первой буквой
     */
    private static String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}

