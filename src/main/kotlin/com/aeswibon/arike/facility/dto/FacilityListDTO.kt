package com.aeswibon.arike.facility.dto

import com.aeswibon.arike.shared.dto.IDTO
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class FacilityListDTO(
  val facilities: List<FacilityMinimumDTO>,
  val count: Long,
) : IDTO
