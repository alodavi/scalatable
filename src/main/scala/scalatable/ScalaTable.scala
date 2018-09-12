package scalatable

case class ScalaTable[A <: Row](sequence: Seq[A], name: Option[String] = None)(implicit tag: reflect.ClassTag[A])
  extends IndexedSeq[A] {

  private def extractFieldNames[A] : Array[String] = tag.runtimeClass.getDeclaredFields.map(_.getName)

  def show: Unit = TableFormatter.show(sequence)

  def columns: Array[String] = extractFieldNames[A]

  def append(otherSeq : A*): ScalaTable[A] = ScalaTable(this.sequence ++ otherSeq)

  def remove(otherSeq : A*): ScalaTable[A] = ScalaTable(this.sequence.filter(e => otherSeq.contains(e)))

  override def take(n: Int): ScalaTable[A] = ScalaTable(
    this.sequence.take(n)
  )

  override def foreach[U](f: A => U): Unit = super.foreach(f)

  override def apply(i: Int): A = sequence.apply(i)

  override def length: Int = sequence.length

  override def iterator: Iterator[A] = super.iterator

}

object TableFormatter {

  def format[A <: Row](table: Seq[A]) =
    table match {
      case Seq() => ""
      case _ =>
        val sizes: Seq[IndexedSeq[Int]] = for (row <- table)
          yield
            (for (i <- 0 to row.productArity - 1)
              yield row.productElement(i).toString.length)
        val colSizes = for (col <- sizes.transpose) yield col.max
        val rows = for (row <- table) yield formatRow(row, colSizes)
        formatRows(rowSeparator(colSizes), rows)
    }

  def formatRows(rowSeparator: String, rows: Seq[String]): String =
    (rowSeparator ::
      rows.head ::
      rowSeparator ::
      rows.tail.toList :::
      rowSeparator ::
      List()).mkString("\n")

  def formatRow[A <: Row](row: A, colSizes: Seq[Int]): String = {
    val cells = (
      for {
        (item, size) <- row.productIterator.zip(colSizes)
      } yield if (size == 0) "" else ("%" + size + "s").format(item)
      )
    cells.mkString("|", "|", "|")
  }

  def rowSeparator(colSizes: Seq[Int]) =
    colSizes map { "-" * _ } mkString ("+", "+", "+")

  def show[A <: Row](table: Seq[A]): Unit = println(this.format(table))
}

