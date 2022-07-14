package eu.davidknotek.tttoe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import eu.davidknotek.tttoe.game.Player
import eu.davidknotek.tttoe.game.TicTacToe

class TTTViewModel(application: Application): AndroidViewModel(application) {
    private val ticTacToe = TicTacToe()

    var currentPlayer = MutableLiveData(ticTacToe.currentPlayer)
    var endGame = MutableLiveData(ticTacToe.endGame)
    var winTrio = MutableLiveData(ticTacToe.winTrio)

    var playerOneScore = MutableLiveData(ticTacToe.playerOneScore)
    var playerTwoScore = MutableLiveData(ticTacToe.playerTwoScore)
    var drawScore = MutableLiveData(ticTacToe.drawScore)

    val fOneOne = MutableLiveData<Player>()
    val fOneTwo = MutableLiveData<Player>()
    val fOneThree = MutableLiveData<Player>()
    val fTwoOne = MutableLiveData<Player>()
    val fTwoTwo = MutableLiveData<Player>()
    val fTwoThree = MutableLiveData<Player>()
    val fThreeOne = MutableLiveData<Player>()
    val fThreeTwo = MutableLiveData<Player>()
    val fThreeThree = MutableLiveData<Player>()

    init {
        refreshBoard()
    }

    fun play() {
        ticTacToe.resetGame()
        refresh()
    }

    fun move(i: Int, j: Int) {
        ticTacToe.move(i, j)
        refresh()
    }

    private fun refresh() {
        refreshBoard()
        refreshScore()
        currentPlayer.value = ticTacToe.currentPlayer
        endGame.value = ticTacToe.endGame
    }

    private fun refreshBoard() {
        fOneOne.value = ticTacToe.board[0][0]
        fOneTwo.value = ticTacToe.board[0][1]
        fOneThree.value = ticTacToe.board[0][2]
        fTwoOne.value = ticTacToe.board[1][0]
        fTwoTwo.value = ticTacToe.board[1][1]
        fTwoThree.value = ticTacToe.board[1][2]
        fThreeOne.value = ticTacToe.board[2][0]
        fThreeTwo.value = ticTacToe.board[2][1]
        fThreeThree.value = ticTacToe.board[2][2]
    }

    private fun refreshScore() {
        drawScore.value = ticTacToe.drawScore
        playerOneScore.value = ticTacToe.playerOneScore
        playerTwoScore.value = ticTacToe.playerTwoScore
    }
}