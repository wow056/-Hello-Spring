package com.hello.hellospring

import com.hello.hellospring.repository.*
import com.hello.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.sql.DataSource

@Configuration
class SpringConfig @Autowired constructor(
    private val memberRepository: MemberRepository
) {
    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository)
    }
//    @Bean
//    fun memberRepository(): MemberRepository {
//        return JpaMemberRepository(entityManager)
//    }
}