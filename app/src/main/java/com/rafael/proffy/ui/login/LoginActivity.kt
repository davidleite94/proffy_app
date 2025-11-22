package com.rafael.proffy.ui.login

import android.content.Intent
import android.content.res.ColorStateList // Import necessário
import android.os.Bundle
import android.util.Patterns // Import necessário
import android.widget.Button // Import necessário
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat // Import necessário
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowCompat // Import para código não-obsoleto
import androidx.core.widget.doOnTextChanged // Import para validação de digitação
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityLoginBinding
import com.rafael.proffy.ui.forgot.ForgotActivity
import com.rafael.proffy.ui.register.RegisterStepOneActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        enableEdgeToEdge()


        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonForgot = binding.buttonForgot
        val buttonRegister = binding.buttonSignup
        val buttonEnter = binding.buttonLogin // ID: button_login
        val textInputEmail = binding.textInputEditEmail
        val textInputPassword = binding.textInputEditPassword

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        // 1. Lógica de Validação e Estado do Botão
        val inputValidator = {
            val email = textInputEmail.text.toString().trim()
            val password = textInputPassword.text.toString().trim()

            // Requer e-mail válido E senha de pelo menos 6 caracteres
            val isValid = isValidEmail(email) && password.length >= 6

            setButtonState(buttonEnter, isValid, enabledButtonColor, disabledButtonColor)
        }

        textInputEmail.doOnTextChanged { _, _, _, _ -> inputValidator() }
        textInputPassword.doOnTextChanged { _, _, _, _ -> inputValidator() }

        // Aplica o estado inicial ao carregar a tela
        inputValidator()

        // 2.  AÇÃO DO BOTÃO "ENTRAR" (Login)
        buttonEnter.setOnClickListener {
            // Revalida no clique (melhor prática)
            val email = textInputEmail.text.toString().trim()
            val password = textInputPassword.text.toString().trim()

            if (isValidEmail(email) && password.length >= 6) {
                // TODO: AQUI VOCÊ FARIA A CHAMADA À API DE LOGIN.
                // Por enquanto, faremos uma simulação de sucesso:
                handleLoginSuccess()
            } else {
                // O botão não deveria estar habilitado, mas é um fallback
                textInputEmail.error = if (!isValidEmail(email)) "E-mail inválido." else null
                textInputPassword.error = if (password.length < 6) "Senha deve ter 6+ caracteres." else null
            }
        }

        buttonForgot.setOnClickListener {
            handleForgot()
        }

        buttonRegister.setOnClickListener {
            handleRegister()
        }
    }

    private fun handleLoginSuccess() {

    }

    private fun handleForgot() {
        val intent = Intent(this, ForgotActivity::class.java)
        startActivity(intent)
    }

    private fun handleRegister() {
        val intent = Intent(this, RegisterStepOneActivity::class.java)
        startActivity(intent)
    }

    // Funções Auxiliares

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setButtonState(button: Button, enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        button.isEnabled = enabled

        val color = if (enabled) enabledColor else disabledColor
        button.backgroundTintList = ColorStateList.valueOf(color)

        // Mantém a cor do texto branca se o botão estiver habilitado
        val textColor = ContextCompat.getColor(this, R.color.shape_01_white)
        button.setTextColor(textColor)
    }
}