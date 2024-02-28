package com.aeswibon.arike.facility.db.repository

import com.aeswibon.arike.facility.db.entity.Facility
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FacilityRepository : JpaRepository<Facility, UUID> {
  fun findByNameContainsIgnoreCase(
    name: String,
    page: Pageable,
  ): Page<Facility>
}
