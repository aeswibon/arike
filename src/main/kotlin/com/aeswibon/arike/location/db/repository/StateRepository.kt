package com.aeswibon.arike.location.db.repository

import com.aeswibon.arike.location.db.entity.State
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StateRepository : JpaRepository<State, Long>
