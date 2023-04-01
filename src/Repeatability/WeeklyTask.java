package Repeatability;

import Task.*;

import java.util.Calendar;
import java.util.Date;

public class WeeklyTask extends Task {
    public WeeklyTask(String title, Type type, String description) {
        super(title, type, description);
        super.setRepeatability("Еженедельная");
    }

    @Override
    public boolean appearsIn(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_WEEK) == getDateTime().get(Calendar.DAY_OF_WEEK)
                && calendar.get(Calendar.DATE) >= getDateTime().get(Calendar.DATE)
                && calendar.get(Calendar.MONTH) >= getDateTime().get(Calendar.MONTH);
    }

    @Override
    public Date getNextDueDate() {
        getDateTime().add(Calendar.DATE, +7);
        return getDateTime().getTime();
    }
}
