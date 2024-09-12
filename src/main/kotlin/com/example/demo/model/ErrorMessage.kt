package com.example.demo.model

import java.time.LocalDateTime

data class ErrorMessage(val status: Int? = null, val message: String? = null, val timestamp: LocalDateTime? = null)