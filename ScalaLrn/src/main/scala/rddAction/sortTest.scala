package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object sortTest {
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
    val sortedRdd = inputrdd.sortBy(_._2, false)
    println(sortedRdd.collect().toList)
  }
}
