package com.rafael.proffy.ui.forgot

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.core.view.WindowCompat
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityForgotBinding
import com.rafael.proffy.ui.finally.FinallyActivity

class ForgotActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()

        // Configuração moderna da Status Bar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        val buttonGoBack = binding.buttonGoBack
        val editTextEmail = binding.textInputEditEmail
        val buttonSendEmail = binding.buttonSendEmail

        buttonGoBack.setOnClickListener {
            goBack()
        }

        // 1. Configura a validação de estado do botão ao digitar
        editTextEmail.addTextChangedListener { text ->
            val email = text?.toString()?.trim() ?: ""
            val isValidEmail = isValidEmail(email)
            setButtonState(isValidEmail, enabledButtonColor, disabledButtonColor)
        }

        // ✅ CORREÇÃO ESSENCIAL: Garante que o estado inicial seja definido.
        // Se o campo estiver vazio, o botão começará desabilitado (apagado).
        editTextEmail.text?.let {
            val email = it.toString().trim()
            val isValidEmail = isValidEmail(email)
            setButtonState(isValidEmail, enabledButtonColor, disabledButtonColor)
        }
        // Se o campo estiver vazio, isValidEmail será false, e setButtonState(false, ...) será chamado.

        // 2. Configura o clique do botão para a ação de redirecionamento
        buttonSendEmail.setOnClickListener {
            val email = editTextEmail.text?.toString()?.trim() ?: ""

            if (isValidEmail(email)) {

                // Redirecionamento para a FinallyActivity (Trabalho concluído!)
                val title = "Redefinição enviada!"
                val subtitle = "Boa, agora é só checar o e-mail que foi enviado para você redefinir sua senha e aproveitar os estudos."
                val buttonText = "Voltar ao login"

                val intent = FinallyActivity.newIntent(this, title, subtitle, buttonText)
                startActivity(intent)

                finish() // Finaliza a tela de Forgot Password
            } else {
                // Embora o botão só deva ser clicável se for válido, isto é um fallback
                editTextEmail.error = "Digite um e-mail válido para continuar."
            }
        }
    }

    private fun goBack() {
        finish()
    }

    private fun isValidEmail(email: String): Boolean {
        // Verifica se não está vazio E se o padrão de e-mail é válido
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        val buttonSendEmail = binding.buttonSendEmail

        // 1. Habilita/Desabilita o clique
        buttonSendEmail.isEnabled = enabled

        // 2. Define a cor de fundo (verde se habilitado, cinza se desabilitado)
        val color = if (enabled) enabledColor else disabledColor
        buttonSendEmail.backgroundTintList = ColorStateList.valueOf(color)

        // 3. Define a cor do texto
        val textColor = if (enabled) {
            ContextCompat.getColor(this, R.color.shape_01_white)
        } else {
            ContextCompat.getColor(this, R.color.text_complement)
        }

        buttonSendEmail.setTextColor(textColor)
    }
}