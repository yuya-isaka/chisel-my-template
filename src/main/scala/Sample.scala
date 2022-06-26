package sample

import chisel3._
import chisel3.util._
import chisel3.stage.ChiselStage

class Top extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(8.W))
    val out = Output(UInt(8.W))
    val exit = Output(Bool())
  })

  val counter = RegInit(0.U(8.W))
  val finish = RegInit(0.U(8.W))

  when(counter < 10.U) {
    when(counter === 0.U) {
      counter := io.in
    }.otherwise {
      counter := counter + 1.U
    }
  }.otherwise {
    counter := io.in
    finish := finish + 1.U
  }

  io.out := counter

  when(finish === 10.U) {
    io.exit := true.B
  }.otherwise {
    io.exit := false.B
  }

  printf(p"io.out : ${io.out}\n")
}

object Top extends App {
  (new ChiselStage).emitVerilog(
    new Top,
    Array(
      "-o",
      "sample.v",
      "--target-dir",
      "rtl"
    )
  )
}
