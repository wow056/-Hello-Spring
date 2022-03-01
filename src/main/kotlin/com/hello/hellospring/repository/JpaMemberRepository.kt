package com.hello.hellospring.repository

import com.hello.hellospring.domain.Member
import javax.persistence.EntityManager

class JpaMemberRepository(private val em: EntityManager): MemberRepository {
    override fun save(member: Member): Member {
        em.persist(member)
        return member
    }

    override fun findById(id: Long): Member? {
        return em.find(Member::class.java, id)
    }

    override fun findByName(name: String): Member? {
        return em.createQuery("SELECT m from Member m WHERE m.name = :name", Member::class.java)
            .setParameter("name", name)
            .resultList
            .firstOrNull()
    }

    override fun findAll(): List<Member> {
        return em.createQuery("SELECT m from Member m", Member::class.java).resultList
    }
}