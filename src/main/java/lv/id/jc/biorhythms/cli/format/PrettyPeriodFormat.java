package lv.id.jc.biorhythms.cli.format;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;
import java.time.Period;

@Component
public class PrettyPeriodFormat extends Format {
    private static final MessageFormat YEARS =
            new MessageFormat("{0, choice, 0#|1#one year|1<{0} years}");
    private static final MessageFormat MONTHS =
            new MessageFormat("{0, choice, 0#|1#one month|1<{0} months}");
    private static final MessageFormat DAYS =
            new MessageFormat("{0, choice, 0#|1#one day|1<{0} days}");

    public static PrettyPeriodFormat getInstance() {
        return new PrettyPeriodFormat();
    }

    @Override
    public StringBuffer format(final Object obj, final @NotNull StringBuffer toAppendTo, final @NotNull FieldPosition pos) {
        if (obj instanceof Period) {
            return format((Period) obj, toAppendTo);
        }
        throw new IllegalArgumentException("Cannot format given Object (" + obj.getClass().getName() + ") as a Period");
    }

    @SuppressWarnings("squid:S1149")
    public StringBuffer format(final Period period, final StringBuffer toAppendTo) {
        final var years = YEARS.format(new Object[]{period.getYears()});
        final var months = MONTHS.format(new Object[]{period.getMonths()});
        final var days = DAYS.format(new Object[]{period.getDays()});

        if ((years + months + days).isEmpty()) {
            return toAppendTo.append("just born");
        }

        final var and = months.isEmpty() || days.isEmpty() ? " and " : ", ";
        final var one = years.isEmpty() || (months.isEmpty() && days.isEmpty()) ? "" : and;
        final var two = days.isEmpty() || months.isEmpty() ? "" : " and ";

        return toAppendTo
                .append(years)
                .append(one)
                .append(months)
                .append(two)
                .append(days);
    }

    @Override
    public Object parseObject(final String source, final @NotNull ParsePosition pos) {
        throw new UnsupportedOperationException();
    }
}