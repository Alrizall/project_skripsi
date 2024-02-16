package com.example.my_e_learning.fitur.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.my_e_learning.R
import com.example.my_e_learning.data.QuizInformation
import com.example.my_e_learning.databinding.FragmentQuizBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentQuiz : Fragment() {
    private var correctAnswer = 0
    private val viewModel: QuizViewModel by viewModels()
    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private var userAnswer: String? = null
    private var questionIndex = 0
    private lateinit var currentQuestion: QuizInformation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRadioButton()
    }

    private fun initQuestion(questionIndex: Int) {
        currentQuestion = viewModel.getQuestion(questionIndex)
        binding.tvQuestionIndex.text = (questionIndex + 1).toString()
        binding.tvQuestion.text = currentQuestion.question
        binding.firstAnswerRadioButton.text = currentQuestion.answer1
        binding.secondAnswerRadioButton.text = currentQuestion.answer2
        binding.thirdAnswerRadioButton.text = currentQuestion.answer3
        binding.fourthAnswerRadioButton.text = currentQuestion.answer4
        binding.btnAnswer.visibility = View.VISIBLE
    }

    private fun initView() {
        val numQuestions = viewModel.provideQuiz().size
        initQuestion(questionIndex)

        with(binding) {
            tvBackWuiz.setOnClickListener{
                findNavController().popBackStack()
            }
            btnAnswer.setOnClickListener {
                if (currentQuestion.correctAnswer.equals(userAnswer, true)) {
                    questionIndex++
                    correctAnswer++
                    initVisibility(
                        state = true,
                        questionIndex = questionIndex,
                        numQuestions = numQuestions
                    )
                    btnNext.setOnClickListener {
                        if ((questionIndex) < numQuestions) {
                            radioGroupRoot.clearCheck()
                            lnAnswer.visibility = View.GONE
                            initQuestion(questionIndex = questionIndex)
                        } else {
                            viewModel.saveNilai(correctAnswer)
                            findNavController().navigate(R.id.action_fragmentQuiz_to_fragmentNilai)
                        }

                    }

                } else {
                    questionIndex++
                    initVisibility(
                        state = false,
                        questionIndex = questionIndex,
                        numQuestions = numQuestions
                    )
                    btnNext.setOnClickListener {
                        if (questionIndex < numQuestions) {
                            radioGroupRoot.clearCheck()
                            lnAnswer.visibility = View.GONE
                            initQuestion(questionIndex)
                        } else {
                            viewModel.saveNilai(correctAnswer)
                            findNavController().navigate(R.id.action_fragmentQuiz_to_fragmentNilai)
                        }
                    }
                }
            }
        }
    }

    private fun initVisibility(state: Boolean, questionIndex: Int, numQuestions: Int) {
        if (questionIndex < numQuestions) {
            currentQuestion = viewModel.getQuestion(questionIndex - 1)
            binding.tvCorrectAnswer.text = currentQuestion.correctAnswer
        }

        if ((questionIndex) == numQuestions) {
            binding.tvAnswerStatus.text = "Semua soal telah terjawab"
            binding.tvCorrectAnswer.text = "Lihat hasil"
        } else {
            if (state) {
                binding.tvAnswerStatus.text = "Jawaban anda benar"
                binding.ivAnswerStatus.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_right_answer
                    )
                )

            } else {
                binding.tvAnswerStatus.text = "Jawaban yang benar adalah"
                binding.ivAnswerStatus.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_wrong_answer
                    )
                )
            }
        }

        binding.lnAnswer.visibility = View.VISIBLE
        binding.btnAnswer.visibility = View.GONE
        binding.btnNext.text =
            if ((questionIndex) == numQuestions) "Lihat hasil" else "Soal Berikutnya"
    }


    private fun initRadioButton() {
        binding.radioGroupRoot.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.firstAnswerRadioButton -> userAnswer =
                    binding.firstAnswerRadioButton.text.toString()

                R.id.secondAnswerRadioButton -> userAnswer =
                    binding.secondAnswerRadioButton.text.toString()

                R.id.thirdAnswerRadioButton -> userAnswer =
                    binding.thirdAnswerRadioButton.text.toString()

                R.id.fourthAnswerRadioButton -> userAnswer =
                    binding.fourthAnswerRadioButton.text.toString()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        correctAnswer = 0
        questionIndex = 0
        _binding = null
    }
}




