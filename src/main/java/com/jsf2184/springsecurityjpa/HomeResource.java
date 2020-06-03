package com.jsf2184.springsecurityjpa;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home() {
        String username = getUsername();
        String res = String.format("<h1>Welcome %s</h1>", username);
        return res;
    }

    @GetMapping("/user")
    public String user() {
        printPrincipal();
        String username = getUsername();
        String res = String.format("<h1>Welcome User %s</h1>", username);
        return res;

    }

    @GetMapping("/admin")
    public String admin() {
        printPrincipal();
        String username = getUsername();
        String res = String.format("<h1>Welcome Admin %s</h1>", username);
        return res;
    }

    @GetMapping("/playful")
    public String playful() {
        printPrincipal();
        String username = getUsername();
        String res = String.format("<h1>Welcome Playful %s</h1>", username);
        return res;
    }

    public void printPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        System.out.printf("principal = %s", user);

    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        String username = user.getUsername();
        return username;
    }

}
