package com.aeswibon.arike.facility.adapter

import com.aeswibon.arike.facility.db.entity.Facility
import com.aeswibon.arike.facility.dto.FacilityMinimumDTO

object FacilityAdapter {
  fun getFacilityDetail(facility: Facility): FacilityMinimumDTO {
    return FacilityMinimumDTO(
      name = facility.name,
      address = facility.address,
      pincode = facility.pincode,
      phone = facility.phone,
      kind = facility.kind.name,
    )
  }
}
