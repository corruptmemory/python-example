lazy val python = settingKey[String]("The path to the Python executable")
lazy val runPythonMain = taskKey[Unit]("Run the python main")
lazy val runPythonWithScala = taskKey[Unit]("Run a python script with the scala artifact")
lazy val pythonSource = settingKey[File]("Where to find the python scripts")

name := "python-example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.8"

python := {
  val p:String = "which python".!!
  p.trim()
}

pythonSource := baseDirectory.value / "src" / "main" / "python"

runPythonMain := {
  val pathToScript = pythonSource.value / "main.py"
  s"${python.value} ${pathToScript.toString}".!
}

runPythonWithScala := {
  val java:File = file(System.getProperty("java.home")) / "bin" / "java"
  val artifact:File = (Keys.`package` in Compile).value
  val pathToScript = pythonSource.value / "runner.py"
  val classpath:Seq[File] = (externalDependencyClasspath in Runtime).value.files

  s"${python.value} ${pathToScript.toString} $java ${classpath.mkString(":")} $artifact example.Main 'hello-there'".!
}