package com.joshkevingeoff.evolearn;

/**
 * Created by josh on 4/10/15.
 */
public enum OperationEnum {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDE("/");

    private final String text;

    /**
     * @param text
     */
    private OperationEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }

}
