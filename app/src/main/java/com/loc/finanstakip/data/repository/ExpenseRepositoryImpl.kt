package com.loc.finanstakip.data.repository

import com.loc.finanstakip.data.model.Expense
import com.loc.finanstakip.data.network.ExpenseApiService
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val apiService: ExpenseApiService
): ExpenseRepository {

    override suspend fun fetchExpenses(): Result<List<Expense>> {
        return try {
            val response = apiService.getExpenses()

            if (response.isSuccessful) {
                val expenses = response.body()?.map { it.toDomain() } ?: emptyList()
                Result.success(expenses)
            } else {
                Result.failure(Exception("Sunucu Hatası: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}