package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object countByValueTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\ScalaLrn\\src\\in.txt")
    val result= input.map(line => (line,line.length)).countByValue()
    result.foreach(println)
  }
}
