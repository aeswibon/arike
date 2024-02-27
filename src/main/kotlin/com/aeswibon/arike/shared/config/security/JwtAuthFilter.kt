package com.aeswibon.arike.shared.config.security

import com.aeswibon.arike.authentication.constants.AuthenticationConstants.AUTH_HEADER
import com.aeswibon.arike.shared.exception.ArikeException
import com.aeswibon.arike.shared.exception.ErrorCodes
import com.aeswibon.arike.shared.service.JwtService
import com.aeswibon.arike.users.services.UserServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val userDetailsService: UserServiceImpl
) : OncePerRequestFilter() {

    @Suppress("TooGenericExceptionCaught")
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val accessToken = getAuthAccessToken(request)
            if (accessToken != null) {
                val username = jwtService.extractUsername(accessToken) ?: throw ArikeException(ErrorCodes.AK10006)
                if (SecurityContextHolder.getContext().authentication == null) {
                    val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
                    if (jwtService.isTokenValid(accessToken, userDetails)) {
                        val authentication = UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.authorities
                        )
                        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                        SecurityContextHolder.getContext().authentication = authentication
                    }
                }
            }
        } catch (e: Exception) {
            logger.error(ErrorCodes.AK00001.exceptionName, e)
        }
        filterChain.doFilter(request, response)
    }

    private fun getAuthAccessToken(request: HttpServletRequest): String? {
        val headerAuth = request.getHeader(AUTH_HEADER)
        return if (headerAuth != null && headerAuth.startsWith(BEARER_PREFIX)) {
            headerAuth.substring(BEARER_PREFIX_LENGTH)
        } else {
            null
        }
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
        private const val BEARER_PREFIX_LENGTH = 7
    }
}