package com.rafael.proffy.models.validators

import android.util.Patterns
import com.rafael.proffy.models.ValidationResult

object FormValidator {
    
    /**
     * Valida email
     * Usado na tela de Login e Esqueci Senha
     */
    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult.error("E-mail é obrigatório")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> 
                ValidationResult.error("E-mail inválido")
            else -> ValidationResult.success()
        }
    }
    
    /**
     * Valida senha
     * Usado na tela de Login e Cadastro
     */
    fun validatePassword(senha: String): ValidationResult {
        return when {
            senha.isBlank() -> ValidationResult.error("Senha é obrigatória")
            senha.length < 6 -> 
                ValidationResult.error("A senha deve conter pelo menos 6 caracteres")
            else -> ValidationResult.success()
        }
    }
    
    /**
     * Valida nome
     * Usado na tela de Cadastro
     */
    fun validateName(nome: String): ValidationResult {
        return when {
            nome.isBlank() -> ValidationResult.error("Nome é obrigatório")
            nome.length < 4 -> 
                ValidationResult.error("O nome deve conter pelo menos 2 caracteres")
            nome.length > 50 -> 
                ValidationResult.error("O nome deve conter no máximo 50 caracteres")
            !nome.matches(Regex("^[a-zA-ZÀ-ÿ\\s]+$")) -> 
                ValidationResult.error("O nome deve conter apenas letras")
            else -> ValidationResult.success()
        }
    }
    
    /**
     * Valida sobrenome
     * Usado na tela de Cadastro
     */
    fun validateLastName(sobrenome: String): ValidationResult {
        return when {
            sobrenome.isBlank() -> ValidationResult.error("Sobrenome é obrigatório")
            sobrenome.length < 4 -> 
                ValidationResult.error("O sobrenome deve conter pelo menos 2 caracteres")
            sobrenome.length > 50 -> 
                ValidationResult.error("O sobrenome deve conter no máximo 50 caracteres")
            !sobrenome.matches(Regex("^[a-zA-ZÀ-ÿ\\s]+$")) -> 
                ValidationResult.error("O sobrenome deve conter apenas letras")
            else -> ValidationResult.success()
        }
    }
    
    /**
     * Valida formulário de login (email + senha)
     */
    fun validateLoginForm(email: String, senha: String): ValidationResult {
        val emailValidation = validateEmail(email)
        if (!emailValidation.isValid) return emailValidation
        
        val passwordValidation = validatePassword(senha)
        if (!passwordValidation.isValid) return passwordValidation
        
        return ValidationResult.success()
    }
    
    /**
     * Valida formulário de cadastro (nome + sobrenome + email + senha)
     */
    fun validateRegisterForm(
        nome: String, 
        sobrenome: String, 
        email: String, 
        senha: String
    ): ValidationResult {
        val nameValidation = validateName(nome)
        if (!nameValidation.isValid) return nameValidation
        
        val lastNameValidation = validateLastName(sobrenome)
        if (!lastNameValidation.isValid) return lastNameValidation
        
        val emailValidation = validateEmail(email)
        if (!emailValidation.isValid) return emailValidation
        
        val passwordValidation = validatePassword(senha)
        if (!passwordValidation.isValid) return passwordValidation
        
        return ValidationResult.success()
    }
}
