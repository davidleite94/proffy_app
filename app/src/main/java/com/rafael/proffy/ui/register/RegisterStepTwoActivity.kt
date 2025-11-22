package com.rafael.proffy.ui.register

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityRegisterStepTwoBinding
import com.rafael.proffy.ui.finally.FinallyActivity
import com.rafael.proffy.ui.login.LoginActivity

class RegisterStepTwoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepTwoBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonGoBackSetOne = binding.buttonGoBack
        val textInputEmail = binding.textInputEditEmail
        val textInputPassword = binding.textInputEditPassword
        val buttonFinallyRegister = binding.buttonNext

        val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")

        print("Primeiro Nome: $firstName Sobrenome: $lastName")

        setValidation(textInputEmail,
            textInputPassword,
            enabledButtonColor,
            disabledButtonColor,
            buttonFinallyRegister)

        buttonGoBackSetOne.setOnClickListener {
            goBack()
        }
    }

    private fun setValidation(textInputEmail: TextInputEditText,
                              textInputPassword: TextInputEditText,
                              enabledButtonColor: Int,
                              disabledButtonColor: Int,
                              buttonFinallyRegister: Button) {
        fun validate() {
            val email = textInputEmail.text.toString().trim()
            val password = textInputPassword.text.toString().trim()

            val isValid = email.isNotEmpty() && password.isNotEmpty()

            setButtonState(isValid, enabledButtonColor, disabledButtonColor)
        }

        textInputEmail.doOnTextChanged { _, _, _, _ -> validate() }
        textInputPassword.doOnTextChanged { _, _, _, _ -> validate() }

        buttonFinallyRegister.setOnClickListener { validateInputs(textInputEmail, textInputPassword) }
    }

    private fun validateInputs(textInputEmail: TextInputEditText,
                               textInputPassword: TextInputEditText) {
        val email = textInputEmail.text.toString().trim()
        val password = textInputPassword.text.toString().trim()

        var isValid = true

        if(!isValidEmail(email)) {
            textInputEmail.error = "Digite um e-mail válido."
            isValid = false
        }

        if(password.length < 6) {
            textInputPassword.error = "A senha deve conter pelo menos 6 caracteres."
            isValid = false
        }

        if (isValid) {
            //  ALTERAÇÃO APLICADA AQUI: Redireciona para FinallyActivity
            val title = "Cadastro concluído!"
            val subtitle = "Agora você faz parte da plataforma da Proffy"
            val buttonText = "Fazer login"

            val intent = FinallyActivity.newIntent(this, title, subtitle, buttonText)
            startActivity(intent)

            finish()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun goBack() {
        finish()
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonNext.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor

        binding.buttonNext.backgroundTintList = ColorStateList.valueOf(color)

        val textColor = if (enabled)
            ContextCompat.getColor(this, R.color.shape_01_white)
        else
            ContextCompat.getColor(this, R.color.text_complement)

        binding.buttonNext.setTextColor(textColor)
    }
}





