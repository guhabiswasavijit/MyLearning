package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object forEachTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val data = sc.parallelize(Array(('k',5),('s',3),('s',4),('p',7),('p',5),('t',8),('k',6)),3)
    val group = data.groupByKey().collect()
    group.foreach(println)
  }
}
