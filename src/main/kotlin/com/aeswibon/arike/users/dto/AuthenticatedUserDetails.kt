package com.aeswibon.arike.users.dto

import com.aeswibon.arike.users.enum.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class AuthenticatedUserDetails(
  private val username: String,
  private val isEnabled: Boolean,
  val role: Role,
  val uuid: String,
  private val password: String? = null,
  private val isCredentialsNonExpired: Boolean = true,
  private val isAccountNonExpired: Boolean = true,
  private val isAccountNonLocked: Boolean = true,
  private val authorities: Set<GrantedAuthority> = setOf(),
) : UserDetails {
  override fun getAuthorities(): Set<GrantedAuthority> = authorities

  override fun getPassword(): String? = password

  override fun getUsername(): String = username

  override fun isAccountNonExpired(): Boolean = isAccountNonExpired

  override fun isAccountNonLocked(): Boolean = isAccountNonLocked

  override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired

  override fun isEnabled(): Boolean = isEnabled
}
