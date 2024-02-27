package com.aeswibon.arike.users.services

import com.aeswibon.arike.shared.exception.ArikeException
import com.aeswibon.arike.shared.exception.ErrorCodes
import com.aeswibon.arike.users.constants.UserConstants
import com.aeswibon.arike.users.db.repo.UserRepository
import com.aeswibon.arike.users.dto.AuthenticatedUserDetails
import com.aeswibon.arike.users.enum.UserStates
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userServiceHelper: UserServiceHelper
): UserService, UserDetailsService {
    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)
        if(user.isEmpty) {
            throw ArikeException(ErrorCodes.AK200001)
        }
        return AuthenticatedUserDetails(
            username = user.get().username,
            password = user.get().password,
            isEnabled = user.get().state == UserStates.ACTIVE.value,
            role = user.get().role,
            uuid = user.get().uuid.toString(),
            authorities = setOf(
                SimpleGrantedAuthority(UserConstants.ROLE_PREFIX + userServiceHelper.convertRoleToUpperCase(user.get().role.name))
            )
        )
    }
}