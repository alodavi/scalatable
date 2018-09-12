package scalatable

case class ScalaTable[A <: Row](sequence: Seq[A], name: Option[String] = None)(
    implicit tag: reflect.ClassTag[A])
    extends IndexedSeq[A] {

  def show: Unit = ScalaTableFormatter.show(sequence)

  def append(otherSeq: A*): ScalaTable[A] =
    ScalaTable(this.sequence ++ otherSeq)

  def remove(otherSeq: A*): ScalaTable[A] =
    ScalaTable(this.sequence.diff(otherSeq))

  override def take(n: Int): ScalaTable[A] = ScalaTable(
    this.sequence.take(n)
  )

  override def foreach[U](f: A => U): Unit = super.foreach(f)

  override def apply(i: Int): A = sequence.apply(i)

  override def length: Int = sequence.length

  override def iterator: Iterator[A] = super.iterator

  override def isEmpty: Boolean = super.isEmpty

  override def nonEmpty: Boolean = super.nonEmpty

}
