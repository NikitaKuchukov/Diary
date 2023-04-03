package Repeatability;

import Task.*;

import java.util.Calendar;
import java.util.Date;

public class DailyTask extends Task {
    public DailyTask(String title, Type type, String description) {
        super(title, type, description);
        setRepeatability("Ежедневная");
    }

    @Override
    public boolean appearsIn(Calendar calendar) {
        return calendar.get(Calendar.DATE) >= getDateTime().get(Calendar.DATE)
                && calendar.get(Calendar.MONTH) >= getDateTime().get(Calendar.MONTH);
    }

    @Override
    public Date getNextDueDate() {
        getDateTime().add(Calendar.DATE, +1);
        return getDateTime().getTime();
    }
}
