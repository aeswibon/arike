package com.aeswibon.arike.shared.utils

import com.aeswibon.arike.shared.exception.ArikeException
import com.aeswibon.arike.shared.exception.ErrorCodes
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

object PagingHelper {
  fun getPagingObject(
    pageNumber: Int,
    pageSize: Int,
    sortBy: String? = null,
  ): PageRequest {
    if (pageNumber < 1 || pageSize < 1) {
      throw ArikeException(ErrorCodes.AK00003)
    }
    if (sortBy.isNullOrEmpty()) {
      return PageRequest.of(
        (pageNumber - 1),
        pageSize,
      )
    }
    return PageRequest.of(
      (pageNumber - 1),
      pageSize,
      Sort.by(sortBy).descending(),
    )
  }
}
