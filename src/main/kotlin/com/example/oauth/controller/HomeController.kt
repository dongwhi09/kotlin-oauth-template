package com.example.oauth.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun home() = "index"

    @GetMapping("/login")
    fun login() = "login"
}