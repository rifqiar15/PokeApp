package com.rifqi.pokeapp.utils

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.rifqi.pokeapp.R
import com.rifqi.pokeapp.databinding.DialogInformationBinding
import com.rifqi.pokeapp.databinding.LoadingBinding

object Dialog {

    fun loading(context: Context) : Dialog {
        var dialog = Dialog(context, R.style.CustomDialog)
        val binding = LoadingBinding.inflate(LayoutInflater.from(context))

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)

        val window = dialog.window
        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
        return dialog
    }


    fun information(context: Context, title: String, content: String, imageResource : Int){
        var dialogInformasi = Dialog(context, R.style.CustomDialog)
        val binding = DialogInformationBinding.inflate(LayoutInflater.from(context))

        dialogInformasi.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogInformasi.setContentView(binding.root)
        dialogInformasi.setCancelable(true)

        binding.tvDialogInformationTitle.text = title
        binding.tvDialogInformationContent.text = content
        binding.ivDialogInformation.setImageResource(imageResource)

        val window = dialogInformasi.window
        window?.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)

        dialogInformasi.show()
    }
}