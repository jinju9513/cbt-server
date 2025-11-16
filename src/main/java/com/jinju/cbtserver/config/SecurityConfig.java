package com.jinju.cbtserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 개발 단계에서는 CSRF 전체 비활성화 (나중에 JWT 붙일 때 다시 조정할 거야)
                .csrf(csrf -> csrf.disable())

                // H2 콘솔이 iframe 안에서 뜨니까 sameOrigin 허용
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )

                // URL 접근 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 모두 허용
                        .anyRequest().permitAll()                     // 나머지도 일단 모두 허용
                );

        // 기본 로그인 폼 같은 건 안 쓸 거라 별도 설정 안 함
        return http.build();
    }
}
