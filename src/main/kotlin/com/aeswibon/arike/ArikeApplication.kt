package com.aeswibon.arike

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ArikeApplication

fun main(args: Array<String>) {
	runApplication<ArikeApplication>(*args)
}
