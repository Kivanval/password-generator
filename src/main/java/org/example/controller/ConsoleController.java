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


    @Override
    public void run() {
        validate();

        GeneratorRules rules = GeneratorRules.builder(minLength, maxLength)
                .alphabetic(alphabetic)
                .numeric(numeric)
                .special(special)
                .additional(additional)
                .build();

        Injector injector = Guice.createInjector(new BasicModule());
        PasswordGenerator passwordGenerator = injector.getInstance(PasswordGenerator.class);

        System.out.println(passwordGenerator.generate(rules));
    }

    private void validate() {
        if (minLength <= 0) {
            System.err.println("Minimal length must be > 0");
            System.exit(1);
        }
        if (minLength > maxLength) {
            System.err.println("Minimal length must be <= maximal length");
            System.exit(1);
        }
        if (!alphabetic && !numeric && !special && additional == null || additional.length() == 0) {
            System.err.println("Unable to create a password without symbols");
            System.exit(1);
        }
    }
}
