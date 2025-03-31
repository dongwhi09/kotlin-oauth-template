package com.example.oauth.service

import com.example.oauth.domain.AuthProvider
import com.example.oauth.domain.User
import com.example.oauth.repository.UserRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository
) : DefaultOAuth2UserService() {

    @Transactional
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)
        
        val provider = AuthProvider.valueOf(userRequest.clientRegistration.registrationId.uppercase())
        val providerId = when (provider) {
            AuthProvider.GOOGLE -> oAuth2User.attributes["sub"] as String
            AuthProvider.GITHUB -> oAuth2User.attributes["id"].toString()
        }
        val email = oAuth2User.attributes["email"] as String
        val name = when (provider) {
            AuthProvider.GOOGLE -> oAuth2User.attributes["name"] as String
            AuthProvider.GITHUB -> oAuth2User.attributes["login"] as String
        }
        val picture = when (provider) {
            AuthProvider.GOOGLE -> oAuth2User.attributes["picture"] as String?
            AuthProvider.GITHUB -> oAuth2User.attributes["avatar_url"] as String?
        }

        val userOptional = userRepository.findByProviderId(providerId)
        val user = if (userOptional.isPresent) {
            userOptional.get()
        } else {
            userRepository.save(
                User(
                    email = email,
                    name = name,
                    picture = picture,
                    provider = provider,
                    providerId = providerId
                )
            )
        }

        return CustomOAuth2User(user, oAuth2User.attributes)
    }
}