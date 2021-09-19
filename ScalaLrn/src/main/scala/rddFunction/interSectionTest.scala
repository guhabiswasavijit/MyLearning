package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object interSectionTest {
  def main(args: Array[String]) = {
    val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Seq((5, "dec", 2014), (17, "sep", 2015)))
    val rdd2 = sc.parallelize(Seq((5, "dec", 2014), (16, "may", 2015)))
    val common = rdd1.intersection(rdd2)
    common.foreach(println)
  }
}
