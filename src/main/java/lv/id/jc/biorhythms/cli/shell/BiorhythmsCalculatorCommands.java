package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.model.Context;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;

@ShellComponent
public record BiorhythmsCalculatorCommands(Context context) {

    @ShellMethod(value = "set birthday of the person", key = {"birthday", "bd"})
    public LocalDate birthday(LocalDate birthday) {
        context().setBirthday(birthday);
        return birthday;
    }

    @ShellMethod
    public String ageInfo() {
        return null;
    }
}
