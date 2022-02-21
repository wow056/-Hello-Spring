package com.hello.hellospring

import com.hello.hellospring.repository.MemberRepository
import com.hello.hellospring.repository.MemoryMemberRepository
import com.hello.hellospring.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig {

    @Bean
    fun memberRepository(): MemberRepository {
        return MemoryMemberRepository()
    }
}