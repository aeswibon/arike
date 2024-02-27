package com.aeswibon.arike.facility.controller

import com.aeswibon.arike.shared.utils.Routes
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.FACILITY)
class FacilityController {
    @GetMapping
    fun helloWorld(): String {
        return "Hello from facility"
    }
}