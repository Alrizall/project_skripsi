package com.example.my_e_learning.fitur.materi

import androidx.lifecycle.ViewModel
import com.example.my_e_learning.R
import com.example.my_e_learning.data.MateriInformation
import com.example.my_e_learning.data.TugasInformation
import com.example.my_e_learning.local.PreferenceHelper
import com.example.my_e_learning.util.KeyConstant
import com.example.my_e_learning.util.KeyConstant.ADMIN_KEY
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class MateriViewModel @Inject constructor(private val preferenceHelper: PreferenceHelper) :
    ViewModel() {
    fun materiViewModelProvider(id: Int): MateriInformation {
        val consumeType: Type =
            object : TypeToken<List<MateriInformation>>() {}.type
        val listOfHasil = preferenceHelper.getStringInSharedPreference(KeyConstant.MATERI_KEY)
        if (!listOfHasil.isNullOrEmpty()) {
            val consume: MutableList<MateriInformation> =
                Gson().fromJson<List<MateriInformation>?>(listOfHasil, consumeType).toMutableList()
            return when (id) {
                1 -> {
                    consume.get(0)
                }

                2 -> {
                    consume.get(1)
                }

                3 -> {
                    consume.get(2)
                }

                else -> {
                    consume.get(3)
                }
            }

        } else {
            initQuestion()
            return when (id) {
                1 -> {
                    MateriInformation(
                        1,
                        " Desain antarmuka pengguna (UI) adalah hal pertama yang Anda temui saat menggunakan aplikasi atau mengunjungi situs web. Desain antarmuka pengguna bertanggung jawab atas tampilan produk, interaktivitas, kegunaan, perilaku, dan nuansa keseluruhan. Desain UI dapat menentukan apakah pengguna memiliki pengalaman positif dengan suatu produk, sehingga penting bagi perusahaan dan pembuat konten untuk memahami praktik terbaik desain UI.",
                        "Jenis Desain Interface",
                        R.drawable.ss_materi_1,
                        "User Interface",uri = null
                    )
                }

                2 -> {
                    MateriInformation(
                        2,
                        "Desain pengalaman pengguna (UX) adalah sebuah konsep yang memiliki banyak dimensi, dan mencakup berbagai disiplin ilmu—seperti desain interaksi, arsitektur informasi, desain visual, kegunaan, dan interaksi manusia-komputer.",
                        "Dengan kata lain, desain UX adalah proses mendesain produk (digital atau fisik) yang berguna, mudah digunakan, dan menyenangkan untuk berinteraksi. Ini tentang meningkatkan pengalaman yang dimiliki orang-orang saat berinteraksi dengan produk Anda, dan memastikan mereka menemukan nilai dalam apa yang Anda berikan.",
                        R.drawable.ss_ux,
                        "User Experience",uri = null
                    )
                }

                3 -> {
                    MateriInformation(
                        3,
                        "Desain Tools adalah aplikasi perangkat lunak yang membantu Anda membuat desain. Alat-alat ini membantu Anda membuat wireframes, mockups, prototypes,dan hasil desain lainnya.",
                        "Berikut contoh - contoh desain tools yang paling populer : Figma, Adobe XD, Sketch, Protopie, Framer",
                        R.drawable.img_desain_tools,
                        "Desain Tools",uri = null
                    )
                }

                else -> {
                    MateriInformation(
                        4,
                        " Unsur atau prinsip desain visual meliputi Kontras, Keseimbangan, Penekanan, Gerakan, Ruang Putih, Proporsi, Hierarki, Pengulangan, Irama, Pola, Kesatuan, dan Variasi. Prinsip-prinsip desain ini bekerja sama untuk menciptakan sesuatu yang estetis dan mengoptimalkan pengalaman pengguna.",
                        "Tipografi adalah seni dan teknik mengatur jenis huruf agar bahasa tulisan dapat dibaca, dibaca, dan menarik saat ditampilkan. Penataan tipe meliputi pemilihan tipografi, ukuran titik, panjang garis, spasi baris (leading), dan spasi huruf (tracking), serta pengaturan jarak antar pasangan huruf (kerning).",
                        R.drawable.img_desain_visual,
                        "Visual Desain",uri = null
                    )
                }
            }
        }

    }

    fun initQuestion() {
        val data = listOf(
            MateriInformation(
                1,
                " Desain antarmuka pengguna (UI) adalah hal pertama yang Anda temui saat menggunakan aplikasi atau mengunjungi situs web. Desain antarmuka pengguna bertanggung jawab atas tampilan produk, interaktivitas, kegunaan, perilaku, dan nuansa keseluruhan. Desain UI dapat menentukan apakah pengguna memiliki pengalaman positif dengan suatu produk, sehingga penting bagi perusahaan dan pembuat konten untuk memahami praktik terbaik desain UI.",
                "Jenis Desain Interface",
                R.drawable.ss_materi_1,
                "User Interface", uri = null
            ), MateriInformation(
                2,
                "Desain pengalaman pengguna (UX)adalah sebuah konsep yang memiliki banyak dimensi, dan mencakup berbagai disiplin ilmu—seperti desain interaksi, arsitektur informasi, desain visual, kegunaan, dan interaksi manusia-komputer.",
                "Dengan kata lain, desain UX adalah proses mendesain produk (digital atau fisik) yang berguna, mudah digunakan, dan menyenangkan untuk berinteraksi. Ini tentang meningkatkan pengalaman yang dimiliki orang-orang saat berinteraksi dengan produk Anda, dan memastikan mereka menemukan nilai dalam apa yang Anda berikan.",
                R.drawable.ss_ux,
                "User Experience",uri = null
            ), MateriInformation(
                3,
                "Desain Tools adalah aplikasi perangkat lunak yang membantu Anda membuat desain. Alat-alat ini membantu Anda membuat wireframes, mockups, prototypes,dan hasil desain lainnya.",
                "Berikut contoh - contoh desain tools yang paling populer : Figma, Adobe XD, Sketch, Protopie, Framer",
                R.drawable.img_desain_tools,
                "Desain Tools",uri = null
            ), MateriInformation(
                4,
                " Unsur atau prinsip desain visual meliputi Kontras, Keseimbangan, Penekanan, Gerakan, Ruang Putih, Proporsi, Hierarki, Pengulangan, Irama, Pola, Kesatuan, dan Variasi. Prinsip-prinsip desain ini bekerja sama untuk menciptakan sesuatu yang estetis dan mengoptimalkan pengalaman pengguna.",
                "Tipografi adalah seni dan teknik mengatur jenis huruf agar bahasa tulisan dapat dibaca, dibaca, dan menarik saat ditampilkan. Penataan tipe meliputi pemilihan tipografi, ukuran titik, panjang garis, spasi baris (leading), dan spasi huruf (tracking), serta pengaturan jarak antar pasangan huruf (kerning).",
                R.drawable.img_desain_visual,
                "Visual Desain",uri = null
            )
        )
        val saveValue = Gson().toJson(data)
        preferenceHelper.saveStringInSharedPreference(KeyConstant.MATERI_KEY, saveValue)
    }

    fun updateQuestion(data: MateriInformation) {
        val consumeType: Type =
            object : TypeToken<List<MateriInformation>>() {}.type
        val listOfHasil = preferenceHelper.getStringInSharedPreference(KeyConstant.MATERI_KEY)
        if (!listOfHasil.isNullOrEmpty()) {
            val consume: MutableList<MateriInformation> =
                Gson().fromJson<List<MateriInformation>?>(listOfHasil, consumeType).toMutableList()
            consume.forEachIndexed { index, question ->
                question.takeIf { it.id == data.id }?.let {
                    consume[index] = it.copy(
                        id = data.id,
                        decription1 = data.decription1,
                        decription2 = data.decription2,
                        image = data.image,
                        title = data.title,
                        uri = data.uri
                    )
                }
            }
            val saveValue = Gson().toJson(consume)
            preferenceHelper.saveStringInSharedPreference(KeyConstant.MATERI_KEY, saveValue)
        }

    }

    fun isAdmin(): Boolean {
        return preferenceHelper.getStringInSharedPreference(ADMIN_KEY).isNotEmpty()
    }
}