package com.rafael.proffy.ui.register

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityRegisterStepOneBinding

class RegisterStepOneActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterStepOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStepOneBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonGoBackLogin = binding.buttonGoBack
        val textInputFirstName = binding.textInputEditFirstName
        val textInputLastName = binding.textInputEditLastName
        val buttonNext = binding.buttonNext

        val enabledButtonColor = ContextCompat.getColor(this, R.color.purple)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        setValidation(textInputFirstName,
            textInputLastName,
            enabledButtonColor,
            disabledButtonColor,
            buttonNext)

        buttonGoBackLogin.setOnClickListener {
            goBack()
        }
    }

    private fun goBack() {
        finish()
    }

    private fun setValidation(textInputFirstName: TextInputEditText,
                              textInputLastName: TextInputEditText,
                              enabledButtonColor: Int,
                              disabledButtonColor: Int,
                              buttonNext: Button) {
        fun validate() {
            val firstName = textInputFirstName.text.toString().trim()
            val lastName = textInputLastName.text.toString().trim()

            val isValid = firstName.isNotEmpty() && lastName.isNotEmpty()

            setButtonState(isValid, enabledButtonColor, disabledButtonColor)
        }

        textInputFirstName.doOnTextChanged { _, _, _, _ -> validate() }
        textInputLastName.doOnTextChanged { _, _, _, _ -> validate() }

        buttonNext.setOnClickListener { validateInputs(textInputFirstName, textInputLastName) }
    }

    private fun validateInputs(textInputFirstName: TextInputEditText,
                               textInputLastName: TextInputEditText) {
        val firstName = textInputFirstName.text.toString().trim()
        val lastName = textInputLastName.text.toString().trim()

        var isValid = true

        if(firstName.length < 3) {
            textInputFirstName.error = "deve conter pelo menos 3 caracteres."
            isValid = false
        }

        if(lastName.length < 3) {
            textInputLastName.error = "deve conter pelo menos 3 caracteres."
            isValid = false
        }

        if (isValid) {
            val intent = Intent(this, RegisterStepTwoActivity::class.java)
            intent.putExtra("firstName", firstName)
            intent.putExtra("lastName", lastName)
            startActivity(intent)
        }
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        binding.buttonNext.isEnabled = enabled
        val color = if (enabled) enabledColor else disabledColor

        binding.buttonNext.backgroundTintList = ColorStateList.valueOf(color)
        val textColor = ContextCompat.getColor(this, R.color.shape_01_white)

        binding.buttonNext.setTextColor(textColor)
    }
}








