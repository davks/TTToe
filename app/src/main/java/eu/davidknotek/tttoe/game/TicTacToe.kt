package eu.davidknotek.tttoe.game

class TicTacToe {
    private var playerWin = Player.EMPTY
    var currentPlayer = Player.X
        private set
    var board = arrayOf<Array<Player>>()
        private set
    var playerOneScore = 0

    var playerTwoScore = 0
    var drawScore = 0
    var endGame = false

    var winTrio = WinTrio.NONE

    companion object {
        private const val FIELD_LENGTH = 3
    }

    init {
        createBoard()
    }

    fun move(i: Int, j: Int) {
        if (!endGame && board[i][j] == Player.EMPTY) {
            board[i][j] = currentPlayer
            compareWinner()
            checkFullBoard()
            setScore()
            changePlayer()
        }
    }

    fun resetGame() {
        currentPlayer = Player.X
        playerWin = Player.EMPTY
        endGame = false
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
        currentPlayer = when {
            endGame -> Player.EMPTY
            currentPlayer == Player.X -> Player.O
            else -> Player.X
        }
    }

    private fun checkFullBoard() {
        var playedFields = 0
        for (i in 0 until FIELD_LENGTH) {
            for (j in 0 until FIELD_LENGTH) {
                if (board[i][j] != Player.EMPTY) {
                    playedFields++
                }
            }
        }
        if (playedFields == FIELD_LENGTH * FIELD_LENGTH) {
            endGame = true
        }
    }

    private fun compareWinner() {
        checkWinnerHorizontallyAndVertically()
        checkWinnerDiagonaly()

        if (playerWin != Player.EMPTY) {
            endGame = true
        }
    }

    private fun checkWinnerHorizontallyAndVertically() {
        for (i in 0 until FIELD_LENGTH) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][2] != Player.EMPTY) {
                playerWin = board[i][0]
                winTrio = checkWinRow(i)
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[2][i] != Player.EMPTY) {
                playerWin = board[0][i]
                winTrio = checkWinCol(i)
            }
        }
    }

    private fun checkWinnerDiagonaly() {
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[2][2] != Player.EMPTY) {
            playerWin = board[0][0]
            winTrio = WinTrio.DIAGONALLY_LEFT
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[2][0] != Player.EMPTY) {
            playerWin = board[0][2]
            winTrio = WinTrio.DIAGONALLY_RIGHT
        }
    }

    private fun checkWinRow(row: Int): WinTrio {
        return when (row) {
            0 -> WinTrio.ROW_ONE
            1 -> WinTrio.ROW_TWO
            else -> WinTrio.ROW_THREE
        }
    }

    private fun checkWinCol(col: Int): WinTrio {
        return when (col) {
            0 -> WinTrio.COL_ONE
            1 -> WinTrio.COL_TWO
            else -> WinTrio.COL_THREE
        }
    }

    private fun setScore() {
        if (endGame) {
            when (playerWin) {
                Player.X -> playerOneScore++
                Player.O -> playerTwoScore++
                else -> drawScore++
            }
        }
    }

}