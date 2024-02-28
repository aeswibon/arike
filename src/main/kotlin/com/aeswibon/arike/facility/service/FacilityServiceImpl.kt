package com.aeswibon.arike.facility.service

import com.aeswibon.arike.facility.adapter.FacilityAdapter
import com.aeswibon.arike.facility.db.repository.FacilityRepository
import com.aeswibon.arike.facility.dto.FacilityListDTO
import com.aeswibon.arike.facility.dto.FacilityMinimumDTO
import com.aeswibon.arike.shared.dto.PaginationSortFilterDTO
import com.aeswibon.arike.shared.utils.PagingHelper
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class FacilityServiceImpl(
  private val facilityRepository: FacilityRepository,
) : FacilityService {
  override fun fetchFacilities(data: PaginationSortFilterDTO): FacilityListDTO {
    val facilityList: ArrayList<FacilityMinimumDTO> = ArrayList()
    if (!data.paginationEnabled) {
      val facilities = facilityRepository.findAll()
      facilities.forEach {
        facilityList.add(FacilityAdapter.getFacilityDetail(it))
      }
      return FacilityListDTO(facilityList, facilityList.size.toLong())
    }
    val paging: Pageable = PagingHelper.getPagingObject(data.pageNumber, data.pageSize)
    val facilities = facilityRepository.findByNameContainsIgnoreCase(data.searchText ?: "", paging)
    facilities.content.forEach {
      facilityList.add(FacilityAdapter.getFacilityDetail(it))
    }
    return FacilityListDTO(facilityList, facilities.totalElements)
  }
}
