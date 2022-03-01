package com.hello.hellospring.repository

import com.hello.hellospring.domain.Member
import org.springframework.stereotype.Repository

class MemoryMemberRepository : MemberRepository {

    private val store = mutableMapOf<Long, Member>()
    private var sequence = 0L

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id!!] = member
        return member
    }

    override fun findById(id: Long): Member? {
        return store[id]
    }

    override fun findByName(name: String): Member? {
        return store.values.find {
            it.name == name
        }
    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }

    fun clearStore() {
        store.clear()
    }
}