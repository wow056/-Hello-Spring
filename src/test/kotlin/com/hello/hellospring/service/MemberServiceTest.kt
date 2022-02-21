package com.hello.hellospring.service

import com.hello.hellospring.domain.Member
import com.hello.hellospring.repository.MemoryMemberRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

internal class MemberServiceTest {

    lateinit var memberRepository: MemoryMemberRepository
    lateinit var memberService: MemberService

    @BeforeEach
    fun beforeEach() {
        memberRepository = MemoryMemberRepository()
        memberService = MemberService(memberRepository)
    }

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "hello")

        // when
        val saveId = memberService.join(member)

        // then
        val findMember = memberService.findOne(saveId)
        assertThat(member.name).isEqualTo(findMember?.name)
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member(name = "spring")

        val member2 = Member(name = "spring")

        // when
        memberService.join(member1)
/*
        try {
            memberService.join(member2)
            fail(null as String?)
        } catch (e: IllegalStateException) {
            assertThat(e.message).isEqualTo("이미 존재하는 회원입니다.")
        }
*/
        val exception = assertThrows<IllegalStateException> {
            memberService.join(member2)
        }

        assertThat(exception.message).isEqualTo("이미 존재하는 회원입니다.")

        // then
    }

    @Test
    fun findMembers() {
    }

    @Test
    fun findOne() {
    }
}