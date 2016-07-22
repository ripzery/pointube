package com.socket9.pointube.extensions

import android.app.ProgressDialog
import android.support.v4.app.Fragment
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

/**
 * Created by ripzery on 7/23/16.
 */
fun Fragment.showLoadingDialog(title: String, msg: String, isCancelable:Boolean = false){
    LoadingDialog.dialog = indeterminateProgressDialog(msg, title)
    LoadingDialog.dialog?.dismiss()
    LoadingDialog.dialog?.setCancelable(isCancelable)
    LoadingDialog.dialog?.show()
}

fun Fragment.hideLoadingDialog(){
    LoadingDialog.dialog?.dismiss()
}

object LoadingDialog {
    var dialog: ProgressDialog? = null
}