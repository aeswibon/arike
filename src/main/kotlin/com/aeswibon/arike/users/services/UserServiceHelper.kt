package com.aeswibon.arike.users.services

import org.springframework.stereotype.Component

@Component
class UserServiceHelper() {
  fun convertRoleToUpperCase(role: String): String {
    return role.trim().replace(" ", "_").uppercase()
  }
}
