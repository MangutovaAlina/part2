package lesson05.task01;

/**
 * класс Pet c полями
 * id - идентификатор
 * petName - имя питомца
 * petOwner - хозяин питомца
 * petWeight - вес питомца
 */
public class Pet {
    public int petId;
    public String petName;
    public Person petOwner;
    public double petWeight;

    /**
     * конструкторы класса Pet
     *
     * @param petId
     * @param petName
     * @param petOwner
     * @param petWeight
     */
    public Pet(int petId, String petName, Person petOwner, double petWeight) {
        this.petId = petId;
        this.petName = petName;
        this.petOwner = petOwner;
        this.petWeight = petWeight;
    }

    /**
     * геттеры и сеттеры
     */
    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Person getPetOwner() {
        return petOwner;
    }

    public void setPetOwner(Person petOwner) {
        this.petOwner = petOwner;
    }

    public double getPetWeight() {
        return petWeight;
    }

    public void setPetWeight(double petWeight) {
        this.petWeight = petWeight;
    }

    public String getPetOwnerName() {
        return petOwner.getName();
    }

    /**
     * функция сравнения двух питомцев по всем полям
     *
     * @param pet - с этим питомцем сравниваем
     * @return
     */
    public boolean petsEqual(Pet pet) {
        if (!this.petName.equals(pet.getPetName())) {
            return false;
        }
        if (!this.petOwner.equals(pet.getPetOwner())) {
            return false;
        }
        if (this.petWeight != pet.getPetWeight()) {
            return false;
        }
        return true;
    }

    /**
     * переопределяем строку вывода для класса Person
     *
     * @return строка вида Sex:  Age:  Name:
     */
    @Override
    public String toString() {

        return "petId = " + petId + " petName = " + petName + " petWeight = " + petWeight
                + " Person ( " + petOwner.getName() + ", " + petOwner.getAge() + ", " + petOwner.getSex() + " ) " + "\n";
    }
}
