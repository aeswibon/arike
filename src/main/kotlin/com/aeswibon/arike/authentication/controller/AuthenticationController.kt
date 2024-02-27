package com.aeswibon.arike.authentication.controller

import com.aeswibon.arike.authentication.dto.LoginRequestDTO
import com.aeswibon.arike.authentication.service.AuthenticationService
import com.aeswibon.arike.shared.dto.GenericRequest
import com.aeswibon.arike.shared.dto.ResponseDTO
import com.aeswibon.arike.shared.dto.createSuccessResponse
import com.aeswibon.arike.shared.utils.Routes
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.AUTH)
class AuthenticationController(
    private val authenticationService: AuthenticationService
) {
    @PostMapping("/login")
    fun loginUser(
        @RequestBody request: GenericRequest<LoginRequestDTO>,
    ): ResponseEntity<ResponseDTO> {
        val loginResponse = authenticationService.loginUser(request.data)
        return createSuccessResponse(loginResponse)
    }

    @PostMapping("/refresh-token")
    fun refreshToken(
        request: HttpServletRequest,
    ): ResponseEntity<ResponseDTO> {
        val refreshTokenResponse = authenticationService.refreshToken(request)
        return createSuccessResponse(refreshTokenResponse)
    }
}