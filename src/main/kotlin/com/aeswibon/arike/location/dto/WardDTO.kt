package com.aeswibon.arike.location.dto

import com.aeswibon.arike.shared.dto.IDTO

data class WardDTO(
  val name: String,
  val lsgBodyDTO: LSGBodyDTO,
) : IDTO
