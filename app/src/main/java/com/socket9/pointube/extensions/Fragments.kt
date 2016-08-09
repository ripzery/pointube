package com.socket9.pointube.extensions

import android.app.ProgressDialog
import android.support.v4.app.Fragment
import com.socket9.pointube.R
import org.jetbrains.anko.support.v4.indeterminateProgressDialog

/**
 * Created by ripzery on 7/23/16.
 */
fun Fragment.showLoadingDialog(title: String, msg: String, isCancelable: Boolean = false) {
    LoadingDialog.dialog = indeterminateProgressDialog(msg, title)
    LoadingDialog.dialog?.dismiss()
    LoadingDialog.dialog?.setCancelable(isCancelable)
    LoadingDialog.dialog?.show()
}

fun Fragment.hideLoadingDialog() {
    LoadingDialog.dialog?.dismiss()
}

fun Fragment.replaceFragment(container: Int = R.id.fragmentListContainer, fragment: Fragment) {
    childFragmentManager.beginTransaction().replace(container, fragment).commit()
}

object LoadingDialog {
    var dialog: ProgressDialog? = null
}