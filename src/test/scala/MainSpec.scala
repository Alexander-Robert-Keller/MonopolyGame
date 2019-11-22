import org.scalatest.{Matchers, WordSpec}

class MainSpec extends WordSpec with Matchers {

  "The Monopoly main class" should {
    "accept text input as argument without readline loop, to test it from command line " in {
      Main.main(Array[String]("1", "2"))
    }
  }
}
