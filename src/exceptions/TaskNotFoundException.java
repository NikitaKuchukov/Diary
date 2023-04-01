package exceptions;


public class TaskNotFoundException extends Exception {
    public String message() {
        return "Данной задачи нет\n";
    }

}
