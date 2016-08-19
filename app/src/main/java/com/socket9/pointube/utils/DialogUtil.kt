package com.socket9.pointube.utils

import android.content.Context
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.socket9.pointube.R

/**
 * Created by ripzery on 8/19/16.
 */
object DialogUtil {
    fun getChangePasswordDialog(context: Context, title: String, positive: String = "OK", negative: String = "Cancel", action: (View) -> Unit): MaterialDialog? {
        return MaterialDialog.Builder(context)
                .title(title)
                .customView(R.layout.dialog_change_password, true)
                .positiveText(positive)
                .negativeText(negative)
                .autoDismiss(false)
                .onPositive { materialDialog, dialogAction ->
                    action(materialDialog.customView!!)
                }
                .build()
    }

    fun getForgotPasswordDialog(context: Context, title: String, positive: String = "OK", negative: String = "Cancel", action: (String) -> Unit): MaterialDialog? {
//        val userEmail = SharedPrefUtil.loadLoginResult()!!.email!!
        return MaterialDialog.Builder(context)
                .title(title)
                .positiveText(positive)
                .negativeText(negative)
                .input("Your email", /* user email */ "", false) { dialog, input ->
                    action(input.toString())
                }
                .onNegative { materialDialog, dialogAction ->
                    materialDialog.dismiss()
                }
                .build()
    }

    fun getForgotPasswordOtpDialog(context: Context, title: String, positive: String = "OK", negative: String = "Cancel", action: (View) -> Unit): MaterialDialog {
        return MaterialDialog.Builder(context)
                .title(title)
                .positiveText(positive)
                .negativeText(negative)
                .customView(R.layout.dialog_forgot_password_otp, true)
                .onPositive { materialDialog, dialogAction ->
                    action(materialDialog.view!!)
                }
                .build()
    }
}