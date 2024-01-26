package com.control.config;

import com.control.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
public class SecurityApp {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtRequestFilter jwtRequestFilter;
    private final UserService userService;
    @Autowired
    public SecurityApp(BCryptPasswordEncoder bCryptPasswordEncoder,
                       JwtRequestFilter jwtRequestFilter, UserService userService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        this.jwtRequestFilter = jwtRequestFilter;
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpsecurity) throws Exception {
        httpsecurity
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/user/add").hasRole("CEO")
                .antMatchers(HttpMethod.DELETE,"/user/delete").hasRole("CEO")
                .antMatchers(HttpMethod.GET,"/user/show").hasAnyRole("CEO","HEAD_DEPARTMENT")
                .antMatchers(HttpMethod.GET,"/employee/came").hasAnyRole("CEO","HEAD_DEPARTMENT","EMPLOYEE")
                .antMatchers(HttpMethod.GET,"/employee/went").hasAnyRole("CEO","HEAD_DEPARTMENT","EMPLOYEE")
                .antMatchers("/user/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpsecurity.build();

    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
