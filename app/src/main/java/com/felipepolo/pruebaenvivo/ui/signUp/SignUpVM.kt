package com.felipepolo.pruebaenvivo.ui.signUp

import androidx.lifecycle.*
import com.felipepolo.pruebaenvivo.data.Result
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignUpVM @Inject constructor(private val firebaseAuth: FirebaseAuth): ViewModel() {

    private var _userRegistered: MutableLiveData<Result<Boolean>> = MutableLiveData()
    var userRegistered: LiveData<Result<Boolean>> = _userRegistered


    fun createNewUser(email:String, password:String, rePassword:String){
        viewModelScope.launch(Dispatchers.IO) {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    _userRegistered.value = Result.Success(true)
                }else {
                    _userRegistered.value = Result.Failure(task.exception!!)
                }
            }
        }
    }
}