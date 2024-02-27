package com.aeswibon.arike.users.db.entity

import com.aeswibon.arike.users.enum.Role
import jakarta.persistence.*
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

    @Column(name = "created_at")
    val createdAt: ZonedDateTime,

    @Column(name = "updated_at")
    val updatedAt: ZonedDateTime
)
