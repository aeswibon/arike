package com.aeswibon.arike.authentication.dto

import com.aeswibon.arike.shared.dto.IDTO

data class LoginRequestDTO(
  val username: String,
  val password: String,
) : IDTO
