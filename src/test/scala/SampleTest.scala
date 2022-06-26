package sample

import chisel3._
import org.scalatest.flatspec._
import chiseltest._
import java.io._

// ２つのトレイトをテストクラスに継承
class SampleTest extends AnyFlatSpec with ChiselScalatestTester {
  "Sample" should "work through test" in {

    println(
      "\n-------------------------- start ------------------------------\n"
    )

    // バックエンドをVerilator
    // test(new Top().withAnnotations(Seq(VerilatorBackendAnnotation)) {
    test(new Top) { c =>
      // peekInt()
      c.io.in.poke(5.U)
      while (!c.io.exit.peekBoolean()) {
        c.clock.step(1)
      }
    }

    println(
      "\n---------------------------- end ------------------------------\n"
    )

  }
}
