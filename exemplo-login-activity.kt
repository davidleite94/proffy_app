// EXEMPLO: Como usar na tela de LOGIN (email + senha)

class LoginActivity : AppCompatActivity() {
    
    private fun validateAndLogin() {
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextPassword.text.toString().trim()
        
        val validation = FormValidator.validateLoginForm(email, senha)
        
        if (validation.isValid) {
            // Fazer login
            performLogin(email, senha)
        } else {
            // Mostrar erro
            showError(validation.errorMessage ?: "Erro na validação")
        }
    }
    
    // Validação em tempo real para o botão
    private fun setupRealTimeValidation() {
        val emailWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateFormAndUpdateButton()
            }
            // ... outros métodos
        }
        
        val passwordWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                validateFormAndUpdateButton()
            }
            // ... outros métodos
        }
        
        binding.editTextEmail.addTextChangedListener(emailWatcher)
        binding.editTextPassword.addTextChangedListener(passwordWatcher)
    }
    
    private fun validateFormAndUpdateButton() {
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextPassword.text.toString().trim()
        
        val isValid = FormValidator.validateLoginForm(email, senha).isValid
        binding.buttonLogin.isEnabled = isValid
        
        // Atualizar cores do botão conforme necessário
        updateButtonState(isValid)
    }
}