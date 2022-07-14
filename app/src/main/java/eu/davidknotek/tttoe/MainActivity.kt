package eu.davidknotek.tttoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import eu.davidknotek.tttoe.databinding.ActivityMainBinding
import eu.davidknotek.tttoe.game.Player
import eu.davidknotek.tttoe.viewmodel.TTTViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = ""
    private lateinit var binding: ActivityMainBinding
    private lateinit var tttViewModel: TTTViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tttViewModel = ViewModelProvider(this).get(TTTViewModel::class.java)

        setObservers()
        setListeners()
    }

    private fun setObservers() {
        tttViewModel.playerOneScore.observe(this) {
            binding.playerOneScoreTv.text = it.toString()
        }

        tttViewModel.playerTwoScore.observe(this) {
            binding.playerTwoScoreTv.text = it.toString()
        }

        tttViewModel.drawScore.observe(this) {
            binding.drawScoreTv.text = it.toString()
        }

        tttViewModel.currentPlayer.observe(this) {
            when (it) {
                Player.X -> {
                    binding.playerOneLine.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
                    binding.playerTwoLine.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    }
                Player.O -> {
                    binding.playerOneLine.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    binding.playerTwoLine.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_200))
                }
                else -> {
                    binding.playerOneLine.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                    binding.playerTwoLine.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
                }
            }
        }

        tttViewModel.endGame.observe(this) {
            binding.playBtn.isEnabled = it
        }

        tttViewModel.fOneOne.observe(this) {
            binding.oneOne.setImageResource(drawableField(it))
        }

        tttViewModel.fOneTwo.observe(this) {
            binding.oneTwo.setImageResource(drawableField(it))
        }

        tttViewModel.fOneThree.observe(this) {
            binding.oneThree.setImageResource(drawableField(it))
        }

        tttViewModel.fTwoOne.observe(this) {
            binding.twoOne.setImageResource(drawableField(it))
        }

        tttViewModel.fTwoTwo.observe(this) {
            binding.twoTwo.setImageResource(drawableField(it))
        }

        tttViewModel.fTwoThree.observe(this) {
            binding.twoThree.setImageResource(drawableField(it))
        }

        tttViewModel.fThreeOne.observe(this) {
            binding.threeOne.setImageResource(drawableField(it))
        }

        tttViewModel.fThreeTwo.observe(this) {
            binding.threeTwo.setImageResource(drawableField(it))
        }

        tttViewModel.fThreeThree.observe(this) {
            binding.threeThree.setImageResource(drawableField(it))
        }
    }

    private fun setListeners() {
        binding.oneOne.setOnClickListener { setField(0, 0) }
        binding.oneTwo.setOnClickListener { setField(0, 1) }
        binding.oneThree.setOnClickListener { setField(0, 2) }
        binding.twoOne.setOnClickListener { setField(1, 0) }
        binding.twoTwo.setOnClickListener { setField(1, 1) }
        binding.twoThree.setOnClickListener { setField(1, 2) }
        binding.threeOne.setOnClickListener { setField(2, 0) }
        binding.threeTwo.setOnClickListener { setField(2, 1) }
        binding.threeThree.setOnClickListener { setField(2, 2) }

        binding.playBtn.setOnClickListener { play() }
    }

    private fun setField(i: Int, j: Int) {
        tttViewModel.move(i, j)
    }

    private fun drawableField(player: Player): Int {
        return when (player) {
            Player.X -> R.drawable.cross
            Player.O -> R.drawable.circle
            else -> R.drawable.empty
        }
    }

    private fun play() {
        tttViewModel.play()
    }

}