package com.aplika.core.domain.model

enum class RainSituation(val id: String?) {
    ABSENT(id = "Ausente"),
    WEAK(id = "Fraca"),
    MODERATE(id = "Moderada"),
    UNKNOWN(id = null);

    companion object {
        fun getById(id: String?): RainSituation = values().find { it.id == id } ?: UNKNOWN
    }
}