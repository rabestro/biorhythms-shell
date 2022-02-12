package lv.id.jc.biorhythms.cli.printer;

import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Period;
import java.util.Locale;

@Component
public class PeriodPrinter implements Printer<Period> {

    @Override
    public String print(Period object, Locale locale) {
        var years = format("year", object.getYears());
        var months = format("month", object.getMonths());
        var days = format("day", object.getDays());

        if ((years + months + days).isEmpty()) {
            return "just born";
        }

        var and = months.isEmpty() || days.isEmpty() ? " and " : ", ";
        var one = years.isEmpty() || (months.isEmpty() && days.isEmpty()) ? "" : and;
        var two = days.isEmpty() || months.isEmpty() ? "" : " and ";

        return years + one + months + two + days;
    }

    private String format(String name, int value) {
        return MessageFormat.format("{0, choice, 0#|1#one %s|1<{0} %<ss}".formatted(name), value);
    }

}
