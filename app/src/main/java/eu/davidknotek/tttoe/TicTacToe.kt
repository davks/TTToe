package eu.davidknotek.tttoe

class TicTacToe {
    var currentPlayer = Player.X
        private set
    var board = arrayOf<Array<Player>>()
        private set
    var playerOneScore = 0
    var playerTwoScore = 0
    var drawScore = 0

    companion object {
        private const val FIELD_LENGTH = 3
    }

    init {
        createBoard()
    }

    fun move(i: Int, j: Int) {
        if (board[i][j] == Player.EMPTY) {
            board[i][j] = currentPlayer
            changePlayer()
        }
    }

    fun resetGame() {
        currentPlayer = Player.X
        resetBoard()
    }

    private fun createBoard() {
        for (i in 0 until FIELD_LENGTH) {
            var k = arrayOf<Player>()
            for (j in 0 until FIELD_LENGTH) {
                k += Player.EMPTY
            }
            board += k
        }
    }

    private fun resetBoard() {
        for (i in 0 until FIELD_LENGTH) {
            for (j in 0 until FIELD_LENGTH) {
                board[i][j] = Player.EMPTY
            }
        }
    }

    private fun changePlayer() {
        currentPlayer = if (currentPlayer == Player.X) Player.O else Player.X
    }
}