package rddFunction
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf

object mapTest {
  def main(args: Array[String]) = {
    val conf:SparkConf = new SparkConf().setAppName("WordCount").setMaster("local")
    val sc = new SparkContext(conf)
    val input = sc.textFile("C:\\ScalaLrn\\src\\in.txt")
    val mapFile = input.map(line => (line,line.length))
    mapFile.foreach(println)
  }

}
