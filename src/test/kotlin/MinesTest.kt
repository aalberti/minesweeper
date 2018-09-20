import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.should
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
        "*".play(0, 0) should beInstanceOf(Kaboom::class)
    }
    "win" {
        "0".play(0, 0) should beInstanceOf(Win::class)
    }
})
