package com.example.tuneup.model

import com.example.tuneup.model.searchResult

data class searchData(
    val results: List<searchResult>,
    val start: Int,
    val total: Int
)