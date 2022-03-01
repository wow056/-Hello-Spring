package com.hello.hellospring.repository

import com.hello.hellospring.domain.Member
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import javax.sql.DataSource

class JdbcTemplateMemberRepository(dataSource: DataSource) : MemberRepository {
    private val jdbcTemplate = JdbcTemplate(dataSource)

    override fun save(member: Member): Member {
        val jdbcInsert = SimpleJdbcInsert(jdbcTemplate)
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id")

        val parameters = mapOf<String, Any>(
            "name" to member.name
        )

        val key = jdbcInsert.executeAndReturnKey(MapSqlParameterSource(parameters))
        member.id = key.toLong()
        return member
    }

    override fun findById(id: Long): Member? {
        val result = jdbcTemplate.query("select * from member where id = ?", rowMapper, id)
        return result.firstOrNull()
    }

    override fun findByName(name: String): Member? {
        val result = jdbcTemplate.query("select * from member where name = ?", rowMapper, name)
        return result.firstOrNull()
    }

    override fun findAll(): List<Member> {
        return jdbcTemplate.query("select * from member", rowMapper)
    }

    private val rowMapper = RowMapper<Member> { rs, rowNum ->
        Member(
            name=rs.getString("name")
        ).apply {
            id = rs.getLong("id")
        }
    }
}