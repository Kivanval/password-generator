package org.example.generate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneratorRules {
    private int minLength;
    private int maxLength;
    private boolean alphabetic;
    private boolean numeric;
    private boolean special;
    private char[] additional;
}
