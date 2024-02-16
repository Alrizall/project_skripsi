package com.example.my_e_learning.fitur.quiz

import androidx.lifecycle.ViewModel
import com.example.my_e_learning.R
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.data.NilaiInformation
import com.example.my_e_learning.data.QuizInformation
import com.example.my_e_learning.local.PreferenceHelper
import com.example.my_e_learning.util.KeyConstant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper): ViewModel() {

    fun provideQuiz(): List<QuizInformation> {
        return listOf(
            QuizInformation(
                id = 1,
                question = "Manakah dari berikut ini yang merupakan contoh UI?",
                answer1 = "Kunci (untuk membuka pintu)",
                answer2 = "Remot TV",
                answer3 = "Tombol (dalam aplikasi)",
                answer4 = "Semua yang di atas",
                correctAnswer = "Semua yang di atas",
            ),
            QuizInformation(
                id = 2,
                question = "Manakah urutan Desain Thinking yang benar?",
                answer1 = "Riset > Analisis > Definisikan > Desain > Uji",
                answer2 = "Tentukan > Penelitian > Analisis > Desain > Uji",
                answer3 = "Tentukan > Penelitian > Desain > Uji > Analisis",
                answer4 = "Desain > Uji > Analisis > Tentukan > Penelitian",
                correctAnswer = "Riset > Analisis > Definisikan > Desain > Uji",
            ),
            QuizInformation(
                id = 3,
                question = "Manakah dari berikut yang benar?",
                answer1 = "Wireframe - Mockup sketsa kertas & pena - Model dengan Warna dan Visual Prototipe UX - Model interaktif",
                answer2 = "Mockup - Sketsa kertas & pena wireframe - Model dengan Warna dan Visual Prototipe UX - Model interaktif",
                answer3 = "Wireframe - Desain dasar produk Mockup - Desain pperantara produk Prototipe -  Desain produk tingkat lanjut",
                answer4 = "Market - Desain perantara produk Wirefrrame - Desain dasar produk prototipe -  Desain produk tingkat lanjut",
                correctAnswer = "Wireframe - Desain dasar produk Mockup - Desain pperantara produk Prototipe -  Desain produk tingkat lanjut",
            ),
            QuizInformation(
                id = 4,
                question = "Istilah (Pengalaman Pengguna) diciptakan oleh siapa?",
                answer1 = "Bill Gates",
                answer2 = "Jakob",
                answer3 = "Elon Musk",
                answer4 = "Don Norman",
                correctAnswer = "Don Norman",
            ),
            QuizInformation(
                id = 5,
                question = "Perusahaan mana yang membuat kerangka Bootstrap UI?",
                answer1 = "Apel",
                answer2 = "Google",
                answer3 = "Microsoft",
                answer4 = "Twitter",
                correctAnswer = "Twitter",
            ),
        )
    }

    fun saveNilai (nilai : Int){
        val consumeType: Type =
            object : TypeToken<List<NilaiInformation>>() {}.type
        val userName = preferenceHelper.getStringInSharedPreference(KeyConstant.USERNAME_KEY)
        val listOfHasil = preferenceHelper.getStringInSharedPreference("$userName${userName.first()}")
        if (!listOfHasil.isNullOrEmpty()) {
            val consume: MutableList<NilaiInformation> =
                Gson().fromJson<List<NilaiInformation>?>(listOfHasil, consumeType).toMutableList()
            consume.add(NilaiInformation(consume.last().id + 1, "hasil nilai =", nilai * 20))
            val saveValue = Gson().toJson(consume)
            preferenceHelper.saveStringInSharedPreference("$userName${userName.first()}", saveValue)

        }else {
           val listOfHasil = listOf(NilaiInformation(1, "hasil nilai =", nilai * 20))
            val saveValue = Gson().toJson(listOfHasil)
            preferenceHelper.saveStringInSharedPreference("$userName${userName.first()}", saveValue)
        }
    }

    fun getQuestion(id:Int): QuizInformation {
        return provideQuiz()[id]
    }
}