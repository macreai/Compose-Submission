package com.communicare.genshinindex.repo

import com.communicare.genshinindex.model.Character
import com.communicare.genshinindex.model.CharacterData

class CharacterRepository {

    fun getCharacters(): List<Character>{
        return CharacterData.characters
    }

    fun searchCharacter(query: String): List<Character>{
        return CharacterData.characters.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getCharacterById(id: Int): Character? {
        return CharacterData.characters.find { it.id == id }
    }
}