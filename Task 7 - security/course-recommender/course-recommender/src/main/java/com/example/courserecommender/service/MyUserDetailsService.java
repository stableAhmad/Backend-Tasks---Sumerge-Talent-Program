package com.example.courserecommender.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*

--------------- MY NOTES -----------------
    goal of overriden method is : given a username, return a UserDetails object that contains user info
        if the user is found we return UserDetails object
        otherwise exception
    UserDetails : interface that provides user information to Spring Security

    HttpBasic : simple authentication scheme built into the HTTP protocol
        - client sends HTTP request with Authorization header that contains "Basic" followed by base64 encoded username:password
        - server decodes the header, extracts username and password, and validates them


    to send an authorized request via postman

        1) request type : PUT
        2) http://localhost:8080/1
        3) add header : x-validation-report : true 
        4) body -> raw -> {
            "name": "Updated Course via Postman authorized path",
            "description": "Updated description from Postman"
            }
        5) authorization tab -> basic Auth -> put hardcoded user : username : user
            Password : password
        6) send
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // since I haven't developed user endpoints yet, I 'll just hardcode a user
        // assume we have a user with username "user" and password "password"

        System.out.println("Trying to Authenticate " + username);
        if ("user".equals(username)) {
            return User.withUsername("user")
                    .password(passwordEncoder.encode("password"))
                    // .password("{noop}password")
                    // .password("password")
                    .roles("USER")
                    .build();
        } else {
            throw new UsernameNotFoundException("User wasn't not found");
        }
    }
}
