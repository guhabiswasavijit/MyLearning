package rddFunction

import org.apache.spark.{SparkConf, SparkContext}

object filterTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\ScalaLrn\\src\\in.txt")
    val mapFile = input.flatMap(lines => lines.split(" ")).filter(value => value=="people")
    mapFile.foreach(println)
  }
}
