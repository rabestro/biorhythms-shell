package lv.id.jc.biorhythms.cli.printer;

import lv.id.jc.biorhythms.cli.model.Context;
import lv.id.jc.biorhythms.cli.model.ZodiacSign;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.Locale;

import static lv.id.jc.biorhythms.cli.formatter.DateFormatter.ORDINAL_SHORT_DATE;

@Component("zodiacSignPrinter")
public class ZodiacSignPrinter implements Printer<Context> {
    private static final String TEMPLATE = """
            %nZodiac Sign: %1$s
                 Symbol: %2$s
                  Dates: %3$s - %4$s
              Lucky Day: %5$tA%n""";

    @Override
    public String print(Context object, Locale locale) {
        final var zodiacSign = object.getBirthday().query(ZodiacSign::from);
        final var output = new StringBuilder(String.format(TEMPLATE,
                zodiacSign,
                zodiacSign.getSymbol(),
                ORDINAL_SHORT_DATE.format(zodiacSign.getStart()),
                ORDINAL_SHORT_DATE.format(zodiacSign.getEnd()),
                zodiacSign.getLuckyDay()));

        if (zodiacSign.isLuckyDay(object.getDate())) {
            output.append(String.format("%n  Today is a lucky day for %s!%n", zodiacSign));
        }
        return output.toString();
    }
}
