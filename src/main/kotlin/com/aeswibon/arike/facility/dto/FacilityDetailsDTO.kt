package com.aeswibon.arike.facility.dto

import com.aeswibon.arike.location.dto.WardDTO
import com.aeswibon.arike.shared.dto.IDTO
import com.aeswibon.arike.users.dto.UserMinimumDTO
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class FacilityDetailsDTO(
  val name: String,
  val address: String,
  val pincode: Long,
  val phone: String,
  val kind: String,
  val ward: WardDTO,
  val createdBy: UserMinimumDTO,
) : IDTO
