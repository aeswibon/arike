package com.aeswibon.arike.users.dto

import com.aeswibon.arike.location.dto.DistrictDTO
import com.aeswibon.arike.shared.dto.IDTO
import java.time.ZonedDateTime

data class UserDetailsDTO(
  val uuid: String,
  val username: String,
  val email: String,
  val state: String,
  val role: String,
  val district: DistrictDTO,
  val createdAt: ZonedDateTime,
  val updatedAt: ZonedDateTime,
) : IDTO
