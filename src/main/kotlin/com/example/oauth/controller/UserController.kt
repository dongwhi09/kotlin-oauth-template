package com.example.oauth.controller

import com.example.oauth.service.CustomOAuth2User
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {

    @GetMapping("/api/user")
    fun getCurrentUser(@AuthenticationPrincipal user: CustomOAuth2User): Map<String, Any> {
        return mapOf(
            "name" to user.name,
            "attributes" to user.attributes
        )
    }
}