package lv.id.jc.biorhythms.cli.shell;

import lv.id.jc.biorhythms.cli.shell.model.Context;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public record Prompt(Context context) implements PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return new AttributedString(context().getDate().toString() + "> ",
                AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}
