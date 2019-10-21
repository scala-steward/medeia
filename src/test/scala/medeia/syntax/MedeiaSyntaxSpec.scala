package medeia.syntax

import cats.data.NonEmptyChain
import medeia.MedeiaSpec
import medeia.decoder.BsonDecoder
import medeia.decoder.BsonDecoderError.KeyNotFound
import medeia.encoder.BsonDocumentEncoder
import org.mongodb.scala.bson.{BsonDocument, BsonString}
import org.mongodb.scala.bson.collection.immutable.Document

class MedeiaSyntaxSpec extends MedeiaSpec {
  behavior of classOf[MedeiaSyntax].getSimpleName

  it should "enrich values that have a bson encoder instance" in {
    val input = 42

    val result = input.toBson.fromBson[Int].right.value

    result should ===(input)
  }

  it should "enrich values that have a bson document encoder instance" in {
    case class Foo(answer: Int)
    object Foo {
      implicit val fooEncoder: BsonDocumentEncoder[Foo] = medeia.generic.semiauto.deriveBsonEncoder
      implicit val fooDecoder: BsonDecoder[Foo] = medeia.generic.semiauto.deriveBsonDecoder
    }

    val input = Foo(42)

    val result = input.toBson.fromBson[Foo].right.value

    result should ===(input)
  }

  it should "support getSafe for Document" in {
    Document("existing" -> "foo").getSafe("existing").right.value should ===(BsonString("foo"))
    Document().getSafe("nonexisting").left.value should ===(NonEmptyChain(KeyNotFound("nonexisting")))
  }

  it should "support getSafe for BsonDocument" in {
    BsonDocument("existing" -> "foo").getSafe("existing").right.value should ===(BsonString("foo"))
    BsonDocument().getSafe("nonexisting").left.value should ===(NonEmptyChain(KeyNotFound("nonexisting")))
  }
}