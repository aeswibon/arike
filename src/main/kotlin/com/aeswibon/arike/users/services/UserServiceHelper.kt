package com.aeswibon.arike.users.services

import com.aeswibon.arike.users.db.repo.UserRepository
import org.springframework.stereotype.Component

@Component
class UserServiceHelper() {
  fun convertRoleToUpperCase(role: String): String {
    return role.trim().replace(" ", "_").uppercase()
  }
}
