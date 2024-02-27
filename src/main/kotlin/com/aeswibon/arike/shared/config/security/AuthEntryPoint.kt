package com.aeswibon.arike.shared.config.security

import com.aeswibon.arike.shared.dto.ErrorResponseDTO
import com.aeswibon.arike.shared.exception.ErrorCodes
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthEntryPoint(
  private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {
  private val logger = LoggerFactory.getLogger(this.javaClass)
  override fun commence(
    request: HttpServletRequest,
    response: HttpServletResponse,
    authException: AuthenticationException
  ) {
    val errorResponse = ErrorResponseDTO(
      ErrorCodes.AK10001.name,
      ErrorCodes.AK10001.exceptionName,
      ErrorCodes.AK10001.message
    )
    logger.error(ErrorCodes.AK10001.exceptionName, authException.message)
    response.sendError(HttpStatus.UNAUTHORIZED.value(), objectMapper.writeValueAsString(errorResponse))
  }
}
