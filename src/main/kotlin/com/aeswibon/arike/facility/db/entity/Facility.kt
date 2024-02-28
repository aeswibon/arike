package com.aeswibon.arike.facility.db.entity

import com.aeswibon.arike.facility.enum.FacilityType
import com.aeswibon.arike.location.db.entity.Ward
import com.aeswibon.arike.users.db.entity.User
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.PrimaryKeyJoinColumn
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "facilities")
data class Facility(
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  val uuid: UUID,
  @Column(nullable = false)
  val name: String,
  @Column(nullable = false)
  val address: String,
  @Column(nullable = false)
  val pincode: Long,
  @Column(nullable = false)
  val phone: String,
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ward_id")
  val ward: Ward,
  @Enumerated(EnumType.STRING)
  val kind: FacilityType,
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "users_uuid")
  @PrimaryKeyJoinColumn(name = "created_by")
  val createdBy: User,
  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "facilities")
  @JsonIgnoreProperties("facilities")
  val users: Set<User>,
)
