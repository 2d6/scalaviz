package de._2d6.scalaviz.graph

import org.scalatest.{FlatSpec, Matchers}

class NodeTest extends FlatSpec with Matchers {

  val labelKey = "label"
  val labelValue = "labelValue"

  "A Node" can "be created with a label" in {
    val node = Node(labelValue)

    node.attributes(labelKey) shouldBe labelValue
  }

  it can "be created with a label and attributes" in {
    val node = Node(labelValue, Map("someKey" -> "someValue"))

    node.attributes(labelKey) shouldBe labelValue
    node.attributes("someKey") shouldBe "someValue"
  }

  it should "override the label given in the attributes" in {
    val node = Node(labelValue, Map(labelKey -> "someOtherValue"))

    node.attributes(labelKey) shouldBe labelValue
  }

}
