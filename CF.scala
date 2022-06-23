//Imports for Task 1
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
//Imports for Task 2
import org.apache.spark.sql.functions.col
//Imports for Task 3
import org.apache.spark.ml.recommendation.ALS
import org.apache.spark.ml.evaluation.RegressionEvaluator



object CF extends App {

  //Task 1: a) Create SparkSession
  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Collaborative Filtering")
    .getOrCreate()

  import spark.implicits._


  //Task 1: b) Define Schema for Books & Rating csv files
  // Book.csv Schema
  val bookSchema = StructType(
    StructField("ISBN", StringType, nullable = true) ::
      StructField("Title", StringType, nullable = true) ::
      StructField("Author", StringType, nullable = true) ::
      StructField("Year", IntegerType, nullable = true) ::
      StructField("Publisher", StringType, nullable = true) ::
      Nil
  )
  // Rating.csv Schema
  val ratingSchema = StructType(
    StructField("USER-ID", IntegerType, nullable = true) ::
      StructField("ISBN", IntegerType, nullable = true) ::
      StructField("Rating", IntegerType, nullable = true) ::
      Nil
  )


  //Task 1: c) Load Books & Rating csv files in separate Spark DataFrames
  // Read Books.csv
  val bookDf = spark.read.format("csv")
    .option("header", value = true)
    .option("delimiter", ";")
    .option("mode", "DROPMALFORMED")
    .schema(bookSchema)
    .load("/home/will/Downloads/Books.csv")
    .cache()
    .as("books")
  bookDf.printSchema()
  bookDf.show(10)

  // Read Ratings.csv
  val ratingDf = spark.read.format("csv")
    .option("header", value = true)
    .option("delimiter", ";")
    .option("mode", "DROPMALFORMED")
    .schema(ratingSchema)
    .load("/home/will/Downloads/Ratings.csv")
    .cache()
    .as("ratings")
  ratingDf.printSchema()
  ratingDf.show(10)


  //Task 2: a) Combine both DataFrames using join operation
  // (Joins elements when the ISBN matches)
  val jdf = ratingDf.join(bookDf, $"ratings.ISBN" === $"books.ISBN")
    .select(
      $"ratings.USER-ID".as("userId"),
      $"ratings.Rating".as("rating"),
      $"ratings.ISBN".as("isbn"),
      $"books.Title".as("title"),
      $"books.Author".as("author"),
      $"books.Year".as("year"),
      $"books.Publisher".as("publisher")
    )
  jdf.printSchema()
  jdf.show(10)


  //Task 2: b) Use filter operation to select specific userID
  jdf.filter(col("userId") === "277378")
    .limit(5)
  .show()


  //Task 3: a) Build a recommendation model using Alternating Least Squares (ALS)


    // Randomly splitting jdf into 80% for training & 20% for testData
    val Array(trainingData, testData) = jdf.randomSplit(Array(0.8, 0.2))

    // build recommendation model with als algorithm
    val als = new ALS()
      .setMaxIter(5)
      .setRegParam(0.01)
      .setUserCol("userId")
      .setItemCol("isbn")
      .setRatingCol("rating")
    val alsModel = als.fit(trainingData)


  //Task 3: b) Evaluate the ALS model using Root Mean Square Error (RSME)
    // evaluate the als model
    // compute root mean square error(rmse) with test data for evaluation
    // set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
    alsModel.setColdStartStrategy("drop")
    val predictions = alsModel.transform(testData)
    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("rating")
      .setPredictionCol("prediction")
    val rmse = evaluator.evaluate(predictions)
    println(s"root mean square error $rmse")


  //Task 3: c) Predict Recommendation


      // top 10 book recommendations for each user
      val allUserRec = alsModel.recommendForAllUsers(10)
      allUserRec.printSchema()
      allUserRec.show(10)

      // top 10 user recommendations for each book
      val allBookRec = alsModel.recommendForAllItems(10)
      allBookRec.printSchema()
      allBookRec.show(10)

      // top 10 book recommendations for specific set of users(3 users)
      val userRec = alsModel.recommendForUserSubset(jdf.select("userId").distinct().limit(3), 10)
      userRec.printSchema()
      userRec.show(10)

      // top 10 user recommendations for specific set of books(3 books)
      val bookRec = alsModel.recommendForItemSubset(jdf.select("isbn").distinct().limit(3), 10)
      bookRec.printSchema()
      bookRec.show(10)

      // top 10 book recommendations for user 277378
      val udf = jdf.select("userId").filter(col("userId") === 277378).limit(1)
      val userRec277378 = alsModel.recommendForUserSubset(udf, 10)
      userRec277378.printSchema()
      userRec277378.show(10)







}

