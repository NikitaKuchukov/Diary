package Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public abstract class Task {
    private static int idGenerator;
    private final String title;
    private final Type type;
    private final int id;
    private final Calendar dateTime = new GregorianCalendar();
    private String repeatability;
    private final String description;

    public Task(String title, Type type, String description) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.id = ++idGenerator;
    }

    public int getId() {
        return id;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setRepeatability(String repeatability) {
        this.repeatability = repeatability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && title.equals(task.title) && type == task.type && dateTime.equals(task.dateTime) && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, id, dateTime, description);
    }

    @Override
    public String toString() {
        return "Задача № " + getId() +
                "\n Название: " + title +
                "\n Описание: " + description +
                "\n Тип: " + type +
                "\n Время создания: " + dateTime.getTime() +
                "\n Повторяемость: " + repeatability;
    }

    public abstract boolean appearsIn(Calendar calendar);

    public abstract Date getNextDueDate();
}
