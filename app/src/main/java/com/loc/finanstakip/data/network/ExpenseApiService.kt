package com.loc.finanstakip.data.network

import com.loc.finanstakip.data.model.Expense
import com.loc.finanstakip.data.model.ExpenseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ExpenseApiService {
    @GET("expenses")
    suspend fun getExpenses(): Response<List<ExpenseDto>>
}

interface ExpenseRepository {
    suspend fun fetchExpenses(): Result<List<Expense>>
}


