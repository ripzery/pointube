package com.socket9.pointube.utils

import android.content.Context
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.socket9.pointube.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 8/19/16.
 */
object DialogUtil : AnkoLogger{
    fun getChangePasswordDialog(context: Context, title: String,
                                positive: String = ContextUtil.context!!.getString(R.string.dialog_default_ok),
                                negative: String = ContextUtil.context!!.getString(R.string.dialog_default_cancel),
                                action: (View) -> Unit): MaterialDialog? {
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

    fun getChangeLanguageDialog(context: Context, title: String,
                                positive: String = ContextUtil.context!!.getString(R.string.dialog_default_ok),
                                negative: String = ContextUtil.context!!.getString(R.string.dialog_default_cancel),
                                action: (Int) -> Unit): MaterialDialog? {
        val defaultLanguage = if(SharedPrefUtil.isEnglish()) 0 else 1

        info { defaultLanguage }

        return MaterialDialog.Builder(context)
                .title(title)
                .items(ContextUtil.context!!.getString(R.string.dialog_language_list_english), ContextUtil.context!!.getString(R.string.dialog_language_list_thai))
                .positiveText(positive)
                .itemsCallbackSingleChoice(defaultLanguage) { dialog, itemView, which, text ->
                    action(which)
                    return@itemsCallbackSingleChoice true
                }
                .build()
    }

    fun getForgotPasswordDialog(context: Context,
                                title: String,
                                positive: String = ContextUtil.context!!.getString(R.string.dialog_default_ok),
                                negative: String = ContextUtil.context!!.getString(R.string.dialog_default_cancel),
                                action: (String) -> Unit): MaterialDialog? {
//        val userEmail = SharedPrefUtil.loadLoginResult()!!.email!!
        return MaterialDialog.Builder(context)
                .title(title)
                .positiveText(positive)
                .negativeText(negative)
                .autoDismiss(false)
                .input("Your email", /* user email */ "", false) { dialog, input ->
                    action(input.toString())
                }
                .onNegative { materialDialog, dialogAction ->
                    materialDialog.dismiss()
                }
                .build()
    }

    fun getForgotPasswordOtpDialog(context: Context,
                                   title: String,
                                   positive: String = ContextUtil.context!!.getString(R.string.dialog_default_ok),
                                   negative: String = ContextUtil.context!!.getString(R.string.dialog_default_cancel),
                                   action: (View) -> Unit): MaterialDialog {
        return MaterialDialog.Builder(context)
                .title(title)
                .positiveText(positive)
                .negativeText(negative)
                .autoDismiss(false)
                .customView(R.layout.dialog_forgot_password_otp, true)
                .onPositive { materialDialog, dialogAction ->
                    action(materialDialog.view!!)
                }
                .build()
    }

    fun getForgotPasswordResetDialog(context: Context, title: String,
                                     positive: String = ContextUtil.context!!.getString(R.string.login_dialog_forget_password_positive),
                                     negative: String = ContextUtil.context!!.getString(R.string.dialog_default_cancel),
                                     action: (View) -> Unit): MaterialDialog {
        return MaterialDialog.Builder(context)
                .title(title)
                .positiveText(positive)
                .negativeText(negative)
                .autoDismiss(false)
                .customView(R.layout.dialog_forgot_password_change_password, true)
                .onPositive { materialDialog, dialogAction ->
                    action(materialDialog.view!!)
                }
                .build()
    }
}