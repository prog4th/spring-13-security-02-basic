package kr.ac.kumoh.s20260000.spring13security02basic

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
// TODO: 실행 결과 확인 후에는 주석 처리할 것
@EnableWebSecurity(debug = true)
class SecurityConfig {

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun userDetailsService() = InMemoryUserDetailsManager()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            csrf { disable() } // 테스트 편의를 위해 disable
            authorizeHttpRequests {
//                listOf(
//                    "/",
//                    "/signup.html",
//                    "/signup",
//                    "/login",
//                ).forEach {
//                    authorize(it, permitAll)
//                }
                authorize("/", permitAll)
                authorize("/signup.html", permitAll)
                authorize("/signup", permitAll)
                authorize("/login", permitAll)
                authorize(anyRequest, authenticated)
            }
            formLogin {
                defaultSuccessUrl("/hello", true)
            }
        }

        return http.build()
    }
}