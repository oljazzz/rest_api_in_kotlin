package com.example.requestservice.repository

import com.example.requestservice.model.Product
import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long>