package com.jsf2184.springsecurityjpa;

import com.jsf2184.springsecurityjpa.models.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository _userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = _userRepository.findByUserName(s);
        User user = userEntity
                .map(MyUserDetailsService::createUser)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username: %s not found", s)));
        return user;
    }

    private static User createUser(UserEntity userEntity) {
        List<SimpleGrantedAuthority> authorities = generateAuthorities(userEntity.getRoles());

        User user = new User(userEntity.getUserName(),
                             userEntity.getPassword(),
                             authorities);
        return user;
    }


    public static List<SimpleGrantedAuthority> generateAuthorities(String roles) {
        if (StringUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        String[] parts = roles.split(",");
        List<SimpleGrantedAuthority> result = Arrays.stream(parts).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return result;
    }
}
