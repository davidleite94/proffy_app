package com.rafael.proffy.ui.teacherList

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityTeacherListBinding

class TeacherListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherListBinding.inflate(layoutInflater)
        val view = binding.root

        replaceFragment(TeacherListFragment())

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNavigationView.onItemSelectedListener = { itemId ->
            when(itemId) {
                R.id.container_teacher_list -> replaceFragment(TeacherListFragment())
                R.id.container_teacher_favorite -> replaceFragment(TeacherFavoriteFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(binding.frameLayout.id, fragment)
        fragmentTransaction.commit()
    }
}


