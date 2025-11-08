package com.rafael.proffy.ui.login

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityLoginBinding
import com.rafael.proffy.models.validators.FormValidator
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
        window.statusBarColor = this.getColor(R.color.purple)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        setupViews()
        setupValidation()
    }
        private fun setupViews() {
            val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
            val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

            // Inicialmente desabilitar o botão
            setButtonState(false, enabledButtonColor, disabledButtonColor)

            binding.buttonForgot.setOnClickListener {
                handleForgot()
            }

            binding.buttonLogin.setOnClickListener {
                handleRegister()
            }

            binding.buttonSignup.setOnClickListener {
                handleSignUp()
            }
        }

        private fun setupValidation() {
            val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
            val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

            // Validação para campo Email
            binding.textInputEditEmail.addTextChangedListener { text ->
                validateFields(enabledButtonColor, disabledButtonColor)
            }

            // Validação para campo senha
            binding.textInputEditPassword.addTextChangedListener { text ->
                validateFields(enabledButtonColor, disabledButtonColor)
            }
        }

        public fun validateFields(enabledButtonColor: Int, disabledButtonColor: Int) {
            val textEditEmail = binding.textInputEditEmail.text?.toString()?.trim() ?: ""
            val textEditSenha = binding.textInputEditPassword.text?.toString()?.trim() ?: ""

            Log.d("LoginActivity", "textEditEmail='$textEditEmail' textEditSenha='$textEditSenha'")

            // Validar email
            val emailValidation = FormValidator.validateEmail(textEditEmail)
            if (textEditEmail.isNotEmpty()) {
                binding.textInputEditEmail.error = if (!emailValidation.isValid) emailValidation.errorMessage else null
            } else {
                binding.textInputEditEmail.error = null
            }

            // Validar senha
            val passwordValidation = FormValidator.validatePassword(textEditSenha)
            if (textEditSenha.isNotEmpty()) {
                binding.textInputEditPassword.error = if (!passwordValidation.isValid) passwordValidation.errorMessage else null
            } else {
                binding.textInputEditPassword.error = null
            }

            // Habilitar botão apenas se ambos os campos são válidos E não estão vazios
            val bothValid = emailValidation.isValid && passwordValidation.isValid &&
                    textEditEmail.isNotEmpty() && textEditSenha.isNotEmpty()

            Log.d("LoginActivity", "emailValid=${emailValidation.isValid} passwordValid=${passwordValidation.isValid} bothValid=$bothValid")

            setButtonState(bothValid, enabledButtonColor, disabledButtonColor)
        }

        private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
            binding.buttonLogin.isEnabled = enabled
            val color = if (enabled) enabledColor else disabledColor
            binding.buttonLogin.backgroundTintList = ColorStateList.valueOf(color)
            val textColor = ContextCompat.getColor(this, R.color.shape_01_white)
            binding.buttonLogin.setTextColor(textColor)

            Log.d("LoginActivity", "Button enabled: $enabled")
        }

        private fun handleForgot() {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }

        private fun handleRegister() {
            val intent = Intent(this, RegisterStepOneActivity::class.java)
            startActivity(intent)
        }

        private fun handleSignUp() {
            val intent = Intent(this, RegisterStepOneActivity::class.java)
            startActivity(intent)
        }
    }










