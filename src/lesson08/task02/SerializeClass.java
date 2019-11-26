package lesson08.task02;

import java.io.*;

/**
 * класс в который входят два метода:
 * serialize(Object object, String file)
 * deSerialize(String file)
 */
public class SerializeClass {

    /**
     * запись объекта в файл
     *
     * @param object - объект, который помещаем в поток (файл)
     * @param file   - имя файла, в который записывается мой объект
     */
    public void serialize(Object object, String file) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    /**
     * чтение ранее сериализованных данных из потока
     *
     * @param file - файл, из которого будет читаться поток
     * @return - возвращаем объект
     */
    public Object deSerialize(String file) {
        Object object = null;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            try {
                object = (Object) objectInputStream.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }

        return object;
    }
}

