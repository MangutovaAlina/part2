package lesson08.task01;

import java.io.Serializable;

public class MyObject implements Serializable {
    private String stringField;
    private int intField;
    private double doubleField;
    private transient boolean booleanField; // исключаю это поле из сериализации

    /**
     * конструктор
     *
     * @param stringField
     * @param intField
     * @param doubleField
     * @param booleanField
     */
    public MyObject(String stringField, int intField, double doubleField, boolean booleanField) {
        this.stringField = stringField;
        this.intField = intField;
        this.doubleField = doubleField;
        this.booleanField = booleanField;
    }

    /**
     * геттеры и сеттеры
     */
    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public void setDoubleField(double doubleField) {
        this.doubleField = doubleField;
    }

    public boolean isBooleanField() {
        return booleanField;
    }

    public void setBooleanField(boolean booleanField) {
        this.booleanField = booleanField;
    }


    @Override
    public String toString() {
        return "MyObject: " + " stringField = " + stringField + ", intField = " + intField +
                ", doubleField = " + doubleField + ", booleanField =" + booleanField + "\n";
    }
}

