## scalatable

scalatable is a library for handling tabular data.

### Usage

You can create a `ScalaTable` out of any case class:

```scala
scala> import scalatable._
import scalatable._

scala> case class Cat(name: String, color: String, age: Int) extends Row
defined class Cat

scala> val cat1 = Cat("Merlin", "grey", 2)
cat1: Cat = Cat(Merlin,grey,2)

scala> val cat2 = Cat("Elliot", "black", 1)
cat2: Cat = Cat(Elliot,black,1)

scala> val cats: Seq[Cat] = Seq(cat1, cat2)
cats: Seq[Cat] = List(Cat(Merlin,grey,2), Cat(Elliot,black,1))

scala> val catsTable: ScalaTable[Cat] = ScalaTable(Seq(cat1, cat2))
catsTable: scalatable.ScalaTable[Cat] = ScalaTable(Cat(Merlin,grey,2), Cat(Elliot,black,1))
```
You can get a graphical representation of the table through the command line:
```scala
scala> catsTable.show
+------+-----+-+
|Merlin| grey|2|
+------+-----+-+
|Elliot|black|1|
+------+-----+-+
```

You can append or remove objects from the table:
```scala
scala> catsTable.append(Cat("Snowball", "white", 2))
res1: scalatable.ScalaTable[Cat] = ScalaTable(Cat(Merlin,grey,2), Cat(Elliot,black,1), Cat(Snowball,white,2))

scala> catsTable.remove(cat2)
res2: scalatable.ScalaTable[Cat] = ScalaTable(Cat(Merlin,grey,2))
```
