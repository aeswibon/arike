package com.aeswibon.arike.shared.exception

import org.postgresql.util.PSQLException
import org.springframework.core.NestedExceptionUtils

object ExceptionUtils {
  fun getDbErrorFromException(ex: Exception, message: String = "Invalid request"): String {
    if (ex.cause == null) {
      return message
    }

    val rootCause = NestedExceptionUtils.getRootCause(ex)
    val remark = rootCause?.let {
      if (rootCause is PSQLException) {
        val psqlException: PSQLException = rootCause
        when (psqlException.sqlState.toString()) {
          "P0001" -> psqlException.serverErrorMessage?.message.toString()
          else -> message
        }
      } else {
        message
      }
    } ?: message
    return remark
  }
}
