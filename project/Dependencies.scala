import sbt.Keys._
import sbt._

object Dependencies {
  object Versions {
    val scalaTest = "3.2.10"
    val mongoScalaBson = "4.3.2"
    val cats = "2.6.1"
    val scalaCheck = "1.15.4"
    val shapeless = "2.3.7"
    val scalaCollectionCompatVersion = "2.5.0"
    val enumeratumVersion = "1.7.0"
    val refinedVersion = "0.9.27"
  }

  lazy val Libraries: Vector[ModuleID] =  Vector(
    "org.mongodb.scala" %% "mongo-scala-bson" % Versions.mongoScalaBson,
    "org.typelevel" %% "cats-core" % Versions.cats,
    "com.chuusai" %% "shapeless" % Versions.shapeless,
    "org.scala-lang.modules" %% "scala-collection-compat" % Versions.scalaCollectionCompatVersion
  )

  lazy val TestLibraries: Vector[ModuleID] = Vector(
    "org.scalatest" %% "scalatest" % Versions.scalaTest,
    "org.scalacheck" %% "scalacheck" % Versions.scalaCheck
  ).map(_ % Test)

  lazy val EnumeratumLibraries: Vector[ModuleID] = Vector(
    "com.beachape" %% "enumeratum" % Versions.enumeratumVersion
  )

  lazy val EnumeratumTestLibraries: Vector[ModuleID] = Vector(
    "com.beachape" %% "enumeratum-scalacheck" % Versions.enumeratumVersion
  ).map(_ % Test)

  lazy val RefinedLibraries: Vector[ModuleID] = Vector(
    "eu.timepit" %% "refined" % Versions.refinedVersion
  )

  lazy val RefinedTestLibraries: Vector[ModuleID] = Vector(
    "eu.timepit" %% "refined-scalacheck" % Versions.refinedVersion
  ).map(_ % Test)
}
