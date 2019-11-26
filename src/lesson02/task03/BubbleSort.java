package lesson02.task03;

import java.util.List;
import java.util.Comparator;

/**
 * сортировка методом "пузырька" (BubbleSort)
 */

public class BubbleSort implements SortInterface {
    BubbleSort() {
    }

    @Override
    public void sort(List<Person> p) {
        boolean bs = false;
        Person tmp;
        /** внешний цикл, который будет выполняться, пока лист не отсортирован весь */
        while (!bs) {
            bs = true;
            /** внутренний цикл прохода по всему листу */
            for (int i = 0; i < p.size() - 1; i++) {
                Person p1 = p.get(i);
                Person p2 = p.get(i + 1);
                /** сравниваем элемент i с i+1
                 *  если элемент i должен идти за i+1, удалем его и помешаем за сдвинувшимся на место i элементом i+1 */

                Comparator<Person> pcomp = new PersonComporator();
                int pi = pcomp.compare(p2, p1);

                if (pi == -1) {
                    bs = false;

                    tmp = p1;
                    p.remove(i);
                    p.add(i + 1, tmp);
                }
            }
        }
    }
}
