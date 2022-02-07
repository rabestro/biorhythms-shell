package lv.id.jc.biorhythms.cli.shell.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("context")
public class Context {
    private LocalDate birthday;
    private LocalDate date = LocalDate.now();

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
