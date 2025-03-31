package com.example.oauth.controller

import com.example.oauth.service.CustomOAuth2User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    @WithMockUser
    fun `인증된 사용자는 API에 접근할 수 있다`() {
        mockMvc.perform(get("/api/user")
            .with(oauth2Login()))
            .andExpect(status().isOk)
    }

    @Test
    fun `비인증 사용자는 API에 접근할 수 없다`() {
        mockMvc.perform(get("/api/user"))
            .andExpect(status().isUnauthorized)
    }
}