package ru.redrik.spring_securityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.redrik.spring_securityApp.models.User;
import ru.redrik.spring_securityApp.repositories.UserRepository;
import ru.redrik.spring_securityApp.security.UserDetailsImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceMy implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceMy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User is empty");
        }

        return new UserDetailsImpl(user.get());
    }
}
