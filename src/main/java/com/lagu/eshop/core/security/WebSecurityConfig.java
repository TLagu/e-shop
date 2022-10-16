package com.lagu.eshop.core.security;

import com.lagu.eshop.module.user.repository.UserRepository;
import com.lagu.eshop.module.user.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * Web security configuration
 *
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepo;
    private final AuthenticationSuccessHandler successHandler;

    public WebSecurityConfig(UserRepository userRepo, AuthenticationSuccessHandler successHandler) {
        this.userRepo = userRepo;
        this.successHandler = successHandler;
    }

    /**
     * User detail service
     *
     * @return Customization user detail service
     * @since 1.0
     */
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(userRepo);
    }

    /**
     * Password encryption
     *
     * @return Encrypted password
     * @since 1.0
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication
     *
     * @return Authentication provider
     * @since 1.0
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Setting authentication provider
     *
     * @param auth Authentication builder manager
     * @throws Exception
     * @since 1.0
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Authentication configuration
     *
     * @param http HTTP security
     * @throws Exception
     * @since 1.0
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**")
                .hasAuthority("ADMIN")
                .antMatchers("/cart/**", "/order/**", "/account/**")
                .authenticated()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .successHandler(successHandler)
                .failureUrl("/login?error")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/shop")
                .permitAll();
    }

}