package com.rafael.proffy.ui.navigationBottomCustom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ViewCustomButtomNavigationBinding

class NavigationBottomCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
): LinearLayout(context, attrs) {

    private val binding = ViewCustomButtomNavigationBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var selectedItemId: Int = R.id.container_teacher_list

    var onItemSelectedListener: ((Int) -> Unit)? = null

    init {
        setupListeners()
        updateSelection(R.id.container_teacher_list)
    }

    private fun setupListeners() {
        binding.containerTeacherList.setOnClickListener { updateSelection(R.id.container_teacher_list) }
        binding.containerTeacherFavorite.setOnClickListener { updateSelection(R.id.container_teacher_favorite) }
    }

    private fun updateSelection(itemId: Int) {
        selectedItemId = itemId

        val items = listOf(binding.containerTeacherList, binding.containerTeacherFavorite)
        val icons = listOf(binding.iconPresentation, binding.iconFavorite)
        val texts = listOf(binding.textProffy, binding.textFavorite)
        val ids = listOf(R.id.container_teacher_list, R.id.container_teacher_favorite)

        for (i in items.indices) {
            val selected = ids[i] == itemId
            items[i].isSelected = selected
            val colorText = ContextCompat.getColor(context,
                if (selected) R.color.text_title else R.color.text_input)
            val colorIcon = ContextCompat.getColor(context,
                if (selected) R.color.purple else R.color.text_input)

            icons[i].setColorFilter(colorIcon)
            texts[i].setTextColor(colorText)
        }
        onItemSelectedListener?.invoke(itemId)
    }

}



