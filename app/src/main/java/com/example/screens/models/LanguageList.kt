package com.example.screens.models

class LanguageList(local: String?, english: String?){
    private var local: String = local!!
    private var english: String = english!!
    fun getLocal(): String? {
        return local
    }
    fun getEnglish(): String? {
        return english
    }
}