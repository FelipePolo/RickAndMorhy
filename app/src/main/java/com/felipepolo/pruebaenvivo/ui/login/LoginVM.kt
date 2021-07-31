package com.felipepolo.pruebaenvivo.ui.login

import androidx.lifecycle.*
import com.felipepolo.pruebaenvivo.data.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginVM @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private var _userLogged: MutableLiveData<Result<Boolean>> = MutableLiveData()
    var userLogged: LiveData<Result<Boolean>> = _userLogged


    fun logUser(email:String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    _userLogged.value = Result.Success(true)
                }else{
                    _userLogged.value = Result.Failure(task.exception!!)
                }
            }
        }
    }
}