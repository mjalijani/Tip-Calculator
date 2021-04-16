package ir.training.tipcalculator.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import ir.training.tipcalculator.R

class SaveDialogFragment : DialogFragment() {

    interface Callback {
        fun onSaveTip(name: String)
    }

    private var saveTipCallBack: SaveDialogFragment.Callback? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        saveTipCallBack = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallBack = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val saveDialog = context?.let {
            val editText = EditText(it)
            editText.id = viewId
            editText.hint = getString(R.string.enter_location)

            AlertDialog.Builder(it)
                .setView(editText)
                .setNegativeButton(getString(R.string.cancel), null)
                .setPositiveButton(getString(R.string.save)) { _, _ -> onSave(editText) }
                .create()
        }

        return saveDialog!!
    }

    companion object{
        val viewId = View.generateViewId()
    }

    private fun onSave(editText: EditText) {
        val text = editText.text.toString()
        if (text.isNotEmpty()) saveTipCallBack?.onSaveTip(text)
    }
}