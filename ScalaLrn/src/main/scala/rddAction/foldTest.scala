package rddAction

import org.apache.spark.{SparkConf, SparkContext}

object foldTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val rdd1 = sc.parallelize(List(("maths", 80),("science", 90)))
    val additionalMarks = ("extra", 0)
    val sum = rdd1.fold(additionalMarks){ (acc, marks) => val add = acc._2 + marks._2
      ("total", add)
    }
    println(sum)
  }
}
