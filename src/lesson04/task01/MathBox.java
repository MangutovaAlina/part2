package lesson04.task01;

import java.util.ArrayList;
import java.util.List;

/**
 * класс MathBox
 * numberList - коллекция
 */
public class MathBox {
    private List<Number> numberList = new ArrayList<Number>();

    public MathBox(Number[] number) {
        for (int i = 0; i < number.length; i++) {
            this.numberList.add(number[i]);
        }
    }

    public void setNumbers(List<Number> numberList) {
        this.numberList = numberList;
    }

    public List<Number> getNumbers() {
        return numberList;
    }

    /**
     * метод summator возвращает сумму всех элементов коллекции
     *
     * @return число double - сумма
     */
    public double summator() {
        double summ = 0.0;

        for (int i = 0; i < this.numberList.size(); i++) {
            summ = summ + this.numberList.get(i).doubleValue();

        }
        return summ;
    }

    /**
     * метод splitter выполняет поочередное деление на делитель всех элементов коллекции
     *
     * @param divider - делитель
     */
    public void splitter(Number divider) {
        for (int i = 0; i < this.numberList.size(); i++) {
            numberList.set(i, this.numberList.get(i).doubleValue() / divider.doubleValue());
        }
    }

    /**
     * удаляет значение из коллекции
     *
     * @param valueList - значение, которое надо удалить
     */
    public void deleteList(Integer valueList) {
        for (int i = 0; i < this.numberList.size(); i++) {
            if (this.numberList.get(i).doubleValue() == valueList.intValue()) {
                numberList.remove(i);
            }
        }
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
        return numberList.equals(objectMathBox);
    }

    /**
     * переопределяем hashCode для класса MathBox
     *
     * @return возвращает хэшкод
     */
    @Override
    public int hashCode() {
        int z = 37;
        double result = 0.0;
        for (int i = 0; i < numberList.size(); i++) {
            result = result + (i * numberList.get(i).doubleValue());
        }
        return z * (int) result + this.hashCode();
    }

    /**
     * переопределяем строку вывода для класса MathBox
     *
     * @return возвращает элементы листа в столбик
     */
    @Override
    public String toString() {
        String strList = "";
        for (int i = 0; i < numberList.size(); i++) {
            strList = strList + numberList.get(i).toString() + "\n";
        }
        return strList;
    }
}
