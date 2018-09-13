#scalatable

scalatable is a library for handling tabular data.

## Usage

You can create a `ScalaTable` out of any case class:

```tut
import scalatable._

case class Cat(name: String, color: String, age: Int) extends Row

val cat1 = Cat("Merlin", "grey", 2)
val cat2 = Cat("Elliot", "black", 1)
val cats: Seq[Cat] = Seq(cat1, cat2)

val catsTable: ScalaTable[Cat] = ScalaTable(Seq(cat1, cat2))

```
You can get a graphical representation of the table through the command line:
```tut
catsTable.show
```

You can append or remove objects from the table:
```tut
catsTable.append(Cat("Snowball", "white", 2))

catsTable.remove(cat2)
```