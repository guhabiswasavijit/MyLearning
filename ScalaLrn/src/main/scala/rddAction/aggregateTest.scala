package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object aggregateTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val inputrdd = sc.parallelize(
                            List(
                                  ("maths", 21),
                                  ("english", 22),
                                  ("science", 31)
                                ),
                              3
                            )
    val result = inputrdd.aggregate(3) (
      (acc, value) => (acc + value._2), (acc1, acc2) => (acc1 + acc2)
    )
    println(result)
  }
}
