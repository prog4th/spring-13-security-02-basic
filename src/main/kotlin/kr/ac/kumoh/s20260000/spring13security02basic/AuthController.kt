package kr.ac.kumoh.s20260000.spring13security02basic

import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val userDetailsService: InMemoryUserDetailsManager,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/signup")
    fun signup(
        @RequestParam username: String,
        @RequestParam password: String,
    ): String {
        if (userDetailsService.userExists(username)) {
            return "이미 존재하는 아이디입니다."
        }

        val password = passwordEncoder.encode(password)
        println("$username: $password")

        val newUser = User.withUsername(username)
            .password(password)
            .roles("USER")
            .build()

        userDetailsService.createUser(newUser)

        return "회원가입 성공! <a href='/login'>로그인하러 가기</a>"
    }
}