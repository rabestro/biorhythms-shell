package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.model.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Printer;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@ShellComponent
public class ReportsCommands {

    @Autowired
    private Context context;

    @Autowired
    private Printer<Period> periodPrinter;

    private static final String AGE_REPORT = """
              Age Report%n
              Birthday: %tA, %<tB %<td, %<tY
                 Today: %tA, %<tB %<td, %<tY
                  Days: %,d
                   Age: %s
            """;

    @ShellMethod(value = "set birthday of the person", key = {"birthday", "bd"})
    public LocalDate birthday(@Past LocalDate birthday) {
        context.setBirthday(birthday);
        return birthday;
    }

    @ShellMethod(value = "prints age info", key = {"age", "age info"})
    public String ageInfo() {
        return String.format(AGE_REPORT,
                context.getBirthday(),
                context.getDate(),
                ChronoUnit.DAYS.between(context.getBirthday(), context.getDate()),
                periodPrinter.print(Period.between(context.getBirthday(), context.getDate()), Locale.ENGLISH)
        );
    }

    @ShellMethod("prints daily biorhythms report")
    public void dailyReport() {

    }
}
