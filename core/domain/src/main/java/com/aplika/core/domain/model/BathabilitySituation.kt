package com.aplika.core.domain.model

enum class BathabilitySituation(val id: String?) {
    APPROPRIATE(id = "PRÓPRIO"),
    INAPPROPRIATE(id = "IMPRÓPRIO"),
    UNDETERMINED(id = null);

    companion object {
        fun getById(id: String?): BathabilitySituation = values().find { it.id == id } ?: UNDETERMINED
    }
}