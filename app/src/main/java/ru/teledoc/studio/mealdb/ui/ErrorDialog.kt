package ru.teledoc.studio.mealdb.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ErrorDialog : DialogFragment() {
    companion object {
        val TAG = ErrorDialog::class.java.name + "TAG"
        private const val EXTRA_MESSAGE = "extra_message"

        fun create(message: String) = ErrorDialog().apply {
            this.arguments = Bundle().apply {
                this.putString(EXTRA_MESSAGE, message)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle("Ошибка")
            .setMessage(arguments?.getString(EXTRA_MESSAGE))
            .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
            .create()
}