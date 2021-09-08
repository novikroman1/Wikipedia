package com.novikroman.service;

import com.novikroman.entity.Role;
import com.novikroman.entity.User;
import com.novikroman.jwt.JwtUtil;
import com.novikroman.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.singleton;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    private static final String USER_DISABLED = "USER_DISABLED";
    private static final String INVALID_CREDENTIALS = "INVALID_CREDENTIALS";
    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(username);
    }

    public String authenticate(final User currentUser) throws Exception {//
        User user = loadUserByUsername(currentUser.getName());
        authenticate(currentUser.getName(), currentUser.getPassword(), user);
        return jwtUtil.generateToken(user);
    }

    private void authenticate(final String username, final String password, User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password,
                    user.getAuthorities()));
        } catch (DisabledException e) {
            throw new Exception(USER_DISABLED, e);
        } catch (BadCredentialsException e) {
            throw new Exception(INVALID_CREDENTIALS, e);
        }
    }

    public User saveUser(final User currentUser) {
        currentUser.setPassword(bCryptPasswordEncoder.encode(currentUser.getPassword()));
//        final User user = fromDtoToEntity(currentUser);
        currentUser.setRoles(singleton(new Role(ROLE_USER)));
        return userRepository.save(currentUser);
    }
}
