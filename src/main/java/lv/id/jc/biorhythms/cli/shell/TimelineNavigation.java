package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.model.Context;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

@ShellComponent
public record TimelineNavigation(Context context) {

    @ShellMethod(value = "decrease report date by days", key = {"minus days", "-d"})
    public void minusDays(int days) {
        context().setDate(context().getDate().minusDays(days));
    }

    @ShellMethod(value = "increase report date by days", key = {"plus days", "+d"})
    public void plusDays(int days) {
        context().setDate(context().getDate().plusDays(days));
    }

    @ShellMethod(value = "decrease report date by weeks", key = {"minus weeks", "-w"})
    public void minusWeeks(int weeks) {
        context().setDate(context().getDate().minusWeeks(weeks));
    }

    @ShellMethod(value = "increase report date by weeks", key = {"plus weeks", "+w"})
    public void plusWeeks(int weeks) {
        context().setDate(context().getDate().plusWeeks(weeks));
    }

    @ShellMethod(value = "decrease report date by months", key = {"minus months", "-m"})
    public void minusMonths(@Max(12_000) int months) {
        context().setDate(context().getDate().minusMonths(months));
    }

    @ShellMethod(value = "increase report date by months", key = {"plus months", "+m"})
    public void plusMonths(@Max(12_000) int months) {
        context().setDate(context().getDate().plusMonths(months));
    }

    @ShellMethod(value = "decrease report date by months", key = {"minus years", "-y"})
    public void minusYears(@Min(-1000) @Max(1000) int years) {
        context().setDate(context().getDate().minusYears(years));
    }

    @ShellMethod(value = "decrease report date by period", key = {"minus", "-"})
    public void minusPeriod(Period period) {
        context().setDate(context().getDate().minus(period));
    }

    @ShellMethod(value = "increase report date by period", key = {"plus", "+"})
    public void plusPeriod(Period period) {
        context().setDate(context().getDate().plus(period));
    }

    @ShellMethod(value = "increase report date by months", key = {"plus years", "+y"})
    public void plusYears(@Min(-1000) @Max(1000) int years) {
        context().setDate(context().getDate().plusYears(years));
    }

    @ShellMethod(value = "set report date equals today", key = {"today", "now"})
    public void today() {
        context().setDate(LocalDate.now());
    }

    @ShellMethod("set report month")
    public void setMonth(Month month) {
        context().setDate(context().getDate().withMonth(month.getValue()));
    }

    @ShellMethod("move report date to the next day of week")
    public void next(DayOfWeek dayOfWeek) {
        var adjuster = TemporalAdjusters.next(dayOfWeek);
        context().setDate(context().getDate().with(adjuster));
    }

    @ShellMethod("move report date to the next day of week")
    public void prev(DayOfWeek dayOfWeek) {
        var adjuster = TemporalAdjusters.previous(dayOfWeek);
        context().setDate(context().getDate().with(adjuster));
    }

    @ShellMethod("Period")
    public Period period(Period period) {
        return period;
    }
}
