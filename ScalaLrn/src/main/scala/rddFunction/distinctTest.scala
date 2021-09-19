package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object distinctTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Seq((1,"jan",2016),(3,"nov",2014),(16,"feb",2014),(3,"nov",2014)))
    val result = rdd1.distinct()
    println(result.collect().mkString(", "))
  }
}
