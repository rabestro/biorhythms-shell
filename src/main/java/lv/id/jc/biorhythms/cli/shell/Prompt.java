package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.model.DateInfo;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public record Prompt(DateInfo dateInfo) implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(dateInfo().getDate().toString() + "> ",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
