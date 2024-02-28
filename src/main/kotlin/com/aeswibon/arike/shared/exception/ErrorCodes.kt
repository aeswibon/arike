package com.aeswibon.arike.shared.exception

enum class ErrorCodes(val exceptionName: String, val message: String) {
  // GENERIC
  AK00001("Arike Exception", "Internal Server Error. Something went wrong."),
  AK00002("Validation Exception", "An error occurred while validating request data."),
  AK00003("Invalid Data Exception", "Page Number or Page Size cannot be less than 1."),
  AK00004("Invalid Data Exception", "Invalid request data/format."),

  // AUTHENTICATION AND AUTHORIZATION
  AK10001("Authorization Exception", "Unauthorized."),
  AK10002("Authentication Exception", "Error in login."),
  AK10003("Authentication Exception", "Token has expired"),
  AK10004("Authentication Exception", "No user found."),
  AK10005("Authentication Exception", "User is disabled."),
  AK10006("Authentication Exception", "Invalid token."),

  // USERS
  AK200001("User Exception", "No such user exists."),
  AK200002("User Exception", "Invalid user state"),
}
