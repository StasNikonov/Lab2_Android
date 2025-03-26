package com.example.lab2android

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit

class InputFragment : Fragment(R.layout.input_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editText = view.findViewById<EditText>(R.id.edit_text)
        val buttonOk = view.findViewById<Button>(R.id.button_ok)
        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)

        buttonOk.setOnClickListener {
            val selectedSizeId = radioGroup.checkedRadioButtonId
            if (selectedSizeId == -1) {
                Toast.makeText(requireContext(), "Будь ласка, виберіть розмір шрифту", Toast.LENGTH_SHORT).show()
            }

            val selectedSize = when (selectedSizeId) {
                R.id.radio_12 -> 12
                R.id.radio_16 -> 16
                R.id.radio_20 -> 20
                R.id.radio_24 -> 24
                else -> 16
            }

            val text = editText.text.toString().trim()
            if (text.isEmpty()) {
                Toast.makeText(requireContext(), "Будь ласка, введіть текст", Toast.LENGTH_SHORT).show()
            } else {
                parentFragmentManager.commit {
                    replace(R.id.fragment_container, OutputFragment.newInstance(text, selectedSize))
                    addToBackStack(null)
                }
            }
        }
    }
}