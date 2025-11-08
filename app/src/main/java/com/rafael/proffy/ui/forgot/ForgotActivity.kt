package com.rafael.proffy.ui.forgot

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
import com.rafael.proffy.databinding.ActivityForgotBinding
import com.rafael.proffy.models.validators.FormValidator
import com.rafael.proffy.ui.register.RegisterStepTwoActivity

class ForgotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
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

        binding.buttonGoBack.setOnClickListener {
            finish()
        }

        binding.buttonSendEmail.setOnClickListener {
            handleSendEmail()
        }
    }

    private fun setupValidation() {
        val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        // Validação para campo Email
        binding.textInputEditEmail.addTextChangedListener { text ->
            validateFields(enabledButtonColor, disabledButtonColor)
        }
    }

    public fun validateFields(enabledButtonColor: Int, disabledButtonColor: Int) {
        val textEditEmail = binding.textInputEditEmail.text?.toString()?.trim() ?: ""

        Log.d("forgotActivity", "textEditEmail='$textEditEmail'")

        // Validar email
        val emailValidation = FormValidator.validateEmail(textEditEmail)
        if (textEditEmail.isNotEmpty()) {
            binding.textInputEditEmail.error = if (!emailValidation.isValid) emailValidation.errorMessage else null
        } else {
            binding.textInputEditEmail.error = null
        }

        // Habilitar botão apenas se ambos os campos são válidos E não estão vazios
        val bothValid = emailValidation.isValid &&
                textEditEmail.isNotEmpty()

        Log.d("forgotActivity", "emailValid=${emailValidation.isValid} bothValid=$bothValid")

        setButtonState(bothValid, enabledButtonColor, disabledButtonColor)
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonSendEmail.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor
        binding.buttonSendEmail.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = ContextCompat.getColor(this, R.color.shape_01_white)
        binding.buttonSendEmail.setTextColor(textColor)

        Log.d("forgotActivity", "Button enabled: $enabled")
    }

    private fun handleSendEmail() {
        val textEditEmail = binding.textInputEditEmail.text?.toString()?.trim() ?: ""

        val intent = Intent(this, RegisterStepTwoActivity::class.java)
        intent.putExtra("textEditEmail", textEditEmail)
        startActivity(intent)
    }
}












