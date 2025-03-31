package com.example.oauth.service

import com.example.oauth.domain.AuthProvider
import com.example.oauth.domain.User
import com.example.oauth.repository.UserRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class CustomOAuth2UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var oAuth2UserRequest: OAuth2UserRequest

    @Mock
    private lateinit var clientRegistration: ClientRegistration

    @Mock
    private lateinit var oAuth2User: OAuth2User

    @InjectMocks
    private lateinit var customOAuth2UserService: CustomOAuth2UserService

    @Test
    fun `Google 사용자 로그인 테스트`() {
        // given
        val attributes = mapOf(
            "sub" to "123",
            "email" to "test@example.com",
            "name" to "Test User",
            "picture" to "http://example.com/picture.jpg"
        )

        `when`(oAuth2UserRequest.clientRegistration).thenReturn(clientRegistration)
        `when`(clientRegistration.registrationId).thenReturn("google")
        `when`(oAuth2User.attributes).thenReturn(attributes)
        `when`(userRepository.findByProviderId("123")).thenReturn(Optional.empty())

        // when
        val result = customOAuth2UserService.loadUser(oAuth2UserRequest)

        // then
        verify(userRepository).save(any(User::class.java))
        assert(result.attributes == attributes)
    }
}