package lv.id.jc.biorhythms.cli.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Period;
import java.util.regex.Pattern;

@Component
public class PeriodConverter implements Converter<String, Period> {
    @Override
    public Period convert(String source) {
        var period = source.replace("one", "1");
        return Period.of(Type.YEAR.parse(period), Type.MONTH.parse(period), Type.DAY.parse(period));
    }

    enum Type {
        YEAR("y(ears?)?"),
        MONTH("m(onths?)?"),
        DAY("d(ays?)?");
        private final Pattern pattern;

        Type(String pattern) {
            this.pattern = Pattern.compile("(\\d+) ?" + pattern, Pattern.CASE_INSENSITIVE);
        }

        int parse(String period) {
            var matcher = pattern.matcher(period);
            return matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;
        }
    }
}
