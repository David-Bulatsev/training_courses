package com.lms_system.training_courses.security;


import com.lms_system.training_courses.entity.User;
import com.lms_system.training_courses.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByNickname(username);

        return user.map(AuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

//        return userRepository.findByNickname(username)
//                .map(user -> new User(
//                        user.getNickname(),
//                        user.getPassword(),
//                        List.of(new SimpleGrantedAuthority(user.getRole().getName()))))
//                .orElseThrow(() -> new UsernameNotFoundException("User nof found"));
    }
}
