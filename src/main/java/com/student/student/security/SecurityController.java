package com.student.student.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@EnableWebSecurity
public class SecurityController extends WebSecurityConfigurerAdapter {
    @Autowired
    MyAuthProvider myAuthProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(myAuthProvider);
//
    }
    //   @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //super.configure(auth);
//        InMemoryUserDetailsManager userDetailsService= new InMemoryUserDetailsManager();
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        UserDetails user = User.withUsername("user").password(passwordEncoder.
//                encode("pass")).authorities("read").build();
//        userDetailsService.createUser(user);
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic();
                http.authorizeRequests()
                .antMatchers("/api/student/" , "/api/student/**")
               .authenticated().and().formLogin()
                .and().csrf().disable();
//        http.httpBasic();
//        http.authorizeRequests().antMatchers("/api/student/*").authenticated().and().formLogin();
    }

}