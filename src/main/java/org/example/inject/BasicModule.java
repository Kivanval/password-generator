package org.example.inject;

import com.google.inject.AbstractModule;
import org.example.generate.PasswordGenerator;
import org.example.generate.SymbolicallyPasswordGenerator;

public class BasicModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PasswordGenerator.class).to(SymbolicallyPasswordGenerator.class);
    }
}
