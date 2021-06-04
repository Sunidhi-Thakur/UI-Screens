package com.example.screens

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.screens.adapters.LanguageAdapter
import com.example.screens.databinding.ActivitySelectLanguageBinding
import com.example.screens.models.LanguageList
import java.util.*


class SelectLanguage : AppCompatActivity(), LocaleListener {

    lateinit var binding: ActivitySelectLanguageBinding
    private val languageList = ArrayList<LanguageList>()
    private lateinit var languageAdapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        languageAdapter = LanguageAdapter(languageList)
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.languageRV.layoutManager = layoutManager
        binding.languageRV.adapter = languageAdapter

        binding.languageRV.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        prepareData()
        languageAdapter.setLocaleListener(this)

        binding.nextButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("SP_INFO", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("LANGUAGE", true)
            editor.apply()
            val intent = Intent(this@SelectLanguage, Slides::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun prepareData() {
        var language = LanguageList("English", "")
        languageList.add(language)
        language = LanguageList("हिंदी", "Hindi")
        languageList.add(language)
        language = LanguageList("ગુજરાતી", "Gujarati")
        languageList.add(language)
        language = LanguageList("मराठी", "Marathi")
        languageList.add(language)
        language = LanguageList("বাংলা", "Bangla")
        languageList.add(language)
        language = LanguageList("ಕನ್ನಡ", "Kannada")
        languageList.add(language)
        language = LanguageList("தமிழ்", "Tamil")
        languageList.add(language)
        language = LanguageList("తెలుగు", "Telugu")
        languageList.add(language)

        languageAdapter.notifyDataSetChanged()
    }

    override fun setLocale(lang: String) {
        val myLocale = Locale(lang)
        val res: Resources = applicationContext.resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, dm)
    }
}
