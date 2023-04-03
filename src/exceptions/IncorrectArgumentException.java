package exceptions;

import java.io.IOException;

public class IncorrectArgumentException extends IOException {
    public void message() {
        System.out.println("Некорректный ввод\n");
    }
}
