package temp;

import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

public class streamMain {

    public static void main(String[] args) {


        SparkSession session = SparkSession.builder().appName("stream").master("local").getOrCreate();
        StructType schema = new StructType().add("userId", DataTypes.IntegerType).add("city",DataTypes.IntegerType)
                .add("timestamp", DataTypes.LongType)
                .add("search",DataTypes.StringType);


        Dataset<Row> load = session.readStream().format("kafka").option("inferSchema","true").option("kafka.bootstrap.servers", "209.250.244.229:9092")
                .option("subscribe", "search-analysis").load();
        System.out.println("çalışıyor");

        Dataset<Row> data = load.selectExpr("CAST(key AS STRING)","CAST(value AS STRING) arama")
                .select(functions.from_json(functions.col("arama"),schema))
                ;



    //    Dataset<Row> count = data.groupBy(functions.window(data.col("city"), "1 minute"), data.col("search")).count();

        StreamingQuery query = data.writeStream().format("console").outputMode("complete").start();
        try{
            query.awaitTermination();

        }catch (Exception e){

        }




    }
}
