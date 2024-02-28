package com.aeswibon.arike.facility.dto

import com.aeswibon.arike.shared.dto.IDTO

data class FacilityMinimumDTO(
  val name: String,
  val address: String,
  val phone: String,
  val pincode: Long,
  val kind: String,
) : IDTO
