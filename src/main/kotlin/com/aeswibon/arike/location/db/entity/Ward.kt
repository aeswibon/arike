package com.aeswibon.arike.location.db.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "districts")
data class Ward(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  val name: String,
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lsg_bodies_id")
  val lsgBody: LSGBody,
)
