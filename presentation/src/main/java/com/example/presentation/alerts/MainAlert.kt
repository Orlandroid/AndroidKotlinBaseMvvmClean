package com.example.presentation.alerts

import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.example.presentation.R
import com.example.presentation.databinding.AlertDialogMessagesBinding


class MainAlert(
    private val kindOfMessage: Int = SUCCESS_MESSAGE,
    private val messageBody: String,
    private val clickOnAccept: () -> Unit = {},
    private val clickOnCancel: () -> Unit = {},
    private val isTwoButtonDialog: Boolean = false
) :
    DialogFragment() {

    private lateinit var binding: AlertDialogMessagesBinding

    companion object {
        const val SUCCESS_MESSAGE_COLOR = R.color.succes
        const val WARNING_MESSAGE_COLOR = R.color.waring
        const val ERROR_MESSAGE_COLOR = R.color.danger
        const val INFO_MESSAGE_COLOR = R.color.info
        const val SUCCESS_MESSAGE = 0
        const val WARNING_MESSAGE = 1
        const val ERROR_MESSAGE = 2
        const val INFO_MESSAGE = 3

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlertDialogMessagesBinding.inflate(layoutInflater, container, false)
        setUpUi()
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        isCancelable = false
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }


    private fun setUpUi() {
        with(binding) {
            buttonAceptarOneButton.setOnClickListener {
                clickOnAccept()
                dialog?.dismiss()
            }
            buttonAceptar.setOnClickListener {
                clickOnAccept()
                dialog?.dismiss()
            }
            buttonCancelar.setOnClickListener {
                clickOnCancel()
                dialog?.dismiss()
            }
            binding.bodyMessage.text = messageBody
        }
        setKindOfMessage()
        setKindOfView(isTwoButtonDialog)
    }

    private fun setKindOfView(isTwoButtonDialog: Boolean) {
        if (isTwoButtonDialog) {
            with(binding) {
                buttonAceptarOneButton.visibility = View.GONE
                buttonAceptar.visibility = View.VISIBLE
                buttonCancelar.visibility = View.VISIBLE
            }
        } else {
            with(binding) {
                buttonAceptarOneButton.visibility = View.VISIBLE
                buttonAceptar.visibility = View.GONE
                buttonCancelar.visibility = View.GONE
            }
        }
    }

    private fun setKindOfMessage() {
        when (kindOfMessage) {
            0 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(SUCCESS_MESSAGE_COLOR))
                binding.titleHeader.text = "Succes"
            }
            1 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(WARNING_MESSAGE_COLOR))
                binding.titleHeader.text = "Warning"
            }
            2 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(ERROR_MESSAGE_COLOR))
                binding.titleHeader.text = "Error"
            }
            3 -> {
                binding.headerDialog.setCardBackgroundColor(resources.getColor(INFO_MESSAGE_COLOR))
                binding.titleHeader.text = "Info"
            }
        }
    }

}