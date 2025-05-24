package com.joel.flySyApp.feature.auth.signin.viewmodel

import android.util.Patterns
import androidx.lifecycle.*
import com.joel.flySyApp.feature.auth.signin.model.SignInData
import com.joel.flySyApp.feature.auth.signin.model.SignInRepository
import com.joel.flySyApp.feature.auth.signin.model.TokenResponse
import kotlinx.coroutines.delay


class SignInViewModel: ViewModel() {

    private val _signInRepository = SignInRepository()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _signInEnabled = MutableLiveData<Boolean>()
    val signInEnabled: LiveData<Boolean> = _signInEnabled

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onEmailChange(it: String) {
        _email.value = it
        _signInEnabled.value = isValidEmail() && isValidPassword()
    }

    fun onPasswordChange(it: String) {
        _password.value = it
        _signInEnabled.value = isValidEmail() && isValidPassword()
    }

    private fun isValidPassword(): Boolean {
        if (_password.value != null) return _password.value!!.length > 6
        return false
    }

    private fun isValidEmail(): Boolean {
        if (_email.value != null) return Patterns.EMAIL_ADDRESS.matcher(_email.value!!).matches()
        return false
    }

    suspend fun onSignInSelected(
        onSuccess: (TokenResponse) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        _isLoading.value = true
        try {
            val result = _signInRepository.signIn(_email.value!!,_password.value!!)
            onSuccess(result)
        } catch (e: Exception) {
            onError(e)
        }
        finally{
            _isLoading.value = false
        }

    }

}