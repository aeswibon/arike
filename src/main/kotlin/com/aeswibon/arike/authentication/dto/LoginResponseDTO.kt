package com.aeswibon.arike.authentication.dto

import com.aeswibon.arike.shared.dto.IDTO

data class LoginResponseDTO(
  val token: String,
  val refreshToken: String,
) : IDTO
