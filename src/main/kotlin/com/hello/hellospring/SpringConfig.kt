package com.hello.hellospring

import com.hello.hellospring.repository.JdbcMemberRepository
import com.hello.hellospring.repository.MemberRepository
import com.hello.hellospring.repository.MemoryMemberRepository
import com.hello.hellospring.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class SpringConfig @Autowired constructor() {

    @Bean
    fun memberRepository(dataSource: DataSource): MemberRepository {
        return JdbcMemberRepository(dataSource)
    }
}