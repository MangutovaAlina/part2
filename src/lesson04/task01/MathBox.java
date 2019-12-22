package lesson04.task01;

import java.util.*;
import java.util.UUID;

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
            Number value = iterator.next();
            if (summ instanceof Double || value instanceof Double) {
                summ = summ.doubleValue() + value.doubleValue();
            } else if (summ instanceof Float || value instanceof Float) {
                summ = summ.floatValue() + value.floatValue();
            } else if (summ instanceof Long || value instanceof Long) {
                summ = summ.longValue() + value.longValue();
            } else if (summ instanceof Integer || value instanceof Integer) {
                summ = summ.intValue() + value.intValue();
            } else if (summ instanceof Short || value instanceof Short) {
                summ = summ.shortValue() + value.shortValue();
            } else {
                summ = summ.byteValue() + value.byteValue();
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
            Number value = iterator.next();
                if (value instanceof Double) {
                    newMathSet.add(value.doubleValue() / divider);
                } else if (value instanceof Float) {
                    newMathSet.add(value.floatValue() / divider);
                } else if (value instanceof Long) {
                    newMathSet.add(value.longValue() / divider);
                } else if (value instanceof Integer) {
                    newMathSet.add(value.intValue() / divider);
                } else if (value instanceof Short) {
                    newMathSet.add(value.shortValue() / divider);
                } else {
                    newMathSet.add(value.byteValue() / divider);
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
        String uniqueID = UUID.randomUUID().toString();
        return uniqueID.hashCode();
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
