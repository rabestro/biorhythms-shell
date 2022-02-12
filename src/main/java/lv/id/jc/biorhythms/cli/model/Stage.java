package lv.id.jc.biorhythms.cli.model;

import java.util.Set;

public enum Stage {
    ZERO("+"),
    POSITIVE_UP("»"),
    POSITIVE_DOWN("«"),
    NEGATIVE_DOWN("«"),
    NEGATIVE_UP("»");

    private final String symbol;

    Stage(final String symbol) {
        this.symbol = symbol;
    }

    public static Stage of(final int period, final int days) {
        final var rest = days % period;

        if (rest == 0 || rest * 2 == period) {
            return ZERO;
        }

        return switch (rest * 4 / period) {
            case 0 -> POSITIVE_UP;
            case 1 -> POSITIVE_DOWN;
            case 2 -> NEGATIVE_DOWN;
            case 3 -> NEGATIVE_UP;
            default -> ZERO;
        };
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isPositive() {
        return Set.of(POSITIVE_UP, POSITIVE_DOWN).contains(this);
    }
}
