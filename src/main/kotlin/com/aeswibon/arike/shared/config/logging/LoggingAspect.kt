package com.aeswibon.arike.shared.config.logging

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import jakarta.servlet.http.HttpServletRequest
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Aspect
@Configuration
class LoggingAspect {
  @Autowired
  lateinit var clientRequest: HttpServletRequest

  private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

  @Around("@annotation(com.aeswibon.arike.shared.config.logging.LogCreation)")
  @Suppress("TooGenericExceptionCaught")
  fun logExecutionTime(joinPoint: ProceedingJoinPoint): Any {
    val start = System.nanoTime()
    var signature = joinPoint.signature.toShortString()
    signature =
      signature.plus(
        "clientIP = ${getClientIP()}, methodType: ${clientRequest.method}, requestURI:${clientRequest.requestURI}" +
          """, {"path": "${joinPoint.target.javaClass.getResource("")?.path}"""" +
          """, "code_file_name":"${joinPoint.target.javaClass.simpleName}""""" +
          """, "method_name": "${joinPoint.signature.name}", "code_file_line": "empty"""",
      )

    val result =
      try {
        with(StringBuilder("start -> Executing $signature }")) {
          appendParameters(joinPoint.args)
          logger.info(toString())
        }
        joinPoint.proceed()
      } catch (throwable: Throwable) {
        logger.error("*** Exception during executing $signature,", throwable)
        throw throwable
      }
    val objectMapper = jacksonObjectMapper()
    objectMapper.registerKotlinModule()
      .registerModule(JavaTimeModule())
      .findAndRegisterModules()
    val duration = System.currentTimeMillis() - start

    logger.info(
      """end -> Finished executing: $signature, "payload": $result, duration(ms): $duration ms""",
    )
    if (result == null) return ""
    return result
  }

  private fun StringBuilder.appendParameters(args: Array<Any?>) {
    append("message = ")
    args.forEachIndexed { i, p ->
      if (p == null) append("Parameter Value is null")
      if (p?.javaClass?.simpleName != "StandardMultipartFile") {
        append(
          try {
            jacksonObjectMapper().writeValueAsString(p)
          } catch (e: JsonProcessingException) {
            logger.info(e.message)
            p.toString()
          },
        )
      } else if (p.javaClass.simpleName == "StandardMultipartFile") {
        append("Parameter is multipart file")
      } else {
        append("Parameter Value is null")
      }
      if (i < args.size - 1) append(", ")
    }
  }

  private fun getClientIP(): String {
    val xfHeader = clientRequest.getHeader("X-Forwarded-For") ?: return clientRequest.remoteAddr
    return xfHeader.split(",".toRegex())
      .dropLastWhile { it.isEmpty() }
      .toTypedArray()[0]
  }
}
