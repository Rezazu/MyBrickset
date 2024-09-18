package com.example.mybrickset.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybrickset.data.Result
import com.example.mybrickset.data.local.datastore.AuthPreferences
import com.example.mybrickset.domain.usecase.BricksetUseCases
import com.example.mybrickset.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(
    private val bricksetUseCases: BricksetUseCases,
    private val pref: AuthPreferences
):ViewModel() {

//    private val _isLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)
//    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val hash: Flow<String> = pref.getUserHash()

    private val _loggedIn: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loggedIn: StateFlow<LoginState> get() = _loggedIn

    private val _onUsernameTextChange: MutableStateFlow<String> = MutableStateFlow("")
    val onUsernameTextChange: StateFlow<String> = _onUsernameTextChange

    private val _onPasswordTextChange: MutableStateFlow<String> = MutableStateFlow("")
    val onPasswordTextChange: StateFlow<String> = _onPasswordTextChange

    init {
        isLogginChecked()
    }

    fun isLogginChecked() = viewModelScope.launch {
        if (hash.first().isNotEmpty()) {
            _loggedIn.value = LoginState(isLoggedIn = true)
        }
    }

    fun login(username:String, password: String) {
        _loggedIn.value = LoginState(message = "")
        bricksetUseCases.login(username, password).onEach { result ->
            when(result) {
                is Result.Error -> {
                    _loggedIn.value = LoginState(error = result.error)
                }
                is Result.Loading -> {

                }
                is Result.Success -> {
                    if (result.data.status == "success") {
                        pref.saveUserHash(result.data.hash)
                        _loggedIn.value = LoginState(isLoggedIn = true)
                    }
                    if (result.data.message != null) {
                        _loggedIn.value = LoginState(message = result.data.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun onUsernameTextChange(usernameText: String) {
        _onUsernameTextChange.value = usernameText
    }

    fun onPasswordTextChange(passwordText: String) {
        _onPasswordTextChange.value = passwordText
    }
}

data class LoginState (
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val message: String = "",
    val error: String = ""
)
