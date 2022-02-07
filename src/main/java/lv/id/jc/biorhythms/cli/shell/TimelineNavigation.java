package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.shell.model.Context;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.time.Month;

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
    public void minusMonths(int months) {
        context().setDate(context().getDate().minusMonths(months));
    }

    @ShellMethod(value = "increase report date by months", key = {"plus months", "+m"})
    public void plusMonths(int months) {
        context().setDate(context().getDate().plusMonths(months));
    }

    @ShellMethod(value = "decrease report date by months", key = {"minus years", "-y"})
    public void minusYears(int years) {
        context().setDate(context().getDate().minusYears(years));
    }

    @ShellMethod(value = "increase report date by months", key = {"plus years", "+y"})
    public void plusYears(int years) {
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
}
