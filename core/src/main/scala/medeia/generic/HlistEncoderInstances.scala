package medeia.generic

import medeia.encoder.BsonEncoder
import medeia.generic.util.VersionSpecific.Lazy
import shapeless.labelled.FieldType
import shapeless.{::, HList, HNil, Witness}

trait HlistEncoderInstances {
  implicit def hnilEncoder[Base]: ShapelessEncoder[Base, HNil] =
    (_, doc) => doc

  implicit def hlistObjectEncoder[Base, K <: Symbol, H, T <: HList](implicit
      witness: Witness.Aux[K],
      hEncoder: Lazy[BsonEncoder[H]],
      tEncoder: ShapelessEncoder[Base, T],
      options: GenericDerivationOptions[Base] = GenericDerivationOptions[Base]()
  ): ShapelessEncoder[Base, FieldType[K, H] :: T] = {
    val fieldName: String = options.transformKeys(witness.value.name)
    (hlist, doc) => {
      val head = hEncoder.value.encode(hlist.head)
      tEncoder.encode(hlist.tail, doc.append(fieldName, head))
    }
  }
}
