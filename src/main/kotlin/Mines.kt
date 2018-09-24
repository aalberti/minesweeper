fun count(mines: String): String {
    val minesGrid = mines.toGrid()
    val countGrid = Array(minesGrid.size) { i ->
        Array(minesGrid[0].size) { j ->
            if (minesGrid[i][j] == '*') '*'
            else minesGrid
                    .neighbourhood(i, j)
                    .count { it == '*' }
                    .toString()[0]
        }
    }
    return countGrid.joinToString("\n") { it.joinToString("") }
}

private fun String.toGrid(): List<List<Char>> = lines().map { it.toList() }

private fun List<List<Char>>.neighbourhood(i: Int, j: Int): String = """
        |${top(i).left(j)}${top(i)[j]}${top(i).right(j)}
        |${this[i].left(j)}${this[i][j]}${this[i].right(j)}
        |${bottom(i).left(j)}${bottom(i)[j]}${bottom(i).right(j)}
    """.trimMargin()

private fun List<List<Char>>.top(i: Int): List<Char> = if (i == 0) List(size) { '.' } else this[i - 1]
private fun List<List<Char>>.bottom(i: Int): List<Char> = if (i == size - 1) List(size) { '.' } else this[i + 1]
private fun List<Char>.left(j: Int) = if (j == 0) '.' else this[j - 1]
private fun List<Char>.right(j: Int) = if (j == size - 1) '.' else this[j + 1]

data class Board(val mines: String, val display: String) {
    fun play(x: Int, y: Int) = when {
        isAMine(x, y) -> Kaboom
        won(x, y) -> Win
        else -> GoOn(Board(mines, newDisplay(x, y)))
    }
    private fun isAMine(x: Int, y: Int) = mines.toGrid()[x][y] == '*'
    private fun won(x: Int, y: Int): Boolean {
        val newDisplay = newDisplay(x, y)
        return isSolved(newDisplay) && allMinesAreMarked(newDisplay)
    }
    private fun isSolved(newDisplay: String) = newDisplay.count { it == '?' } == 0
    private fun allMinesAreMarked(newDisplay: String) = newDisplay.count { it == '!' } == mines.count { it == '*' }
    private fun newDisplay(x: Int, y: Int): String = display.lines()
            .mapIndexed { i, line ->
                if (i == x) line.replaceRange(y..y, "${mines[y]}")
                else line
            }
            .joinToString("\n")
}

sealed class Outcome
object Kaboom : Outcome()
object Win : Outcome()
data class GoOn(val newBoard: Board) : Outcome()
