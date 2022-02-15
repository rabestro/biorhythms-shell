package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.model.DateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Printer;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.validation.constraints.Past;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@ShellComponent
public class ReportsCommands {

    private static final String AGE_REPORT = """
              %n  Birthday: %tA, %<tB %<td, %<tY
                 Today: %tA, %<tB %<td, %<tY
                  Days: %,d
                   Age: %s
            """;

    @Autowired
    private Printer<Period> periodPrinter;
    @Autowired
    private DateInfo info;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private Printer<DateInfo> zodiacSignPrinter;

    @ShellMethod(value = "Set birthday of the person.", key = {"birthday", "bd"})
    public LocalDate birthday(@Past LocalDate birthday) {
        info.setBirthday(birthday);
        return birthday;
    }

    @ShellMethod(value = "Print age info.")
    public String ageInfo() {
        return String.format(AGE_REPORT,
                info.getBirthday(),
                info.getDate(),
                ChronoUnit.DAYS.between(info.getBirthday(), info.getDate()),
                periodPrinter.print(Period.between(info.getBirthday(), info.getDate()), Locale.ENGLISH)
        );
    }

    @ShellMethod("Print information about zodiac sign.")
    public String zodiac() {
        return zodiacSignPrinter.print(info, Locale.ENGLISH);
    }

    @ShellMethod(value = "Print daily biorhythms report.", key = {"daily", "daily report"})
    public String dailyReport() {
        Context context = new Context();
        context.setVariable("name", "Spring");
        context.setVariable("url", "http://spring.io");
        context.setVariable("tags", "#framework #java #spring".split(" "));

        String text = templateEngine.process("text-template", context);
        return text;
    }

    @ShellMethod("Print age information using thymeleaf")
    public String age() {
        var context = new Context();
        context.setVariable("birthday", info.getBirthday());
        context.setVariable("date", info.getDate());
        context.setVariable("days", ChronoUnit.DAYS.between(info.getBirthday(), info.getDate()));
        context.setVariable("age", Period.between(info.getBirthday(), info.getDate()));
        return templateEngine.process("age", context);
    }

    @ShellMethod(value = "hello")
    public String hello() {
        StringWriter writer = new StringWriter();
        Context context = new Context();
        context.setVariable("variable1", "World");
        templateEngine.process("test", context, writer);
        return writer.toString();
    }
}
