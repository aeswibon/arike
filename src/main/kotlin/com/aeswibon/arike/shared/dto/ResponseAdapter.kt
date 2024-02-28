package com.aeswibon.arike.shared.dto

import org.springframework.http.ResponseEntity

fun createErrorResponse(
  errorCode: String,
  message: String,
  detailedMessage: String,
): ResponseEntity<ErrorResponseDTO> =
  ResponseEntity
    .ok()
    .body(ErrorResponseDTO(errorCode, message, detailedMessage))

fun createSuccessResponse(dto: IDTO): ResponseEntity<ResponseDTO> =
  ResponseEntity
    .ok()
    .body(SuccessResponseDTO(dto))
