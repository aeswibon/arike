package com.aeswibon.arike.authentication.service

import com.aeswibon.arike.authentication.dto.LoginRequestDTO
import com.aeswibon.arike.authentication.dto.LoginResponseDTO
import com.aeswibon.arike.shared.exception.ArikeException
import com.aeswibon.arike.shared.exception.ErrorCodes
import com.aeswibon.arike.shared.service.JwtService
import com.aeswibon.arike.token.db.entity.Token
import com.aeswibon.arike.token.db.repository.TokenRepository
import com.aeswibon.arike.users.db.repo.UserRepository
import com.aeswibon.arike.users.services.UserServiceImpl
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val jwtService: JwtService,
    private val userServiceImpl: UserServiceImpl
) : AuthenticationService {
    private fun saveToken(userDetails: UserDetails, jwtToken: String) {
        val user = userRepository.findByUsername(userDetails.username)
        if(user.isEmpty) {
            throw ArikeException(ErrorCodes.AK200001)
        }
        tokenRepository.save(
            Token(
                user = user.get(),
                token = jwtToken
            )
        )
    }

    private fun revokeAllUserToken(userDetails: UserDetails) {
        val user = userRepository.findByUsername(userDetails.username)
        if(user.isEmpty) {
            throw ArikeException(ErrorCodes.AK200001)
        }
        val userTokens = tokenRepository.findAllValidTokenByUser(user.get().uuid)

        if(userTokens.isEmpty()) return
        userTokens.forEach{
            it.expired = true
            it.revoked = true
        }
        tokenRepository.saveAll(userTokens)
    }

    override fun loginUser(data: LoginRequestDTO): LoginResponseDTO {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                data.username,
                data.password
            )
        )
        val user = userServiceImpl.loadUserByUsername(data.username)
        val token = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
        saveToken(user, token)
        return LoginResponseDTO(
            token = token,
            refreshToken = refreshToken
        )
    }

    override fun refreshToken(request: HttpServletRequest): LoginResponseDTO {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw ArikeException(ErrorCodes.AK10001)
        }
        val refreshToken = authHeader.substring(7)
        val username = jwtService.extractUsername(refreshToken) ?: throw ArikeException(ErrorCodes.AK10006)
        val user = userServiceImpl.loadUserByUsername(username)
        if(!jwtService.isTokenValid(refreshToken, user)) {
            throw ArikeException(ErrorCodes.AK10006)
        }
        val token = jwtService.generateToken(user)
        revokeAllUserToken(user)
        saveToken(user, token)
        return LoginResponseDTO(
            token = token,
            refreshToken = refreshToken
        )
    }
}