package com.aeswibon.arike.facility.service

import com.aeswibon.arike.facility.dto.FacilityListDTO
import com.aeswibon.arike.shared.dto.PaginationSortFilterDTO

interface FacilityService {
  fun fetchFacilities(paginationSortFilterDTO: PaginationSortFilterDTO): FacilityListDTO
}
