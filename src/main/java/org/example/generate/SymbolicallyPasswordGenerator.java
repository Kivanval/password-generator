package org.example.generate;

import org.apache.commons.lang3.RandomUtils;

public class SymbolicallyPasswordGenerator implements PasswordGenerator {
    private static final String LETTERS = "_ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "1234567890";
    private static final String SPECIALS = "!#$%&()*+,-./:;<=>?@[\\]^{|}~";

    @Override
    public String generate(GeneratorRules rules) {
        if(rules.getMinLength() > rules.getMaxLength() || rules.getMaxLength() <= 0) {
            throw new IllegalArgumentException();
        }
        int length = RandomUtils.nextInt(rules.getMinLength(), rules.getMaxLength() + 1);
        StringBuilder mockup = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            mockup.append(peekChar(rules.isAlphabetic(), rules.isNumeric(), rules.isSpecial(), rules.getAdditional()));
        }
        return mockup.toString();
    }

    private char peekChar(boolean alphabetic, boolean numeric, boolean special, char[] chars) {
        if (alphabetic) {
            if (numeric) {
                if (special) {
                    int randomValue = RandomUtils.nextInt(0, chars == null ? 3 : 4);
                    switch (randomValue) {
                        case 0:
                            return LETTERS.charAt(RandomUtils.nextInt(0, LETTERS.length()));
                        case 1:
                            return NUMBERS.charAt(RandomUtils.nextInt(0, NUMBERS.length()));
                        case 2:
                            return SPECIALS.charAt(RandomUtils.nextInt(0, SPECIALS.length()));
                        case 3:
                            return chars[RandomUtils.nextInt(0, chars.length)];
                    }
                } else {
                    int randomValue = RandomUtils.nextInt(0, chars == null ? 2 : 3);
                    switch (randomValue) {
                        case 0:
                            return LETTERS.charAt(RandomUtils.nextInt(0, LETTERS.length()));
                        case 1:
                            return NUMBERS.charAt(RandomUtils.nextInt(0, NUMBERS.length()));
                        case 2:
                            return chars[RandomUtils.nextInt(0, chars.length)];
                    }
                }
            } else {
                int randomValue = RandomUtils.nextInt(0, chars == null ? 1 : 2);
                switch (randomValue) {
                    case 0:
                        return LETTERS.charAt(RandomUtils.nextInt(0, LETTERS.length()));
                    case 1:
                        return chars[RandomUtils.nextInt(0, chars.length)];
                }
            }
        } else if (numeric) {
            if (special) {
                int randomValue = RandomUtils.nextInt(0, chars == null ? 2 : 3);
                switch (randomValue) {
                    case 0:
                        return NUMBERS.charAt(RandomUtils.nextInt(0, NUMBERS.length()));
                    case 1:
                        return SPECIALS.charAt(RandomUtils.nextInt(0, SPECIALS.length()));
                    case 2:
                        return chars[RandomUtils.nextInt(0, chars.length)];
                }
            } else {
                int randomValue = RandomUtils.nextInt(0, chars == null ? 1 : 2);
                switch (randomValue) {
                    case 0:
                        return NUMBERS.charAt(RandomUtils.nextInt(0, NUMBERS.length()));
                    case 1:
                        return chars[RandomUtils.nextInt(0, chars.length)];
                }
            }

        } else {
            int randomValue = RandomUtils.nextInt(0, chars == null ? 1 : 2);
            switch (randomValue) {
                case 0:
                    return SPECIALS.charAt(RandomUtils.nextInt(0, SPECIALS.length()));
                case 1:
                    return chars[RandomUtils.nextInt(0, chars.length)];
            }
        }
        return 0;
    }
}
