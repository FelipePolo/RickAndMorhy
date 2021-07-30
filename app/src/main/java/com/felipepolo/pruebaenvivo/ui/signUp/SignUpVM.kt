package com.felipepolo.pruebaenvivo.ui.signUp

import androidx.lifecycle.*
import com.felipepolo.pruebaenvivo.data.Result
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class SignUpVM @Inject constructor(): ViewModel() {

    private var _userRegistered: MutableLiveData<Result<Boolean>> = MutableLiveData()
    var userRegistered: LiveData<Result<Boolean>> = _userRegistered


    fun createNewUser(email:String, password:String, rePassword:String){
        viewModelScope.launch(Dispatchers.IO) {
            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    _userRegistered.value = Result.Success(true)
                }else {
                    _userRegistered.value = Result.Failure(task.exception!!)
                }
            }
        }
    }
}