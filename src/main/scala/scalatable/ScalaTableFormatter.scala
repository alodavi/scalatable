package scalatable

object ScalaTableFormatter {

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
