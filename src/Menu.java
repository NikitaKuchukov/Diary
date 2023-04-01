import Repeatability.*;
import Task.*;
import exceptions.IncorrectArgumentException;
import exceptions.TaskNotFoundException;

import java.util.*;

public class Menu {
    private String tittle;
    private String description;
    private Type type;
    private int input;
    private Scanner scanner;
    private Calendar date;
    private final TaskService service = new TaskService();

    public void menu() {
        scanner = new Scanner(System.in);
        System.out.println("Привет!");
        try {
            while (true) {
                startMenu();
                switch (input) {
                    case 1:
                        createTaskMenu();
                        switch (input) {
                            case 1:
                                createTask();
                                service.add(new OneTimeTask(tittle, type, description));
                                break;
                            case 2:
                                createTask();
                                service.add(new DailyTask(tittle, type, description));
                                break;
                            case 3:
                                createTask();
                                service.add(new WeeklyTask(tittle, type, description));
                                break;
                            case 4:
                                createTask();
                                service.add(new MonthlyTask(tittle, type, description));
                                break;
                            case 5:
                                createTask();
                                service.add(new YearlyTask(tittle, type, description));
                                break;
                        }
                        break;
                    case 2:
                        getTaskList();
                        break;

                    case 3:
                        removeTask();
                        break;
                }
                if (input == 0) {
                    System.out.println("До свидания!");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод");
            menu();
        }
    }

    private void startMenu() {
        try {
            System.out.println("Выберите пункт меню" +
                    "\n 1 - Добавить задачу" +
                    "\n 2 - Получить список задач на день" +
                    "\n 3 - Удалить задачу по id" +
                    "\n 0 - Выход");
            input = scanner.nextInt();
            if (input > 3 || input < 0)
                throw new IncorrectArgumentException();
        } catch (IncorrectArgumentException e) {
            e.message();
            startMenu();
        }
    }

    private void createTaskMenu() {
        try {

            System.out.println("Выберите повторяемость задачи" +
                    "\n 1 - Однократная" +
                    "\n 2 - Ежедневная" +
                    "\n 3 - Еженедельная" +
                    "\n 4 - Ежемесячная" +
                    "\n 5 - Ежегодная");
            input = scanner.nextInt();
            if (input > 5 || input < 1)
                throw new IncorrectArgumentException();
        } catch (IncorrectArgumentException e) {
            e.message();
            createTaskMenu();
        }
    }

    private void createTask() {
        System.out.println("Введите название");
        try {
            do {
                tittle = scanner.nextLine();
            } while (tittle.isEmpty());
            do {
                try {
                    System.out.println("Выберите тип" +
                            "\n 1 - Рабочая" +
                            "\n 2 - Личная");
                    input = scanner.nextInt();
                    if (input != 1 || input != 2) throw new IncorrectArgumentException();
                    if (input == 1) type = Type.WORK;
                    if (input == 2) type = Type.PERSONAL;
                } catch (IncorrectArgumentException e) {
                    e.message();
                }
            } while (!(input == 1 || input == 2));

            System.out.println("Введите описание");
            do {
                description = scanner.nextLine();
            } while (description.isEmpty());
            System.out.println("Задача создана\n");
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод\n");
            createTask();
        }
    }

    private void getTaskList() {
        try {
            scanner = new Scanner(System.in);
            System.out.println("Введите число месяца");
            int dayOfMonth = scanner.nextInt();
            if (dayOfMonth <= 0 || dayOfMonth > 31) {
                throw new IncorrectArgumentException();
            }
            System.out.println("Введите порядковый номер месяца");
            int month = scanner.nextInt();
            if (month <= 0 || month > 12) {
                throw new IncorrectArgumentException();
            }
            System.out.println("Введите год в формате YYYY");
            int year = scanner.nextInt();
            date = new GregorianCalendar(year, month - 1, dayOfMonth);
            System.out.println("\n Список задач на " + dayOfMonth + "." + month + "." + year);
            System.out.println("----------------------------");
            Collection<Task> taskList = service.getAllByDate(date);
            for (Task task : taskList) {
                System.out.println(task);
            }
            System.out.println();
            if (taskList.isEmpty()) System.out.println("Задач нет\n");
        } catch (IncorrectArgumentException e) {
            e.message();
            getTaskList();
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод\n");
            getTaskList();
        }
    }

    private void removeTask() {
        scanner = new Scanner(System.in);
        System.out.println("Введите номер задачи для удаления");
        try {
            input = scanner.nextInt();
            service.remove(input);
            System.out.println("Задача №" + input + " удалена");
            System.out.println();
        } catch (TaskNotFoundException a) {
            System.out.println(a.message());
        } catch (InputMismatchException e) {
            System.out.println("Некорректный ввод\n");
            removeTask();
        }
    }
}








