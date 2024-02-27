package com.aeswibon.arike.shared.exception

import com.aeswibon.arike.shared.dto.ErrorResponseDTO
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import jakarta.validation.ValidationException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.orm.jpa.JpaSystemException
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

  @ExceptionHandler(ArikeException::class)
  fun handleOpsPortalBackendException(
    ex: ArikeException
  ): ResponseEntity<ErrorResponseDTO> {
    return ResponseEntity.badRequest()
      .body(
        ErrorResponseDTO(
          ex.errorCode?.name ?: ErrorCodes.AK00001.name,
          ex.errorCode?.exceptionName ?: ErrorCodes.AK00001.exceptionName,
          ex.reason ?: ex.errorCode?.message ?: ErrorCodes.AK00001.message
        )
      )
  }

  @ExceptionHandler(JpaSystemException::class)
  fun handleJpaSystemException(
    ex: JpaSystemException
  ): ResponseEntity<ErrorResponseDTO> {
    return ResponseEntity.badRequest()
      .body(
        ErrorResponseDTO(
          ErrorCodes.AK00001.name,
          ErrorCodes.AK00001.exceptionName,
          ExceptionUtils.getDbErrorFromException(ex)
        )
      )
  }

  @ExceptionHandler(Exception::class)
  fun handleException(ex: Exception): ResponseEntity<ErrorResponseDTO> {
    logger.error(ex)
    return ResponseEntity.badRequest()
      .body(
        ErrorResponseDTO(
          ErrorCodes.AK00001.name,
          ErrorCodes.AK00001.exceptionName,
          ErrorCodes.AK00001.message
        )
      )
  }

  override fun handleHttpMessageNotReadable(
    ex: HttpMessageNotReadableException,
    headers: HttpHeaders,
    status: HttpStatusCode,
    request: WebRequest
  ): ResponseEntity<Any> {
    val parameter: String
    if (ex.cause is MismatchedInputException) {
      parameter =
        (ex.cause as MismatchedInputException).message
          ?: (ex.cause as MismatchedInputException).localizedMessage.toString()
      return ResponseEntity.badRequest().body(
        ErrorResponseDTO(
          ErrorCodes.AK00002.name,
          ErrorCodes.AK00002.exceptionName,
          "$parameter is missing"
        )
      )
    }
    return ResponseEntity.badRequest().body(
      ErrorResponseDTO(
        ErrorCodes.AK00004.name,
        ErrorCodes.AK00004.exceptionName,
        ErrorCodes.AK00004.message
      )
    )
  }

  override fun handleMethodArgumentNotValid(
    ex: MethodArgumentNotValidException,
    headers: HttpHeaders,
    status: HttpStatusCode,
    request: WebRequest
  ): ResponseEntity<Any>? {
    val errors = ex.bindingResult.allErrors.map { error -> error.defaultMessage }.toList()
    return ResponseEntity.badRequest().body(
      ErrorResponseDTO(
        ErrorCodes.AK00002.name,
        ErrorCodes.AK00002.exceptionName,
        errors.joinToString()
      )
    )
  }

  @ExceptionHandler(ValidationException::class)
  fun handleValidationException(
    ex: ValidationException
  ): ResponseEntity<ErrorResponseDTO> {
    return ResponseEntity.badRequest().body(
      ErrorResponseDTO(
        ErrorCodes.AK00002.name,
        ErrorCodes.AK00002.exceptionName,
        ex.message ?: ex.localizedMessage
      )
    )
  }

  @ExceptionHandler(AccessDeniedException::class)
  fun handleAccessDeniedException(
    ex: AccessDeniedException
  ): ResponseEntity<ErrorResponseDTO> {
    return ResponseEntity.badRequest().body(
      ErrorResponseDTO(
        ErrorCodes.AK10001.name,
        ErrorCodes.AK10001.exceptionName,
        ex.message ?: ex.localizedMessage
      )
    )
  }
}
