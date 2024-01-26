package com.control.service;

import com.control.entity.User;
import com.control.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
@Service
public class UserService implements UserDetailsService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }
    public String addUser(User user){
        User user1=new User(encoder.encode(user.getPassword()),user.getUserName());
        userRepository.save(user1);
        return "saved";
    }
    public String updateUser(User user){
        User user1 = userRepository.findById(user.getId()).orElse(null);
        if (user1 !=null){
            user1.setUserName(user.getUserName());
            user1.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user1);
            return "updated";
        }
        return "dont found";
    }

    public String deleteUser(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            if (user.getEmployee() != null){
                userRepository.deleteById(id);
                return "deleted";
            }
            return "this bind to employee";
        }
        return "dont found";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserDatial");
        User user=userRepository.findByUserName(username);
        SimpleGrantedAuthority newAuthority = new SimpleGrantedAuthority("ROLE_"+user.getEmployee().getRole().name());
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                Collections.singleton(newAuthority)
        );
    }
}
