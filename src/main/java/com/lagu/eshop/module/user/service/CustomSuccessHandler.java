package com.lagu.eshop.module.user.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * Custom user handler
 * @author Tomasz Łagowski
 * @version 1.0
 */
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * Behavior if successful login is successful
     * @since 1.0
     * @param request HTTP servlet request
     * @param response HTTP servlet response
     * @param authentication Authentication
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Optional<String> redirectUrl = authorities.stream()
                .filter(a -> a.getAuthority().equals("USER") || a.getAuthority().equals("ADMIN"))
                .map(a -> (a.getAuthority().equals("ADMIN")) ? "/admin" : "/shop")
                .findFirst();
        if (redirectUrl.isEmpty()) {
            throw new IllegalStateException();
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl.get());
    }

}
