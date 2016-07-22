package com.socket9.pointube.utils

import com.rengwuxian.materialedittext.validation.METValidator
import android.util.Patterns
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * Created by ripzery on 7/21/16.
 */
object ValidatorUtil : AnkoLogger{
    fun provideEmailValidator(): METValidator = object : METValidator("Invalid email"){
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty || !Patterns.EMAIL_ADDRESS.matcher(text).matches()
            return !notValid
        }
    }

    fun providePasswordValidator() = object : METValidator("Password must be at least 4 characters") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty || text.length < 4
            return !notValid
        }
    }

    fun provideRepeatPasswordValidator(password: CharSequence) = object : METValidator("Repeat password is not match with password") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty || !password.toString().equals(text.toString())
            return !notValid
        }
    }

    fun provideFirstNameEnValidator() = object : METValidator("First name must not be empty") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideLastNameEnValidator() = object : METValidator("Last name must not be empty") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideFirstNameThValidator() = object : METValidator("First name must not be empty") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideLastNameThValidator() = object : METValidator("Last name must not be empty") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            val notValid = isEmpty
            return !notValid
        }
    }

    fun provideCitizenIdValidator() = object : METValidator("CitizenID must be 13 characters") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            return text.length == 13
        }
    }

    fun providePassportValidator() = object : METValidator("Passport must be at least 10 characters") {
        override fun isValid(text: CharSequence, isEmpty: Boolean): Boolean {
            return text.length >= 10
        }
    }
}