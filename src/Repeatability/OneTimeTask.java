package Repeatability;

import Task.*;

import java.util.Calendar;
import java.util.Date;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, Type type, String description) {
        super(title, type, description);
        super.setRepeatability("Однократная");
    }

    @Override
    public boolean appearsIn(Calendar calendar) {
        return calendar.get(Calendar.DATE) >= getDateTime().get(Calendar.DATE)
                && calendar.get(Calendar.MONTH) >= getDateTime().get(Calendar.MONTH);
    }

    @Override
    public Date getNextDueDate() {
        System.out.println("Задача однократная");
        return null;
    }
}
