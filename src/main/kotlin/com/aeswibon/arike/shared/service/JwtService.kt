package com.aeswibon.arike.shared.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey
import java.util.function.Function

@Service
class JwtService {
    @Value("\${application.security.jwt.secret-key}")
    private lateinit var secretKey: String

    @Value("\${application.security.jwt.expiration}")
    private lateinit var jwtExpiration: String

    @Value("\${application.security.jwt.refresh-token.expiration}")
    private lateinit var refreshExpiration: String

    private fun getSignKey(): SecretKey {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parser().verifyWith(getSignKey()).build().parseSignedClaims(token).payload
    }

    private fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

     private fun buildToken(extractClaims: Map<String, Any>, userDetails: UserDetails, expiration: Long): String {
        return Jwts.builder().claims().add(extractClaims).subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis())).expiration(Date(System.currentTimeMillis() + expiration)).and()
            .signWith(getSignKey()).compact()
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun isTokenExpired(token: String): Boolean {
       return extractExpiration(token).before(Date())
    }

    fun extractUsername(token: String): String? {
        return extractClaim(token, Claims::getSubject)
    }

    fun generateToken(userDetails: UserDetails): String {
        return generateToken(HashMap(), userDetails)
    }

    fun generateToken(extractClaims: Map<String, Any>, userDetails: UserDetails): String {
        return buildToken(extractClaims, userDetails, jwtExpiration.toLong())
    }

    fun generateRefreshToken(userDetails: UserDetails): String {
        return generateRefreshToken(HashMap(), userDetails)
    }

    fun generateRefreshToken(extractClaims: Map<String, Any>, userDetails: UserDetails): String {
        return buildToken(extractClaims, userDetails, refreshExpiration.toLong())
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }
}