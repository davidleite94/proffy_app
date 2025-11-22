package com.rafael.proffy.ui.finally // Novo pacote sugerido

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.rafael.proffy.R
import com.rafael.proffy.ui.login.LoginActivity
import androidx.core.view.WindowCompat
class FinallyActivity : AppCompatActivity() {

    // --- Constantes para as chaves dos Extras da Intent ---
    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_SUBTITLE = "extra_subtitle"
        const val EXTRA_BUTTON_TEXT = "extra_button_text"

        // Função de fábrica para criar uma Intent de forma segura
        fun newIntent(context: Context, title: String, subtitle: String, buttonText: String): Intent {
            return Intent(context, FinallyActivity::class.java).apply {
                putExtra(EXTRA_TITLE, title)
                putExtra(EXTRA_SUBTITLE, subtitle)
                putExtra(EXTRA_BUTTON_TEXT, buttonText)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Certifique-se de que o activity_finally.xml está no seu diretório res/layout
        setContentView(R.layout.activity_finally)

        // Definir a cor da Status Bar como purple (cor de fundo)
        WindowCompat.getInsetsController(window, window.decorView)?.isAppearanceLightStatusBars = false // Mantém os ícones claros
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)
        // 1. Receber os parâmetros da Intent
        val title = intent.getStringExtra(EXTRA_TITLE)
        val subtitle = intent.getStringExtra(EXTRA_SUBTITLE)
        val buttonText = intent.getStringExtra(EXTRA_BUTTON_TEXT)

        // 2. Localizar os componentes da UI
        val tvTitle: TextView = findViewById(R.id.finally_tv_title)
        val tvSubtitle: TextView = findViewById(R.id.finally_tv_subtitle)
        val btnAction: Button = findViewById(R.id.finally_btn_action)

        // 3. Personalizar a tela
        tvTitle.text = title
        tvSubtitle.text = subtitle
        btnAction.text = buttonText

        // 4. Configurar a ação do botão: Redirecionar para Login
        btnAction.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).apply {
                // Limpa o back stack: o usuário não pode voltar para as telas de sucesso/cadastro/forgot
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            startActivity(intent)
            finish()
        }
    }
}