package com.kemalkeskin.authentication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private  JwtAuthEntryPoint jwtAuthEntryPoint;

    private  CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService,JwtAuthEntryPoint jwtAuthEntryPoint) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtAuthEntryPoint=jwtAuthEntryPoint;
    }

    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws  Exception{
        http
                .csrf(configurer->configurer.disable())// CSRF korumasını devre dışı bırakır
                .exceptionHandling() //istisna hata yakalama ekledik
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .sessionManagement()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();
        return http.build();
    }

   /* @Bean
    public UserDetailsService detailsService(){
        UserDetails admin= User.builder().username("ADMİN").password("admin123").roles("ADMİN").build();
        return new InMemoryUserDetailsManager(admin);
    }*/

    // db -> user ve role repsoitory ->userdetailsservice akış şeması

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder  passwordEncoder(){
        //şifreleri veri tabanında normal düz metin şeklinde değil bcrypt şeklinde saklar
        return new BCryptPasswordEncoder();
    }
}
