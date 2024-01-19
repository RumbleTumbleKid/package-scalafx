# How to package a ScalaFX application

This project uses two SBT plugins:

1. [SBT Native Packager](https://github.com/sbt/sbt-native-packager) for generating jlink images.
2. [sbt-native-image](https://github.com/scalameta/sbt-native-image) for generating AOT compiled binaries.

## Application image with bundled runtime

### 1. Build modularized runtime via jlink

Strictly speaking, this step isn't necessary, but it builds a custom runtime by removing unneeded modules, resulting in a smaller runtime size.

`sbt stage`

You will now find a custom built and portable runtime image in `.\target\universal\stage` which contains all the libraries of your project and a run-script in `\bin`.

As a zip package, this portable image sizes about __44MB__ (on Windows), which is quite small considering that this image includes a runtime and all the required libraries (Scala stdlib and ScalaFX including JFX).

⚠️ The Graal JDK generates considerably larger images (probably a bug) which is why you should use another JDK to generate jlink images if size is important to you.

### 2. Package appliation with jpackage

This step lets you create a portable executable or an installer for your application. 

```
cd .\target\universal\stage
jpackage --input .\lib\ --main-jar scalafxapplication.scalafxapplication-0.1.jar --main-class ScalaFXApplication --type app-image --runtime-image .\jre\
```

You will now find a new folder which contains an executable runtime image of your application.

You can read more about jpackage's options [here](https://docs.oracle.com/en/java/javase/17/docs/specs/man/jpackage.html).


## AOT compiled Native Image

You can read more about GraalVM Native Image and how to configure it [here](https://www.graalvm.org/latest/reference-manual/native-image/), if needed. 

### 1. Run application with Graal's Tracing Agent

GraalVM’s tracing agent is a tool that helps you to generate configuration files for building native images from Java applications that use dynamic features such as reflection, JNI, proxies, or resources. Ideally, the application gets used as extensively as possible so the tracing agent picks up as much as possible.

`sbt nativeImageRunAgent`

### 2. Compile application

`sbt nativeImage`

You will find the executable in `.\target\native-image`