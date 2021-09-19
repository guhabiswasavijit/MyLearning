package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object reduceTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(20,32,45,62,8,5))
    val sum = rdd1.reduce(_+_)
    println(sum)
  }
}
