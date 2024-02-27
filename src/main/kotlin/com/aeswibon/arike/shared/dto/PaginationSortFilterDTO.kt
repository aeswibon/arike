package com.aeswibon.arike.shared.dto

import jakarta.validation.constraints.Min

data class PaginationSortFilterDTO(
  @field:Min(value = 1, message = "page_size must be greater than zero")
  val pageSize: Int = 10,
  @field:Min(value = 1, message = "page_number must be greater than zero")
  val pageNumber: Int = 1,
  val searchText: String? = null,
  val sortBy: String? = null,
  val paginationEnabled: Boolean = true
)
