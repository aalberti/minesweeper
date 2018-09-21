import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class MinesTest : StringSpec({
    "count" {
        count("""
            |*..
            |...
            |.*.
        """.trimMargin()) shouldBe """
            |*10
            |221
            |1*1
        """.trimMargin()
    }

    "loose" {
        Board("*", "?").play(0, 0) shouldBe Kaboom()
    }
    "win" {
        Board("0", "?").play(0, 0) shouldBe Win()
    }
    "multi-cell win with no mine" {
        Board("00", "?0").play(0, 0) shouldBe Win()
    }
    "mono-line go on" {
        Board("1*1", "???").play(0, 0) shouldBe GoOn(Board("1*1", "1??"))
    }
    "multi-line go on" {
        val hiddenBoard = """
                    |111
                    |1*1
                """.trimMargin()
        Board(
                hiddenBoard,
                """
                    |???
                    |???
                """.trimMargin()
        ).play(1, 0) shouldBe GoOn(Board(
                hiddenBoard, """
                    |???
                    |1??
                """.trimMargin())
        )
    }
    //TODO multi-cell win
})
