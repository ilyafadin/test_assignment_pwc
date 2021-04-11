object TemperatureTest extends App {

  //4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0
  //4.0 1.0 0.5 0.0           -0.5  0.0 0.5 0.0 -2.0 0.0 0.5 0.6            2.0
  //4.0 1.0 0.5 0.0 freezing -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 unfreezing 2.0

  //5.0 -0.5 0.5 -0.2 100 101
  //5.0 -0.5          0.5 -0.2 100                    101
  //5.0 -0.5 freezing 0.5 -0.2 100 unfreezing boiling 101

  //0.0 0.3 0.5 0.4 0.7
  //0.0          0.3 0.5 0.4 0.7
  //0.0 freezing 0.3 0.5 0.4 0.7 unfreezing

  //Alert “freezing” is triggered if the previous temperature was above freezing threshold
  //alert “unfreezing” is triggered if the previous temperature was below (freezing threshold + fluctuation value)
  //Alert “boiling” is triggered if the previous temperature was below boiling threshold
  //alert “unboiling” is triggered if the previous temperature was above (boiling threshold - fluctuation value)

  val res1 = Temperature.giveOutput(0D, 100D, 0.5D, "4.0 1.0 0.5 0.0 -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 2.0")
  val resString1 = res1.toArray().mkString(" ")
  println(resString1)
  assert(resString1.equals("4.0 1.0 0.5 0.0 freezing -0.5 0.0 0.5 0.0 -2.0 0.0 0.5 0.6 unfreezing 2.0"))

  val res2 = Temperature.giveOutput(0D, 100D, 0.5D, "5.0 -0.5 0.5 -0.2 100 101")
  val resString2 = res2.toArray().mkString(" ")
  println(resString2)
  assert(resString2.equals("5.0 -0.5 freezing 0.5 -0.2 100.0 unfreezing boiling 101.0"))

  val res3 = Temperature.giveOutput(0D, 100D, 0.5D, "0.0 0.3 0.5 0.4 0.7")
  val resString3 = res3.toArray().mkString(" ")
  println(resString3)
  assert(resString3.equals("0.0 freezing 0.3 0.5 0.4 0.7 unfreezing"))

}
