package de._2d6.scalaviz.graph

import org.scalatest.{FlatSpec, Matchers}

class AttributeHolderTest extends FlatSpec with Matchers {

  val labelKey = "label"
  val labelValue = "labelValue"

  case class TestHolder(override val attributes: Map[String, String]) extends AttributeHolder[TestHolder] {
    override val copyFunction: (Map[String, String]) => TestHolder = a => copy(attributes = a)
  }

  val baseHolder = TestHolder(Map.empty)

  "An AttributeHolder" should "add a new attribute" in {
    val holder = baseHolder.withAttribute("someKey", "someValue")

    holder.attributes("someKey") shouldBe "someValue"
  }

  it should "override an attribute" in {
    val holder = baseHolder.withAttribute(labelKey, "someOtherValue")

    holder.attributes(labelKey) shouldBe "someOtherValue"
  }

  it should "add new attributes" in {
    val holder = baseHolder.withAttributes(Map(
      "someKey" -> "someValue", "otherKey" -> "otherValue"))

    holder.attributes("someKey") shouldBe "someValue"
    holder.attributes("otherKey") shouldBe "otherValue"
  }

  it should "override attributes" in {
    val holder = baseHolder.withAttributes(Map("someKey" -> "someValue", "otherKey" -> "otherValue"))
      .withAttributes(Map("someKey" -> "newValue", "otherKey" -> "newValue"))

    holder.attributes("someKey") shouldBe "newValue"
    holder.attributes("otherKey") shouldBe "newValue"
  }

  it should "ignore new attributes if they are empty" in {
    val holder = baseHolder.withAttributes(Map.empty)

    holder.attributes shouldBe empty
  }

  it should "remove an attribute" in {
    val holder = baseHolder.withAttributes(Map("someKey" -> "someValue"))
      .withAttribute("someKey", null)

    holder.attributes("someKey") shouldBe null
  }

  it should "remove attributes" in {
    val holder = baseHolder.withAttributes(Map("someKey" -> "someValue", "otherKey" -> "otherValue"))
      .withAttributes(Map("someKey" -> null, "otherKey" -> null))

    holder.attributes("someKey") shouldBe null
    holder.attributes("otherKey") shouldBe null
  }

}
