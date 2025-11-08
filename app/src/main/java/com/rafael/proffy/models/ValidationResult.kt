package com.rafael.proffy.models

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        fun success() = ValidationResult(true)
        fun error(message: String) = ValidationResult(false, message)
    }
}