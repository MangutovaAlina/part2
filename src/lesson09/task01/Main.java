package lesson09.task01;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * public interface Worker {
 * <p>
 * void doWork();
 * <p>
 * }
 * <p>
 * Необходимо написать программу, выполняющую следующее:
 * <p>
 * Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * Полученный файл подгружается в программу с помощью кастомного загрузчика
 * Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, Exception {
        /** получаем путь до нашего класса
         */
        Path path = Paths.get("SomeClass.java");
        WriteSomeClass writeSomeClass = new WriteSomeClass();

        /** считываем с клавиатуры код и заносим его в SomeClass.java
         */
        writeSomeClass.readWriteSomeClass(path);

        /** компилим с помощью JavaCompiler исходник SomeClassюофмф
         */
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null, null, null, path.toAbsolutePath().toString());

        /** создаем экземпляр класса нашего лоадера SomeClassLoader
         */
        SomeClassLoader classLoader = new SomeClassLoader();

        /** загружаем наш класс SomeClass и вызываем его метод doWork()
         */
        Class<?> loadSomeClass = classLoader.loadClass("SomeClass");
        Worker interfaceWorker = (Worker) loadSomeClass.newInstance();  // экземпляр Worker
        interfaceWorker.doWork();
    }
}

