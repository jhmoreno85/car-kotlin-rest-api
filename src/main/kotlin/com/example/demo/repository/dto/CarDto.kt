package com.example.demo.repository.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "CARS")
class CarDto(
    var model: String = "",
    var color: String = "",
    var brand: String = "",
    var plates: String = "",
    var vin: String = "",
    var year: Int = -1,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null)