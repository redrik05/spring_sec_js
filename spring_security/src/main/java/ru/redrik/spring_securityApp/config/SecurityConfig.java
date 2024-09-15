package ru.redrik.spring_securityApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import ru.redrik.spring_securityApp.security.AuthProviderImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthProviderImpl authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    //Настройка аутентификации
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(authProvider);
    }
}
