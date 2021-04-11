import java.util

object Temperature extends App{

  import scala.io.StdIn.readLine

  trait Status{}

  case class Freezing() extends Status()
  case class Boiling() extends Status()
  case class Neutral() extends Status()

  print("Enter freezing threshold: ")
  val minTemp = readLine.toDouble

  print("Enter boiling threshold: ")
  val maxTemp = readLine.toDouble

  print("Enter fluctuation value: ")
  val fluctiuation = readLine.toDouble

  print("Enter temperature values separated with whitespace: ")
  val tempValues = readLine

  val resultArray = giveOutput(minTemp, maxTemp, fluctiuation, tempValues)
  val resultString = resultArray.toArray().mkString(" ")
  println(resultString)


  def giveOutput(min: Double, max: Double, fluct: Double, values: String): util.ArrayList[String] = {

    val readyValues = values.split(" ").map(_.toDouble)

    class myAL extends util.ArrayList[String]{
      def addD(x: Double) = super.add(x.toString)
    }

    val al = new myAL()

    var status: Status = Neutral()

    readyValues.head match {
      case x if x <= min => al.addD(x); al.add("freezing"); status = Freezing()
      case x if x >= max => al.addD(x); al.add("boiling"); status = Boiling()
      case x => al.addD(x);
    }

    for(t <- readyValues.tail) {

      t match {

        case x if x <= min  => {
          status match {
            case s: Freezing =>
              al.addD(x)
            case s: Boiling =>
              al.addD(x)
              al.add("unboiling")
              al.add("freezing")
              status = Freezing()
            case s: Neutral =>
              al.addD(x)
              al.add("freezing")
              status = Freezing()
          }
        }

        case x if x >= max => {
          status match {
            case s: Boiling =>
              al.addD(x)
            case s: Freezing =>
              al.addD(x)
              al.add("unfreezing")
              al.add("boiling")
              status = Boiling()
            case _ =>
              al.addD(x)
              al.add("boiling")
              status = Boiling()
          }
        }

        case x =>
          status match {
            case s: Freezing =>
              if (x > min + fluct) {
                al.addD(x)
                al.add("unfreezing")
                status = Neutral()
              } else {
                al.addD(x)
              }
            case s: Boiling =>
              if (x < min - fluct) {
                al.addD(x)
                al.add("unboiling")
                status = Neutral()
              } else {
                al.addD(x)
              }
            case _ =>
              al.addD(x)
          }
      }
    }
    al
  }
}
