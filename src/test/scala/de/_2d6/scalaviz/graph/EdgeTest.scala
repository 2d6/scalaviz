package de._2d6.scalaviz.graph

import org.scalatest.{FlatSpec, Matchers}

class EdgeTest extends FlatSpec with Matchers {

  val labelKey = "label"
  val labelValue = "labelValue"

  val nodeA = Node()
  val nodeB = Node()

  "An Edge" can "be created with a label" in {
    val edge = Edge(nodeA, nodeB, labelValue)

    edge.attributes(labelKey) shouldBe labelValue
  }

  it can "be created with a label and attributes" in {
    val edge = Edge(nodeA, nodeB, labelValue, Map("someKey" -> "someValue"))

    edge.attributes(labelKey) shouldBe labelValue
    edge.attributes("someKey") shouldBe "someValue"
  }

  it should "override the label given in the attributes" in {
    val edge = Edge(nodeA, nodeB, labelValue, Map(labelKey -> "someValue"))

    edge.attributes(labelKey) shouldBe labelValue
  }

}
