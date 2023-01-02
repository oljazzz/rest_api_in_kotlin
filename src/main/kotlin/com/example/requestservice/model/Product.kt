package com.example.requestservice.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "products")
data class Product(
    @JsonProperty("name")
    @Column(name = "name", length = 200)
    val name: String = "",

    @JsonProperty("description")
    @Column(name = "description", length = 1000)
    val description: String = "",

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L
)
