package com.communicare.genshinindex.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.communicare.genshinindex.repo.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.communicare.genshinindex.model.Character
import kotlinx.coroutines.launch

class CharactersViewModel(private val repository: CharacterRepository): ViewModel() {

    private val _groupedCharacters = MutableStateFlow(
        repository.getCharacters()
            .sortedBy { it.weapon }
            .groupBy { it.weapon }
    )
    val groupedCharacter: StateFlow<Map<String, List<Character>>> get() = _groupedCharacters

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _character = MutableStateFlow<Character?>(null)
    val character: StateFlow<Character?> = _character

    fun search(newQuery: String){
        _query.value = newQuery
        if (newQuery.isEmpty()){
            _groupedCharacters.value = repository.searchCharacter(_query.value)
                .sortedBy { it.weapon }
                .groupBy { it.weapon }
        } else {
            _groupedCharacters.value = repository.searchCharacter(_query.value)
                .sortedBy { it.name }
                .groupBy { it.name }
        }
    }

    fun getCharacterById(id: Int){
        viewModelScope.launch {
            val character = repository.getCharacterById(id)
            _character.value = character
        }
    }
}