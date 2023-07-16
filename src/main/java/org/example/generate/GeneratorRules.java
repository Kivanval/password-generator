package org.example.generate;

public class GeneratorRules {
    private int minLength;
    private int maxLength;
    private boolean alphabetic;
    private boolean numeric;
    private boolean special;
    private char[] additional;

    GeneratorRules(int minLength, int maxLength, boolean alphabetic, boolean numeric, boolean special, char[] additional) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.alphabetic = alphabetic;
        this.numeric = numeric;
        this.special = special;
        this.additional = additional;
    }

    public static GeneratorRulesBuilder builder(int minLength, int maxLength) {
        return new GeneratorRulesBuilder(minLength, maxLength);
    }

    public int getMinLength() {
        return this.minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean isAlphabetic() {
        return this.alphabetic;
    }

    public void setAlphabetic(boolean alphabetic) {
        this.alphabetic = alphabetic;
    }

    public boolean isNumeric() {
        return this.numeric;
    }

    public void setNumeric(boolean numeric) {
        this.numeric = numeric;
    }

    public boolean isSpecial() {
        return this.special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }

    public char[] getAdditional() {
        return this.additional;
    }

    public void setAdditional(char... additional) {
        this.additional = additional;
    }

    public static class GeneratorRulesBuilder {
        private static final char[] EMPTY_CHARS = {};
        private final int minLength;
        private final int maxLength;
        private boolean alphabetic;
        private boolean numeric;
        private boolean special;
        private char[] additional = EMPTY_CHARS;

        GeneratorRulesBuilder(int minLength, int maxLength) {
            this.minLength = minLength;
            this.maxLength = maxLength;
        }

        public GeneratorRulesBuilder alphabetic(boolean alphabetic) {
            this.alphabetic = alphabetic;
            return this;
        }

        public GeneratorRulesBuilder numeric(boolean numeric) {
            this.numeric = numeric;
            return this;
        }

        public GeneratorRulesBuilder special(boolean special) {
            this.special = special;
            return this;
        }

        public GeneratorRulesBuilder additional(char... additional) {
            if (additional != null) {
                this.additional = additional;
            }
            return this;
        }

        public GeneratorRulesBuilder additional(String additional) {
            if (additional != null) {
                this.additional = additional.toCharArray();
            }
            return this;
        }

        public GeneratorRules build() {
            return new GeneratorRules(this.minLength, this.maxLength, this.alphabetic, this.numeric, this.special, this.additional);
        }
    }
}
