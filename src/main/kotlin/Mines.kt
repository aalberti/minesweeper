fun count(mines: String): String {
    val minesGrid = mines.toGrid()
    val countGrid = Array(minesGrid.size) { i ->
        Array(minesGrid[0].size) { j ->
            if (minesGrid[i][j] == '*') '*'
            else minesGrid
                    .neighbours(i, j)
                    .count { it == '*' }
                    .toString()[0]
        }
    }
    return countGrid.joinToString("\n") { it.joinToString("") }
}

private fun String.toGrid(): List<List<Char>> = lines().map { it.toList() }

private fun List<List<Char>>.neighbours(i: Int, j: Int): String = """
    |${top(i).left(j)}${top(i)[j]}${top(i).right(j)}
    |${this[i].left(j)}${this[i][j]}${this[i].right(j)}
    |${bottom(i).left(j)}${bottom(i)[j]}${bottom(i).right(j)}
    """.trimMargin()

private fun List<List<Char>>.top(i: Int): List<Char> = if (i == 0) List(size) {'.'} else this[i - 1]
private fun List<List<Char>>.bottom(i: Int): List<Char> = if (i == size - 1) List(size) {'.'} else this[i + 1]
private fun List<Char>.left(j: Int) = if (j == 0) '.' else this[j - 1]
private fun List<Char>.right(j: Int) = if (j == size - 1) '.' else this[j + 1]

fun String.play(x: Int, y: Int): Outcome = if (toGrid()[x][y] == '*') Kaboom() else SomethingElse()
sealed class Outcome
class Kaboom : Outcome()
class SomethingElse : Outcome()