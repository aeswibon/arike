package com.aeswibon.arike.users.dto

import com.aeswibon.arike.shared.dto.IDTO

data class UserMinimumDTO(
  val name: String,
  val email: String,
  val role: String,
) : IDTO
