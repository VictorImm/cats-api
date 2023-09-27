package com.example.catsapitraining.data

data class Cat(
    val name: String,
    val image_link: String,
    val origin: String,
    val family_friendly: Int,
    val children_friendly: Int,
    val other_pets_friendly: Int,
    val playfulness: Int,
    val intelligence: Int,
    val grooming: Int,
)