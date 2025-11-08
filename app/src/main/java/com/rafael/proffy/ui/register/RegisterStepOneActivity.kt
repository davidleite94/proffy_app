
// VERSÃO CORRIGIDA E SIMPLIFICADA para RegisterStepOneActivity

package com.rafael.proffy.ui.register

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
import com.rafael.proffy.databinding.ActivityRegisterStepOneBinding
import com.rafael.proffy.models.validators.FormValidator

class RegisterStepOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepOneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

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

        binding.buttonNext.setOnClickListener {
            handleNextStep()
        }
    }

    private fun setupValidation() {
        val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        // Validação para campo Nome
        binding.textInputEditFirstName.addTextChangedListener { text ->
            validateFields(enabledButtonColor, disabledButtonColor)
        }

        // Validação para campo Sobrenome
        binding.textInputEditLastName.addTextChangedListener { text ->
            validateFields(enabledButtonColor, disabledButtonColor)
        }
    }

    public fun validateFields(enabledButtonColor: Int, disabledButtonColor: Int) {
        val firstName = binding.textInputEditFirstName.text?.toString()?.trim() ?: ""
        val lastName = binding.textInputEditLastName.text?.toString()?.trim() ?: ""

        Log.d("RegisterStep1", "firstName='$firstName' lastName='$lastName'")

        // Validar nome
        val nameValidation = FormValidator.validateName(firstName)
        if (firstName.isNotEmpty()) {
            binding.textInputLayoutFirstName.error = if (!nameValidation.isValid) nameValidation.errorMessage else null
        } else {
            binding.textInputLayoutFirstName.error = null
        }

        // Validar sobrenome
        val lastNameValidation = FormValidator.validateLastName(lastName)
        if (lastName.isNotEmpty()) {
            binding.textInputLayoutLastName.error = if (!lastNameValidation.isValid) lastNameValidation.errorMessage else null
        } else {
            binding.textInputLayoutLastName.error = null
        }

        // Habilitar botão apenas se ambos os campos são válidos E não estão vazios
        val bothValid = nameValidation.isValid && lastNameValidation.isValid &&
                firstName.isNotEmpty() && lastName.isNotEmpty()

        Log.d("RegisterStep1", "nameValid=${nameValidation.isValid} lastNameValid=${lastNameValidation.isValid} bothValid=$bothValid")

        setButtonState(bothValid, enabledButtonColor, disabledButtonColor)
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonNext.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor
        binding.buttonNext.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = ContextCompat.getColor(this, R.color.shape_01_white)
        binding.buttonNext.setTextColor(textColor)

        Log.d("RegisterStep1", "Button enabled: $enabled")
    }

    private fun handleNextStep() {
        val firstName = binding.textInputEditFirstName.text?.toString()?.trim() ?: ""
        val lastName = binding.textInputEditLastName.text?.toString()?.trim() ?: ""

        val intent = Intent(this, RegisterStepTwoActivity::class.java)
        intent.putExtra("firstName", firstName)
        intent.putExtra("lastName", lastName)
        startActivity(intent)
    }
}





