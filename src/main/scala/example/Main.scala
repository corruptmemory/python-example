package example

object Main {
  def main(args:Array[String]): Unit = {
    println(s"hello from main! -- ${args.mkString(", ")}")
  }
}