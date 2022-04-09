package com.example.holybibleapp.core

import org.junit.Assert.*
import org.junit.Test
import java.io.IOException
import java.lang.Exception

class AbstractTest{

    @Test
    fun test_success(){
        val dataObject = TestDataObject.Success("a", "b")
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }

    @Test
    fun test_fail(){
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        assertTrue(domainObject is DomainObject.Success)
    }



    private sealed class TestDataObject: Abstract.Object<DomainObject, DataToDomainMapper> {
        abstract override fun map(toDomainMapper: DataToDomainMapper): DomainObject

        class Success(private val textOne: String, private val textTwo: String): TestDataObject() {
            override fun map(toDomainMapper: DataToDomainMapper): DomainObject {
               return toDomainMapper.map(textOne, textTwo)
            }
        }

        class Fail(private val exception: Exception): TestDataObject() {
            override fun map(toDomainMapper: DataToDomainMapper): DomainObject {
                return toDomainMapper.map(exception)
            }
        }
    }

    private sealed class DomainObject: Abstract.Object<UiObject, DomainToUiMapper>{
        class Success(private val textCombine: String): DomainObject(){
            override fun map(mapper: DomainToUiMapper): UiObject {
                TODO("Not yet implemented")
            }
        }
        class Fail: DomainObject(){
            override fun map(mapper: DomainToUiMapper): UiObject {
                TODO("Not yet implemented")
            }
        }
    }

    private interface DataToDomainMapper: Abstract.Mapper{
        fun map(textOne: String, textTwo: String): DomainObject

        fun map(exception: Exception): DomainObject

        class Base: DataToDomainMapper {
            override fun map(textOne: String, textTwo: String): DomainObject {
                return DomainObject.Success("$textOne $textTwo")
            }

            override fun map(exception: Exception): DomainObject {
                return DomainObject.Fail()
            }
        }

    }
    }

    private sealed class UiObject: Abstract.Object<Unit, Abstract.Mapper.Empty>
    private interface DomainToUiMapper: Abstract.Mapper{

    }