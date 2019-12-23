package lesson05.task01;

import java.lang.Exception;

/**
 * класс для пользовательских исключений
 */
public class MyException extends Exception {
    private String errorMessage;

    public MyException(String message) {
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
