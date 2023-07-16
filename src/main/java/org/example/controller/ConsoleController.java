package org.example.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.generate.GeneratorRules;
import org.example.generate.PasswordGenerator;
import org.example.inject.BasicModule;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "passgen", mixinStandardHelpOptions = true)
public class ConsoleController implements Runnable {

    @Option(names = "--min", description = "Minimal length", required = true)
    private int minLength;

    @Option(names = "--max", description = "Maximal length", required = true)
    private int maxLength;

    @Option(names = {"-a", "--alpha"}, description = "Use alphabetic symbols")
    private boolean alphabetic;

    @Option(names = {"-n", "--num"}, description = "Use numeric symbols")
    private boolean numeric;

    @Option(names = {"-s", "--spec"}, description = "Use special symbols")
    private boolean special;

    @Option(names = "--add", description = "Use additional symbols")
    private String additional;

    @Option(names = {"-c", "--count"}, description = "Password count")
    private int count = 1;


    @Override
    public void run() {
        validate();

        GeneratorRules.GeneratorRulesBuilder rulesBuilder = GeneratorRules.builder()
                .minLength(minLength)
                .maxLength(maxLength)
                .alphabetic(alphabetic)
                .numeric(numeric)
                .special(special);
        if(additional != null) {
            rulesBuilder.additional(additional.toCharArray());
        }
        GeneratorRules rules = rulesBuilder.build();

        Injector injector = Guice.createInjector(new BasicModule());
        PasswordGenerator passwordGenerator = injector.getInstance(PasswordGenerator.class);

        String[] passwords = new String[count];
        for(int i = 0; i < count; ++i) {
            passwords[i] = passwordGenerator.generate(rules);
        }

        String result = String.join("\n", passwords);
        System.out.println(result);
    }

    private void validate() {
        if(count < 1) {
            System.err.println("Count must be > 0");
            System.exit(1);
        }
        if (minLength <= 0) {
            System.err.println("Minimal length must be > 0");
            System.exit(1);
        }
        if (minLength > maxLength) {
            System.err.println("Minimal length must be <= maximal length");
            System.exit(1);
        }
        if (!alphabetic && !numeric && !special && (additional == null || additional.length() == 0)) {
            System.err.println("Unable to create a password without symbols");
            System.exit(1);
        }
    }
}
