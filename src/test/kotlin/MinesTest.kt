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
    "go on" {
        Board("1*1", "???").play(0, 0) shouldBe GoOn(Board("1*1", "1??")) 
    }
    //TODO multi-cell win
    //TODO multiline go on
})
