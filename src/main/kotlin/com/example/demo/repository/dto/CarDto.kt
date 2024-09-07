package com.example.demo.repository.dto

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "CARS")
class CarDto(val model: String = "",
             val color: String = "",
             val brand: String = "",
             val plates: String = "",
             val year: Int = -1,
             @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = -1)