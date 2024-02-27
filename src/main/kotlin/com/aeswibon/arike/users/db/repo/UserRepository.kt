package com.aeswibon.arike.users.db.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.aeswibon.arike.users.db.entity.User
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository: JpaRepository<User, String> {
    fun findByUsername(username: String): Optional<User>
}