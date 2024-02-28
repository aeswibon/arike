package com.aeswibon.arike.location.db.repository

import com.aeswibon.arike.location.db.entity.LSGBody
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LSGBodyRepository : JpaRepository<LSGBody, Long>
