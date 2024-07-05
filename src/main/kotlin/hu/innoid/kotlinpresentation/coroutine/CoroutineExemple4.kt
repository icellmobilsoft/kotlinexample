package hu.innoid.kotlinpresentation.coroutine

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.random.Random

data class Account(val name: String, val accountId: Long)
data class Balance(val accountId: Long, val balance: Long)

interface ApiService {
    suspend fun getAccounts(): List<Account>
    suspend fun getBalance(accountId: Long): Long
}

class MockedApiService : ApiService {
    override suspend fun getAccounts(): List<Account> {
        return withContext(Dispatchers.IO) {
            val mockedAccounts = mutableListOf<Account>()
            for (i in 1..10) {
                mockedAccounts.add(Account("Account$i", i.toLong()))
            }
            delay(1000)
            mockedAccounts
        }
    }

    override suspend fun getBalance(accountId: Long): Long {
        return withContext(Dispatchers.IO) {
            println("Get balance account: $accountId")
            delay(1000)
            Random.nextLong(300000)
        }
    }
}

suspend fun fetchAllAccountBalanceAfterEachOther(apiService: ApiService): List<Balance> {
    return apiService.getAccounts().map { account ->
        val balance = apiService.getBalance(account.accountId)
        Balance(account.accountId, balance)
    }
}

suspend fun fetchAllAccountBalance(apiService: ApiService): List<Balance> {
    return coroutineScope {
        val balances = apiService.getAccounts().map { account ->
            async {
                val balance = apiService.getBalance(account.accountId)
                Balance(account.accountId, balance)
            }
        }.awaitAll()
        balances
    }
}

fun main() {
    runBlocking {
        try {
            //println(fetchAllAccountBalanceAfterEachOther(MockedApiService()))
            println(fetchAllAccountBalance(MockedApiService()))
        } catch (exception: Exception) {
            println(exception.message)
        }
    }
}
