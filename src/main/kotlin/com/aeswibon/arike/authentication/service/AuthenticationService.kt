package com.aeswibon.arike.authentication.service

import com.aeswibon.arike.authentication.dto.LoginRequestDTO
import com.aeswibon.arike.authentication.dto.LoginResponseDTO
import jakarta.servlet.http.HttpServletRequest

interface AuthenticationService {
  fun loginUser(data: LoginRequestDTO): LoginResponseDTO

  fun refreshToken(request: HttpServletRequest): LoginResponseDTO
}
