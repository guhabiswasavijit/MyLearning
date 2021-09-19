package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object mapByValuesCase {
  def main(args: Array[String]) = {
    val conf: SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(Array(('k', 5), ('s', 3), ('p', 7), ('t', 8)), 3)
    val data1 = sc.parallelize(Array(('k', 5), ('s', 3), ('p', 7), ('t', 8)), 3)
    val group = data.join(data1).mapValues{case(x,y) => x+y}
    group.foreach(println)
  }
}
