package com.aeswibon.arike.location.db.entity

import com.aeswibon.arike.location.enum.LSGBodyKind
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "lsg_bodies")
data class LSGBody(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  val name: String,
  @Enumerated(EnumType.STRING)
  val kind: LSGBodyKind,
  val code: String,
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id")
  val district: District,
)
