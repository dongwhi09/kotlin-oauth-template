package com.example.oauth.repository

import com.example.oauth.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
    fun findByProviderId(providerId: String): Optional<User>
}