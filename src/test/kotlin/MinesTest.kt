import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class MinesTest : StringSpec({
    "foo" {
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
})

