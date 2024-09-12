package com.example.demo.exception

import com.example.demo.model.ErrorMessage

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import java.time.LocalDateTime

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleIllegalStateException(ex: IllegalStateException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            ex.message,
            LocalDateTime.now()
        )
        return ResponseEntity(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.BAD_REQUEST.value(),
            ex.message,
            LocalDateTime.now()
        )
        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleNotFoundException(ex: NotFoundException): ResponseEntity<ErrorMessage> {
        val errorMessage = ErrorMessage(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            LocalDateTime.now()
        )
        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }
}