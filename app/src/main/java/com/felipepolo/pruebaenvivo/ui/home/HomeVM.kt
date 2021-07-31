package com.felipepolo.pruebaenvivo.ui.home

import androidx.lifecycle.*
import com.felipepolo.pruebaenvivo.data.Result
import com.felipepolo.pruebaenvivo.data.local.models.CharactersEntity
import com.felipepolo.pruebaenvivo.data.local.models.toCharacterEntityList
import com.felipepolo.pruebaenvivo.domain.HomeRepositoryInt
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeVM @Inject constructor(
    private val homeRepositoryInt: HomeRepositoryInt,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {


    private var currentPage: Int = 0
    var isFavoriteList = false

    private var _saveCharacterEvent: MutableLiveData<Result<Boolean>> = MutableLiveData()
    val saveCharacterEvent: LiveData<Result<Boolean>> = _saveCharacterEvent

    private var _deleteCharacterEvent: MutableLiveData<Result<CharactersEntity>> = MutableLiveData()
    val deleteCharacterEvent: LiveData<Result<CharactersEntity>> = _deleteCharacterEvent

    private var _characterList: MutableLiveData<Result<List<CharactersEntity>>> = MutableLiveData()
    val characterList: LiveData<Result<List<CharactersEntity>>> = _characterList

    init {
        getCharacters()
    }

    fun getCharacters() {
        _characterList.value = Result.Loading
        currentPage++
        homeRepositoryInt.getCharacters(currentPage)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.errors?.let {
                    _characterList.value = Result.Failure(Exception(it.get(0).message))
                }
                _characterList.value = Result.Success(
                    toCharacterEntityList(it.data!!, firebaseAuth.uid!!)
                )
            }, {
                Result.Failure(Exception(it.message))
            })
    }

    fun saveFavoriteCharacter(charactersEntity: CharactersEntity) {
        viewModelScope.launch {
            homeRepositoryInt.saveFavoriteCharacter(charactersEntity)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _saveCharacterEvent.value = Result.Success(true)
                }, {
                    _saveCharacterEvent.value = Result.Failure(Exception(it.message!!))
                })
        }
    }

    fun deleteFavoriteCharacter(character: CharactersEntity) {
        viewModelScope.launch {
            homeRepositoryInt.deleteFavoriteCharacter(character)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _deleteCharacterEvent.value = Result.Success(character)
                }, {
                    _deleteCharacterEvent.value = Result.Failure(Exception(it.message!!))
                })
        }
    }

    fun switchList(isCheked: Boolean) {
        _characterList.value = Result.Success(emptyList())
        isFavoriteList = isCheked
        if (isCheked) {
            viewModelScope.launch {
                _characterList.value = Result.Loading
                _characterList.value = homeRepositoryInt.getAllFavoriteCharacter()
            }
        } else {
            currentPage = 0
            getCharacters()
        }
    }

    fun logOutUser() {
        firebaseAuth.signOut()
    }
}