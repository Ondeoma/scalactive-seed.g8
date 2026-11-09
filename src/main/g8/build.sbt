
ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "3.3.7"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  // ScalactivePlugin: src/main/scalactive 配下の .st(単一ファイルコンポーネント)をScalaのソースに変換します.
  .enablePlugins(ScalaJSPlugin, ScalactivePlugin)
  .settings(
    name := "$name$",
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= {
      _.withModuleKind(ModuleKind.ESModule)
    },
    libraryDependencies ++= Seq(
      "io.github.ondeoma" %%% "scalactive" % "$scalactive_version$",
    )
  )
