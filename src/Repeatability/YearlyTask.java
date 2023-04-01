package Repeatability;

import Task.*;

import java.util.Calendar;
import java.util.Date;

public class YearlyTask extends Task {
    public YearlyTask(String title, Type type, String description) {
        super(title, type, description);
        super.setRepeatability("Ежегодная");
    }

    @Override
    public boolean appearsIn(Calendar calendar) {
        return calendar.get(Calendar.DATE) == getDateTime().get(Calendar.DATE)
                && calendar.get(Calendar.MONTH) == getDateTime().get(Calendar.MONTH);
    }

    @Override
    public Date getNextDueDate() {
        getDateTime().add(Calendar.YEAR, +1);
        return getDateTime().getTime();
    }
}
