package com.aeswibon.arike.users.db.repo

import com.aeswibon.arike.users.db.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, String> {
  fun findByUsername(username: String): Optional<User>
}
