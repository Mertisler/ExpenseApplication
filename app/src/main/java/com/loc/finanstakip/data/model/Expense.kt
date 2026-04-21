package com.loc.finanstakip.data.model

data class Expense(
    val id: String,
    val title: String,
    val amount: Double,
    val category: String
)