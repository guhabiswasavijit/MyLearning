package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object topTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\ScalaLrn\\src\\in.txt")
    val mapFile = input.map(line => (line,line.length))
    val res = mapFile.top(3)
    res.foreach(println)
  }
}
