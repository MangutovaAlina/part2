package lesson05.task01;

import java.util.*;

/**
 * Разработать программу – картотеку домашних животных.
 * <p>
 * У каждого животного есть:
 * - уникальный идентификационный номер,
 * - кличка,
 * - хозяин (объект класс Person с полями – имя, возраст, пол),
 * - вес.
 * <p>
 * Реализовать:
 * <p>
 * метод добавления животного в общий список (учесть, что добавление дубликатов должно приводить к исключительной ситуации)
 * поиск животного по его кличке
 * изменение данных животного по его идентификатору
 * вывод на экран списка животных в отсортированном порядке. Поля для сортировки –  хозяин, кличка животного, вес.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        /** заводим хозяев  */
        Person person1 = new Person(20, "Vasilii", Sex.MAN);
        Person person2 = new Person(38, "Petr", Sex.MAN);
        Person person3 = new Person(11, "Svetlana", Sex.WOMAN);
        Person person4 = new Person(62, "Maria", Sex.WOMAN);

        /** заводим питомцев  */
        Pet cat1 = new Pet(11, "Stepa", person1, 3.2);
        Pet cat2 = new Pet(12, "Murka", person1, 2.2);
        Pet cat3 = new Pet(13, "Vasya", person2, 4.5);
        Pet dog1 = new Pet(21, "Polkan", person3, 10.3);
        Pet dog2 = new Pet(22, "Tarzan", person4, 25.1);
        Pet dog3 = new Pet(23, "Vasya", person4, 8.7);

        /**
         * заводим карточки на животных
         */
        CardPersonPet cardPersonPet = new CardPersonPet();

        try {
            cardPersonPet.addCardPet(cat1);
            cardPersonPet.addCardPet(cat2);
            cardPersonPet.addCardPet(cat3);
            cardPersonPet.addCardPet(dog1);
            cardPersonPet.addCardPet(dog2);
            cardPersonPet.addCardPet(dog3);
        } catch (MyException e) {
            System.out.println(e.getErrorMessage());
        }

        /** поиск животного по его кличке  */
        String petName = "Vasya";
        List<Pet> listPet = cardPersonPet.petSearchName(petName);

        if (listPet.size() != 0) {
            for (int i = 0; i < listPet.size(); i++) {
                System.out.println("Питомец с таким именем есть: " + listPet.get(i).toString());
            }
        } else {
            System.out.println("Питомец с именем " + petName + " не найден");
        }

        /** изменяем питомца по id */
        try {
            Pet cat4 = new Pet(13, "Sonya", person2, 3.5);
            cardPersonPet.updateCardPet(cat4);
        } catch (MyException e) {
            System.out.println(e.getErrorMessage());
        }

        /** печать картотеки
         */
        cardPersonPet.printCardPersonPetAll();

        /** сортируем картотеку по имени хозяина, имени питомца, весу */
        List<Pet> sortedList = cardPersonPet.sortCardPersonPetAll(
                Comparator.comparing(Pet::getPetOwnerName)
                        .thenComparing(Pet::getPetName)
                        .thenComparing(Pet::getPetWeight)
        );

        for (int i = 0; i < sortedList.size(); i++) {
            System.out.println(" " + sortedList.get(i).toString());
        }

        /** добавляем дубликат - получаем исключение  */
        try {
            cardPersonPet.addCardPet(cat3);
        } catch (MyException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}
