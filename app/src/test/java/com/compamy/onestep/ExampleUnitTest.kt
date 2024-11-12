package com.compamy.onestep

import android.content.Context
import com.compamy.onestep.ForTesting.ClassUnderTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

private val FAKE_NAME ="onestep"
@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {


    @Mock
    private lateinit var mockContext : Context

    @Test
    fun readStringFromContext()
    {
        val mockContext = mock<Context>{
            on { getString(R.string.app_name)} doReturn FAKE_NAME
        }

        val objectUnderTest = ClassUnderTest(mockContext)

        val result = objectUnderTest.getAppName()

        assertEquals(result, FAKE_NAME)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun emailValidator_CorrectEmail_ReturnTrue()
    {
        assertTrue("abc@gmail".contains("@"))

    }
}