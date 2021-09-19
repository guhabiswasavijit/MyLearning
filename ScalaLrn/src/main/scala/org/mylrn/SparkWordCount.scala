package org.mylrn

import org.apache.spark.SparkContext
import org.apache.spark._

object SparkWordCount {
  def main(args: Array[String]) {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\ScalaLrn\\src\\in.txt")
    /* Transform the inputRDD into countRDD */
    val count = input.flatMap(line ⇒ line.split(" "))
      .map(word ⇒ (word, 1))
      .reduceByKey(_ + _)
    /* saveAsTextFile method is an action that effects on the RDD */
    count.saveAsTextFile("outfile")
    System.out.println("OK");
  }
}
