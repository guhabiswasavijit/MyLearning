package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object reduceByKeyTest {
  def main(args: Array[String]) = {
    val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val words = Array("one","two","two","four","five","six","six","eight","nine","ten")
    val data = sc.parallelize(words).map(w => (w,1)).reduceByKey(_+_)
    data.foreach(println)
  }
}
