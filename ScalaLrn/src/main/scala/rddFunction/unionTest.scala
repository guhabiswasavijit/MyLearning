package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object unionTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(Seq((1,"jan",2016),(3,"nov",2014),(16,"feb",2014)))
    val rdd2 = sc.parallelize(Seq((5,"dec",2014),(17,"sep",2015)))
    val rdd3 = sc.parallelize(Seq((6,"dec",2011),(16,"may",2015)))
    val rddUnion = rdd1.union(rdd2).union(rdd3)
    rddUnion.foreach(println)
  }
}
