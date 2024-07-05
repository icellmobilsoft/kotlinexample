package hu.innoid.kotlinpresentation

import hu.innoid.kotlinpresentation.coroutine.Account
import hu.innoid.kotlinpresentation.coroutine.ApiService
import hu.innoid.kotlinpresentation.coroutine.Balance
import hu.innoid.kotlinpresentation.coroutine.fetchAllAccountBalance
import hu.innoid.kotlinpresentation.flow.dataEmitter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun `fetch all account balances returns the balance of every available account`() = runTest {
        val accountList = listOf(Account("Name1", 1L), Account("Name2", 1L))
        val apiService: ApiService = mockk()
        val mockedBalance = 100L

        coEvery { apiService.getAccounts() } returns accountList
        coEvery { apiService.getBalance(any()) } returns mockedBalance

        val expectedBalances = accountList.map { account -> Balance(account.accountId, mockedBalance) }

        val balances = fetchAllAccountBalance(apiService)

        accountList.forEach { account ->
            coVerify { apiService.getBalance(account.accountId) }
        }

        Assert.assertEquals(expectedBalances, balances)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `data emitter emits a value in every 100ms`() = runTest {
        var emitCount = 0
        launch {
            dataEmitter.collectLatest {
                emitCount++
            }
        }

        Assert.assertEquals(0, emitCount)
        advanceTimeBy(1000L) //Default delay
        Assert.assertEquals(0, emitCount)
        advanceTimeBy(100L)
        Assert.assertEquals(0, emitCount)
        advanceTimeBy(1L)
        Assert.assertEquals(1, emitCount)
        advanceTimeBy(200L)
        Assert.assertEquals(3, emitCount)
    }
}
