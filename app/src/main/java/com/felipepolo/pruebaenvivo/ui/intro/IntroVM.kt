package com.felipepolo.pruebaenvivo.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.felipepolo.pruebaenvivo.data.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.lang.Exception
import javax.inject.Inject

class IntroVM @Inject constructor(private val firebaseAuth: FirebaseAuth): ViewModel() {

    private var _user: MutableLiveData<Result<FirebaseUser?>> = MutableLiveData()
    var user: LiveData<Result<FirebaseUser?>> = _user

    fun checkUserLog() {
        if(firebaseAuth.currentUser != null){
            _user.value = Result.Success(firebaseAuth.currentUser)
        }else{
            _user.value = Result.Failure(Exception("you must login first"))
        }

    }

}