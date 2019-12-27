package lesson05.task01;

import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * создаем класс картотеки - коллекцию HashSet из классов Pet с методами:
 * - добавление питомца в картотеку
 * - поиск питомца в картотеке по всем полям для отслеживания дубликатов
 * - поиск питомца в картотеке по id
 * - поиск питомца в картотеке по имени
 * - изменение учетной карточки питомца
 */
public class CardPersonPet {
    public HashSet<Pet> cardsPet = new HashSet<>();

    public CardPersonPet() {
    }

    public CardPersonPet(HashSet<Pet> cardsPet) {
        this.cardsPet = cardsPet;
    }

    /**
     * добавление питомца в картотеку
     *
     * @param pet - питомец
     */
    public void addCardPet(Pet pet) throws MyException {
        Pet petfind = petSearchAll(pet);
        if (petfind.getPetId() != 0) {
            throw new MyException("Такое животное уже существует!");
        } else {
            cardsPet.add(pet);
        }
    }

    /**
     * апдейтим карточку питомца, заменяем найденное по id на поля petUpdate
     *
     * @param petUpdate - карточка питомца, на которую нужно заменить найденную
     */
    public void updateCardPet(Pet petUpdate) throws MyException {
        Pet petfind = petSearchId(petUpdate.petId);
        if (petfind.getPetId() != 0) {
            Pet pet = petSearchId(petUpdate.petId);
            pet.setPetName(petUpdate.getPetName());
            pet.setPetOwner(petUpdate.getPetOwner());
            pet.setPetWeight(petUpdate.getPetWeight());
        } else {
            throw new MyException("Питомец с id " + petUpdate.petId + " не найден!");
        }
    }

    /**
     * ищем питомца в картотеке по всем полям
     *
     * @param petInput - на вход подаем искомого питомца
     * @return - возвращаем true или false
     */
    private Pet petSearchAll(Pet petInput) {
        Person person = new Person(0, "", Sex.MAN);
        Pet petfind = new Pet(0, "", person, 0);
        Iterator iterator = cardsPet.iterator();
        while (iterator.hasNext()) {
            Pet pet = (Pet) iterator.next();
            if (pet.petsEqual(petInput)) {
                petfind = pet;
            }
        }
        return petfind;
    }

    /**
     * ищем питомца по id
     *
     * @param idPet - на вход подаем идентификатор питомца
     * @return - возвращаем true или false
     */
    private Pet petSearchId(int idPet) {
        Person person = new Person(0, "", Sex.MAN);
        Pet petfind = new Pet(0, "", person, 0);
        Iterator iterator = cardsPet.iterator();
        while (iterator.hasNext()) {
            Pet pet = (Pet) iterator.next();
            if (pet.getPetId() == idPet) {
                petfind = pet;
            }
        }
        return petfind;
    }

    /**
     * ищем питомца по имени
     *
     * @param namePet - на вход подаем имя питомца
     * @return возвращаем питомца или null
     */
    public List<Pet> petSearchName(String namePet) {
        List<Pet> listPet = new ArrayList<>();

        Iterator iterator = cardsPet.iterator();
        while (iterator.hasNext()) {
            Pet pet = (Pet) iterator.next();
            if (pet.getPetName() == namePet) {
                listPet.add(pet);
            }
        }
        return listPet;
    }

    /**
     * печать картотеки
     */
    public void printCardPersonPetAll() {
        System.out.println("Картотека:" + "\n");

        Iterator iterator = cardsPet.iterator();
        while (iterator.hasNext()) {
            Pet pet = (Pet) iterator.next();
            System.out.println(pet.toString());
        }
        System.out.println("---------------" + "\n");
    }

    /**
     * сортировка картотеки
     */
    public List<Pet> sortCardPersonPetAll(Comparator<Pet> comparator) {
        return cardsPet.stream().sorted(comparator).collect(Collectors.toList());
    }
}
