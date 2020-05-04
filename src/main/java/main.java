import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.types.*;

public class main {

    public static void main(String[] args) {

        // Spark Stream

        SparkSession session = SparkSession.builder().appName("suat").master("local").getOrCreate();

        Dataset<Row> load = session.readStream().format("kafka")
                .option("kafka.bootstrap.servers", "136.244.110.112:9092")
                .option("subscribe", "test")
                .load();

        StructType schema = new StructType().add("userId", DataTypes.IntegerType)
                .add("city", DataTypes.IntegerType)
                .add("timestamp", DataTypes.LongType)
                .add("search",DataTypes.StringType);


        Dataset<Row> data = session.readStream().format("kafka").option("header", "true").option("subscribe", "test").option("kafka.bootstrap.servers", "ip-adress:9092").load();


        Dataset<Product> dataset = data.selectExpr("CAST(value as STRING) message").select(functions.from_json(functions.col("message"), schema)).as("json").select("json.*").as(Encoders.bean(Product.class));

        // dataset.show();

        Dataset<Row> count = dataset.groupBy(functions.window(dataset.col("timestamp"),"1 minute")).count();
        StreamingQuery start = count.writeStream().outputMode("complete").format("console").start();
        try {
            start.awaitTermination();

        }catch (Exception e){
            System.out.println("Hata VAr ------------");
        }




    }
}
