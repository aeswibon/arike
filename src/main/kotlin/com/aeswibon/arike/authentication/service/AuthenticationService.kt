package com.aeswibon.arike.authentication.service

import com.aeswibon.arike.authentication.dto.LoginRequestDTO
import com.aeswibon.arike.authentication.dto.LoginResponseDTO
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

interface AuthenticationService {
    fun loginUser(data: LoginRequestDTO): LoginResponseDTO
    fun refreshToken(request: HttpServletRequest): LoginResponseDTO
}