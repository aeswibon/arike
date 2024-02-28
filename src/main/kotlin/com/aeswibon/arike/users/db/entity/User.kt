package com.aeswibon.arike.users.db.entity

import com.aeswibon.arike.facility.db.entity.Facility
import com.aeswibon.arike.location.db.entity.District
import com.aeswibon.arike.users.enum.Role
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.ZonedDateTime
import java.util.UUID

@Entity
@Table(name = "users")
data class User(
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  val uuid: UUID,
  @Column(name = "username")
  val username: String,
  @Column(name = "email")
  val email: String,
  @Column(name = "password")
  val password: String,
  @Column(name = "state")
  val state: String,
  @Column(name = "role")
  val role: Role,
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "district_id")
  val district: District,
  val deleted: Boolean = false,
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "linked_facilities",
    joinColumns = [JoinColumn(name = "facilities_uuid")],
    inverseJoinColumns = [JoinColumn(name = "users_uuid")],
  )
  @JsonIgnoreProperties("users")
  var facilities: List<Facility> = mutableListOf(),
  @Column(name = "created_at")
  val createdAt: ZonedDateTime,
  @Column(name = "updated_at")
  val updatedAt: ZonedDateTime,
)
