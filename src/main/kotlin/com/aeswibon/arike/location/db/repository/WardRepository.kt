package com.aeswibon.arike.location.db.repository

import com.aeswibon.arike.location.db.entity.Ward
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WardRepository : JpaRepository<Ward, Long>
