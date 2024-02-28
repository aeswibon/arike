package com.aeswibon.arike.token.db.entity

import com.aeswibon.arike.token.enum.TokenType
import com.aeswibon.arike.users.db.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tokens")
data class Token(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long = 0,
  @Column(unique = true)
  val token: String,
  @Column(name = "token_type")
  @Enumerated(EnumType.STRING)
  val tokenType: TokenType = TokenType.BEARER,
  var revoked: Boolean = false,
  var expired: Boolean = false,
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_uuid")
  val user: User,
)
