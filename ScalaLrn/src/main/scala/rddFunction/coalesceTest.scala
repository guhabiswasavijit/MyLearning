package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object coalesceTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Array("jan","feb","mar","april","may","jun"),3)
    val result = rdd1.coalesce(2)
    result.foreach(println)
  }
}
