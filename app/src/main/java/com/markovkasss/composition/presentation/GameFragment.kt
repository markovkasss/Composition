package com.markovkasss.composition.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markovkasss.composition.R
import com.markovkasss.composition.databinding.FragmentGameBinding
import com.markovkasss.composition.domain.entity.GameResult
import com.markovkasss.composition.domain.entity.GameSettings
import com.markovkasss.composition.domain.entity.Level

class GameFragment : Fragment() {

    private val viewModel: GameViewModel by lazy {
        val args = GameFragmentArgs.fromBundle(requireArguments())
        ViewModelProvider(
            this,
            GameViewModelFactory(
                args.level,
                requireActivity().application
            )
        )[GameViewModel::class.java]
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel() {
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections.actionGameFragment2ToGameFinishedFragment22(
                gameResult
            )
        )
    }
}