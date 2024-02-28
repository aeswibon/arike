package com.aeswibon.arike.users.controller

import com.aeswibon.arike.shared.utils.Routes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.USERS)
class UserController {
  @GetMapping("/")
  fun getHelloWorld(): String {
    return "Hello World"
  }
}
