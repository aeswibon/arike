package com.aeswibon.arike.location.dto

import com.aeswibon.arike.shared.dto.IDTO

data class DistrictDTO(
  val name: String,
  val state: StateDTO,
) : IDTO
