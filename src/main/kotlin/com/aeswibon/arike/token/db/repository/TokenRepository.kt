package com.aeswibon.arike.token.db.repository

import com.aeswibon.arike.token.db.entity.Token
import com.aeswibon.arike.token.db.query.TokenQueries
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional
import java.util.UUID

interface TokenRepository: JpaRepository<Token, Long> {
    @Query(TokenQueries.TOKEN_FETCH)
    fun findAllValidTokenByUser(uuid: UUID): List<Token>

    fun findByToken(token: String): Optional<Token>
}
