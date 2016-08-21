package com.socket9.pointube.utils

import com.rengwuxian.materialedittext.validation.METValidator
import android.util.Patterns
import com.socket9.pointube.R
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 7/21/16.
 */
object ValidatorUtil : AnkoLogger{
    fun provideEmailValidator(): METValidator = object : METValidator(ContextUtil.context!!.getString(R.string.validate_email)){
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty || !Patterns.EMAIL_ADDRESS.matcher(text).matches()
            return !notValid
        }
    }

    fun providePasswordValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_password)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty || text.length < 4
            return !notValid
        }
    }

    fun provideRepeatPasswordValidator(password: CharSequence) = object : METValidator(ContextUtil.context!!.getString(R.string.validate_repeat_password)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty || !password.toString().equals(text.toString())
            return !notValid
        }
    }

    fun provideFirstNameEnValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_first_name)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideLastNameEnValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_last_name)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideFirstNameThValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_first_name)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideLastNameThValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_last_name)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideCitizenIdValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_validate_citizen_id)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            return text.length == 13
        }
    }

    fun providePassportValidator() = object : METValidator(ContextUtil.context!!.getString(R.string.validate_passport)) {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            return text.length >= 10
        }
    }
}