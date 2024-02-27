package com.aeswibon.arike.shared.dto

interface RequestDTO<DataClass> : IDTO {
  val data: DataClass
}
