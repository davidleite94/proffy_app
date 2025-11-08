// EXEMPLO: Como usar na tela de CADASTRO (nome + sobrenome + email + senha)

class RegisterActivity : AppCompatActivity() {
    
    private fun validateAndRegister() {
        val nome = binding.editTextNome.text.toString().trim()
        val sobrenome = binding.editTextSobrenome.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextPassword.text.toString().trim()
        
        val validation = FormValidator.validateRegisterForm(nome, sobrenome, email, senha)
        
        if (validation.isValid) {
            // Fazer cadastro
            performRegister(nome, sobrenome, email, senha)
        } else {
            // Mostrar erro específico
            showError(validation.errorMessage ?: "Erro na validação")
        }
    }
    
    // Validação individual de campos com feedback visual
    private fun setupFieldValidation() {
        
        // Validação do Nome
        binding.editTextNome.addTextChangedListener { text ->
            val nome = text?.toString()?.trim() ?: ""
            val validation = FormValidator.validateName(nome)
            
            if (nome.isNotEmpty() && !validation.isValid) {
                binding.textInputLayoutNome.error = validation.errorMessage
            } else {
                binding.textInputLayoutNome.error = null
            }
            validateFormAndUpdateButton()
        }
        
        // Validação do Sobrenome
        binding.editTextSobrenome.addTextChangedListener { text ->
            val sobrenome = text?.toString()?.trim() ?: ""
            val validation = FormValidator.validateLastName(sobrenome)
            
            if (sobrenome.isNotEmpty() && !validation.isValid) {
                binding.textInputLayoutSobrenome.error = validation.errorMessage
            } else {
                binding.textInputLayoutSobrenome.error = null
            }
            validateFormAndUpdateButton()
        }
        
        // Validação do Email
        binding.editTextEmail.addTextChangedListener { text ->
            val email = text?.toString()?.trim() ?: ""
            val validation = FormValidator.validateEmail(email)
            
            if (email.isNotEmpty() && !validation.isValid) {
                binding.textInputLayoutEmail.error = validation.errorMessage
            } else {
                binding.textInputLayoutEmail.error = null
            }
            validateFormAndUpdateButton()
        }
        
        // Validação da Senha
        binding.editTextPassword.addTextChangedListener { text ->
            val senha = text?.toString()?.trim() ?: ""
            val validation = FormValidator.validatePassword(senha)
            
            if (senha.isNotEmpty() && !validation.isValid) {
                binding.textInputLayoutPassword.error = validation.errorMessage
            } else {
                binding.textInputLayoutPassword.error = null
            }
            validateFormAndUpdateButton()
        }
    }
    
    private fun validateFormAndUpdateButton() {
        val nome = binding.editTextNome.text.toString().trim()
        val sobrenome = binding.editTextSobrenome.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextPassword.text.toString().trim()
        
        val isValid = FormValidator.validateRegisterForm(nome, sobrenome, email, senha).isValid
        binding.buttonRegister.isEnabled = isValid
        
        // Atualizar cores do botão
        updateButtonState(isValid)
    }
}