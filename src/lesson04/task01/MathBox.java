package lesson04.task01;

import java.util.*;

/**
 * класс MathBox
 * mathSet - коллекция
 */
public class MathBox {
    private HashSet mathSet = new HashSet<>();

    public MathBox(Number[] number) {
        mathSet = new HashSet<>(Arrays.asList(number));
    }

    public HashSet getNumbers() {
        return mathSet;
    }

    /**
     * метод summator возвращает сумму всех элементов коллекции
     *
     * @return число double - сумма
     */
    public Number summator() {
        Number summ = 0;
        Iterator<Number> iterator = this.getNumbers().iterator();
        while (iterator.hasNext()) {
            if (summ instanceof Double || iterator.next() instanceof Double) {
                summ = summ.doubleValue() + iterator.next().doubleValue();
            } else if (summ instanceof Float || iterator.next() instanceof Float) {
                summ = summ.floatValue() + iterator.next().floatValue();
            } else if (summ instanceof Long || iterator.next() instanceof Long) {
                summ = summ.longValue() + iterator.next().longValue();
            } else if (summ instanceof Integer || iterator.next() instanceof Integer) {
                summ = summ.intValue() + iterator.next().intValue();
            } else if (summ instanceof Short || iterator.next() instanceof Short) {
                summ = summ.shortValue() + iterator.next().shortValue();
            } else {
                summ = summ.byteValue() + iterator.next().byteValue();
            }
        }
        return summ;
    }

    /**
     * метод splitter выполняет поочередное деление на делитель всех элементов коллекции
     *
     * @param divider - делитель
     */
    public void splitter(Integer divider) {
        HashSet newMathSet = new HashSet<>();
        Iterator<Number> iterator = this.getNumbers().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() instanceof Double) {
                newMathSet.add(iterator.next().doubleValue() / divider);
            } else if (iterator.next() instanceof Float) {
                newMathSet.add(iterator.next().floatValue() / divider);
            } else if (iterator.next() instanceof Long) {
                newMathSet.add(iterator.next().longValue() / divider);
            } else if (iterator.next() instanceof Integer) {
                newMathSet.add(iterator.next().intValue() / divider);
            } else if (iterator.next() instanceof Short) {
                newMathSet.add(iterator.next().shortValue() / divider);
            } else {
                newMathSet.add(iterator.next().byteValue() / divider);
            }
        }

        this.mathSet = newMathSet;
    }

    /**
     * удаляет значение из коллекции
     *
     * @param valueList - значение, которое надо удалить
     */
    public void deleteList(Integer valueList) {
        this.mathSet.remove(valueList);
    }

    /**
     * переопределяем equals для класса MathBox
     *
     * @param object - входной объект, который сравниваем с существующим
     * @return - булевое значение
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        MathBox objectMathBox = (MathBox) object;
        return this.mathSet.equals(objectMathBox);
    }

    /**
     * переопределяем hashCode для класса MathBox
     *
     * @return возвращает хэшкод
     */
    @Override
    public int hashCode() {
        int hashMath = 0;
        for (Iterator<Number> iterator = this.getNumbers().iterator(); iterator.hasNext(); ) {
            hashMath = hashMath + iterator.next().hashCode();
        }
        return hashMath;
    }

    /**
     * переопределяем строку вывода для класса MathBox
     *
     * @return возвращает элементы
     */
    @Override
    public String toString() {
        return "элементы MathBox: " + mathSet + '}';
    }
}
