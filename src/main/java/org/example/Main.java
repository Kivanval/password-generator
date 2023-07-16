package org.example;

import org.example.controller.ConsoleController;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        new CommandLine(new ConsoleController()).execute(args);
    }
}