name         := "ScalaFXApplication"
version      := "0.1"
scalaVersion := "3.3.1"

libraryDependencies += "org.scalafx" %% "scalafx" % "21.0.0-R32" exclude ("org.openjfx", "javafx-web")

nativeImageInstalled := true
nativeImageOptions ++= Seq(
  s"-H:ConfigurationFileDirectories=${target.value / "native-image-configs"}",
  "-O1"
)

enablePlugins(JlinkPlugin, NativeImagePlugin)
scriptClasspath := Seq("*")
jlinkModules += "java.desktop"
jlinkOptions ++= Seq("--strip-debug", "--compress", "0", "--no-header-files", "--no-man-pages")

Compile / run / mainClass := Some("ScalaFXApplication")
