package com.example.demo.service

interface Crud<T> {
    fun get(id: Int): T
    fun getAll(): List<T>
    fun save(t: T): T
    fun update(t: T): T
    fun delete(id: Int)
}