package com.aeswibon.arike.shared.dto

data class ErrorResponseDTO(
  val errorCode: String,
  val message: String,
  val detailedMessage: String
) : ResponseDTO
