package scalatable

import org.specs2._

class ScalaTableSpec extends Specification {
  def is = s2"""

  This is a specification for the ScalaTable case class

  A ScalaTable should
    be able to append a sequence to an existing ScalaTable                                  $testAppend
    be able to remove a sequence from an existing ScalaTable                                $testRemove
    take an arbitrary number of rows                                                        $testTake
                                                      """

  case class Cat(name: String, color: String, age: Int) extends Row

  val cat1 = Cat("Merlin", "grey", 2)
  val cat2 = Cat("Elliot", "black", 1)
  val cats: Seq[Cat] = Seq(cat1, cat2)

  val catsTable: ScalaTable[Cat] = ScalaTable(Seq(cat1, cat2))

  def testAppend = {

    val newTable = catsTable.append(Cat("Snowball", "white", 2))

    newTable must beEqualTo(
      ScalaTable(Seq(cat1, cat2, Cat("Snowball", "white", 2))))

  }

  def testRemove = {

    val removeTable = catsTable.remove(cat2)

    removeTable must beEqualTo(ScalaTable(Seq(cat1)))

  }

  def testTake = {
    catsTable.take(1) must beEqualTo(ScalaTable(Seq(cat1)))
  }

}
