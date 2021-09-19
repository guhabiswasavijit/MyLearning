package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object sortByKeyTest {
  def main(args: Array[String]) = {
    val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(Seq(("maths",52), ("english",75), ("science",82), ("computer",65), ("maths",85)))
    val sorted = data.sortByKey()
    sorted.foreach(println)
  }
}
