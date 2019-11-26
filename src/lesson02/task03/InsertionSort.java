package lesson02.task03;

import java.util.List;
import java.util.Comparator;

/**
 * сортировка методом "вставки" (Insertion Sort)
 */
public class InsertionSort implements SortInterface {
    InsertionSort() {
    }

    @Override
    public void sort(List<Person> p) {
        /** внешний цикл по всему листу
         */
        for (int j = 0; j < p.size(); j++) {
            /** берем элемент листа j
             */
            Person temp = p.get(j);
            int i = j - 1;

            for (; i >= 0; i--) {
                /** внутренний цикл идет по всем элементам перед j
                 */
                Person cur = p.get(i);
                /** сравниваем элемент j с элементом перед ним из внутреннего цикла
                 * если элемент j "меньше", сдвигаем большой элемент дальше по списку
                 */

                Comparator<Person> pcomp = new PersonComporator();
                int pi = pcomp.compare(temp, cur);

                if (pi == -1) {
                    p.remove(i);
                    p.add(i + 1, cur);
                } else {
                    /** если элемент j "больше" — останавливаемся
                     */
                    break;
                }
            }
        }
    }
}