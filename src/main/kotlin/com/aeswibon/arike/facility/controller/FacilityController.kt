package com.aeswibon.arike.facility.controller

import com.aeswibon.arike.facility.service.FacilityService
import com.aeswibon.arike.shared.config.logging.LogCreation
import com.aeswibon.arike.shared.dto.PaginationSortFilterDTO
import com.aeswibon.arike.shared.dto.ResponseDTO
import com.aeswibon.arike.shared.dto.createSuccessResponse
import com.aeswibon.arike.shared.utils.Routes
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.FACILITY)
class FacilityController(
  private val facilityService: FacilityService,
) {
  @LogCreation
  @GetMapping
  fun fetchFacilities(
    @Valid paginationSortFilterDTO: PaginationSortFilterDTO,
  ): ResponseEntity<ResponseDTO> {
    val response = facilityService.fetchFacilities(paginationSortFilterDTO)
    return createSuccessResponse(response)
  }
}
