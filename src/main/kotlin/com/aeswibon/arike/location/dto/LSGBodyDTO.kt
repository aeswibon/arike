package com.aeswibon.arike.location.dto

import com.aeswibon.arike.shared.dto.IDTO

data class LSGBodyDTO(
  val name: String,
  val kind: String,
  val code: String,
  val district: DistrictDTO,
) : IDTO
