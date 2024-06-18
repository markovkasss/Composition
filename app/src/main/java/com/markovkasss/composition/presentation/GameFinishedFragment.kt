package com.markovkasss.composition.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.markovkasss.composition.R
import com.markovkasss.composition.databinding.FragmentGameFinishedBinding
import com.markovkasss.composition.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
        bindingTV()
        choosePictureToWinnerOrNot()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindingTV() {
        binding.tvScoreAnswers.text = String.format(
            resources.getString(R.string.score_answers),
            args.gameResult.countOfRightAnswers.toString()
        )
        binding.tvScorePercentage.text = String.format(
            getString(R.string.score_percentage),
            getPercentOfRightAnswers().toString()
        )
        binding.tvRequiredAnswers.text = String.format(
            getString(R.string.required_score),
            args.gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
        binding.tvRequiredPercentage.text = String.format(
            getString(R.string.required_percentage),
            args.gameResult.gameSettings.minPercentOfRightAnswers.toString()
        )
    }

    private fun choosePictureToWinnerOrNot() {
        val drawableWin = resources.getDrawable(R.drawable.fun_ic)
        val drawableLose = resources.getDrawable(R.drawable.sad_ic)
        if (args.gameResult.winner) {
            binding.emojiResult.setImageDrawable(drawableWin)
        } else {
            binding.emojiResult.setImageDrawable(drawableLose)
        }
    }

    private fun getPercentOfRightAnswers() = with(args.gameResult) {
        if (countOfQuestions == 0) {
            0
        } else {
            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
        }
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}