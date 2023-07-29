package org.example.generate;

import com.google.inject.Guice;
import org.example.inject.BasicModule;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SymbolicallyPasswordGeneratorTest {

    PasswordGenerator passwordGenerator = Guice.createInjector(new BasicModule()).getInstance(PasswordGenerator.class);

    @Test
    void generateThrowsException() {
        GeneratorRules rules = GeneratorRules.builder().build(); //Given

        assertThrows(IllegalArgumentException.class,
                () -> passwordGenerator.generate(rules)); //When, Then
    }

    @Test
    void generateLengthWithinMinMax() {
        int minLength = 1;
        int maxLength = 5;
        GeneratorRules rules = GeneratorRules.builder()
                .minLength(minLength)
                .maxLength(maxLength)
                .alphabetic(true)
                .build(); //Given

        int passwordLength = passwordGenerator.generate(rules).length(); //When

        assertThat(passwordLength).isBetween(1, 5); //Then
    }
}