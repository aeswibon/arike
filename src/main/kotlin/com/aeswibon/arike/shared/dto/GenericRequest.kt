package com.aeswibon.arike.shared.dto

import jakarta.validation.Valid

data class GenericRequest<DataClass>(
  @get:Valid
  override val data: DataClass
) : RequestDTO<DataClass>
