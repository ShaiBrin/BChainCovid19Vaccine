package com.log450.bchainvoteversion1

import com.log450.bchainvoteversion1.Utils.Functions
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_validateCredentials() {
        assertEquals(true, Functions.validateEmail("mail@mail.com"))
        assertEquals(false, Functions.validateEmail("mailmail.com"))
    }


}

