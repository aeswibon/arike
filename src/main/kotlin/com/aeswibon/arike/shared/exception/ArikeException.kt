package com.aeswibon.arike.shared.exception

open class ArikeException(
  open val errorCode: ErrorCodes? = ErrorCodes.AK00001,
  open val exception: Throwable? = null,
  open val reason: String? = null
) : RuntimeException("${errorCode?.exceptionName}: ${reason ?: errorCode?.message}", exception)
