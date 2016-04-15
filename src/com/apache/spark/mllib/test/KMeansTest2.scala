/**
 * 只是简单的测试mllib。KMeans的聚类，使用的是自带的数据集
 * 之进行了聚类和误差平方和分析，没有预测
 * 完善请见KMeansTest3
 */
package com.apache.spark.mllib.test
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import scala.Array.canBuildFrom

object KMeansTest2 {
 def main(args: Array[String]) {
    val Conf = new SparkConf()
          .setAppName("K-Means")
          .setMaster("local[2]");
    val sc = new SparkContext(Conf);
    var text1 = sc.textFile("hdfs://219.219.220.149:9000/xubo/spark/data/mllib/kmeans_data.txt");
//    for( i <- text1) println(i) 
//     text1.foreach(println)
      println(text1.count);
      text1.foreach(println);
    
    val parsedData=text1.map(s=>Vectors.dense(s.split(" ").map(_.toDouble)))
      parsedData.foreach(println)
    var numClusters = 2; //预测分为2个簇类
    var numIterations = 20; //迭代20次
    var runs = 10; //运行10次，选出最优解
    var model = KMeans.train(parsedData, numClusters, numIterations, runs);    
    for(c<-model.clusterCenters) println(" "+c.toString());
    var cost=model.computeCost(parsedData)
    println("Within Set Sum of Squared Errors = " + cost)
    
    }
}