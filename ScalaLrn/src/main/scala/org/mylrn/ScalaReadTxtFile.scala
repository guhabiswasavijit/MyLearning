package org.mylrn

import scala.io.Source
import scala.collection.mutable.Map
object ScalaReadTxtFile {
  def main(args: Array[String]): Unit = {
    val filename = "C:\\ScalaLrn\\src\\in.txt"
    val wordCntMap:Map[String,Int] = Map.empty[String,Int]
    for (line <- Source.fromFile(filename).getLines) {
      println(line)
      val words = line.split(" ")
      for(word <- words){
        if(wordCntMap.contains(word)){
          wordCntMap(word) += 1;
        }else{
          wordCntMap(word) = 1;
        }
      }
    }
    println(wordCntMap)
  }
}
