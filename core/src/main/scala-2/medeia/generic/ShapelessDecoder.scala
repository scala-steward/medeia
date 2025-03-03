package medeia.generic

import cats.data.EitherNec
import medeia.decoder.BsonDecoderError
import org.mongodb.scala.bson.BsonDocument

private[medeia] trait ShapelessDecoder[Base, H] {
  def decode(bsonDocument: BsonDocument): EitherNec[BsonDecoderError, H]
  def map[B](f: H => B): ShapelessDecoder[Base, B] = x => this.decode(x).map(f(_))
}

private[medeia] object ShapelessDecoder extends HlistDecoderInstances with CoproductDecoderInstances
