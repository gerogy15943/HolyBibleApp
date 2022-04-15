package com.example.holybibleapp.presentation

import com.example.holybibleapp.R
import com.example.holybibleapp.domain.BookDomainToUiMapper
import com.example.holybibleapp.domain.TestamentType

class BaseBookDomainToUiMapper(private val resourceProvider: ResourceProvider): BookDomainToUiMapper {
    override fun map(id: String, name: String): BookUi {
        return when{
            TestamentType.OLD.matches(id) -> BookUi.Testament(id, resourceProvider.getStringRes(R.string.old_testament))
            TestamentType.NEW.matches(id) -> BookUi.Testament(id, resourceProvider.getStringRes(R.string.new_testament))
            else -> BookUi.Base(id, name)
        }
    }
}