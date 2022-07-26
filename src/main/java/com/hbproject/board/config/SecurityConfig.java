package com.hbproject.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/login*").permitAll()
                    // 게시판 내부 접근은 role적용
                    .antMatchers("/", "/posts").permitAll()
                    .antMatchers("/account/**").permitAll()
                    //css 못가져온다고 에러 메시지
                    //기본적으로 정적인 파일 까지도 보안 필터를 거치기 때문에 정적 파일에 대한 권한 허용을 설정해 주어야 한다.
                    .mvcMatchers("/css/**", "/js/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/loginPage")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/", true)
                    .and()
                .logout()
                    .logoutUrl("/logout")
                    .deleteCookies("JJESSIONID");
        return http.build();
    }
}
