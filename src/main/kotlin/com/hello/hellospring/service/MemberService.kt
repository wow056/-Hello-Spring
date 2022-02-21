package com.hello.hellospring.service

import com.hello.hellospring.domain.Member
import com.hello.hellospring.repository.MemberRepository
import com.hello.hellospring.repository.MemoryMemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MemberService @Autowired constructor(
    private val memberRepository: MemberRepository
) {
    /*
    * 회원 가입
     */
    fun join(member: Member):Long {
        validateDuplicateMember(member) // 중복 회원 검증
        memberRepository.save(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        val result = memberRepository.findByName(member.name)
        if (result != null) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }
    /*
    * 전체 회원 조회
    * */
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }
}