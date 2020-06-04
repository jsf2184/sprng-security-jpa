package com.jsf2184.springsecurityjpa;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MyUserDetailsServiceTests {

    @Test
    public void testGenerateAuthoritieszWithOneValue() {
        validateResults("ADMIN_ROLE", Collections.singletonList(new SimpleGrantedAuthority("ADMIN_ROLE")));
    }

    @Test
    public void testGenerateAuthoritieszWithTwoValues() {
        validateResults("ADMIN_ROLE,USER_ROLE", Arrays.asList(new SimpleGrantedAuthority("ADMIN_ROLE"),
                                                              new SimpleGrantedAuthority("USER_ROLE")));
    }

    @Test
    public void testGenerateAuthoritieszWithNoValues() {
        validateResults("", new ArrayList<>());
    }


    private static void validateResults(String input,
                                        List<SimpleGrantedAuthority> expected) {
        List<SimpleGrantedAuthority> actual = MyUserDetailsService.generateAuthorities(input);
        Assert.assertEquals(expected, actual);
    }

}
