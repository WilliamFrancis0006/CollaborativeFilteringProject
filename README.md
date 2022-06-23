# CollaborativeFilteringProject
 ## This project implements collaborative filtering with two datasets Book.csv and Ratings.csv. 
 ### First a new SparkSession is created, and we define the schema for both datasets and load each one into dataframes.
 ### Next we join both dataframes and give an example of filtering
 ### Then we build a recommendation model by randomly splitting the joint dataframe where 80% is used  as training data and 20% as testing data.
 ### Lastly we evaluate the ALS model using RSME and the predict a few possible recommendations
 
 ## Screenshots:
 
 
  ## Task 1: 
  ### a) Create SparkSession
  ### b) Define Schema for Books & Rating csv files
  (Book.csv Schema)
  (Rating.csv Schema)

![Screenshot from 2022-06-23 10-28-57](https://user-images.githubusercontent.com/60329354/175359217-60bf53c0-9958-47f2-b282-15bd5a2e8324.png)

![Screenshot from 2022-06-23 10-29-33](https://user-images.githubusercontent.com/60329354/175359223-5cc16a0b-a028-497a-9c98-204eeda66d49.png)


  ### c) Load Books & Rating csv files in separate Spark DataFrames
  
![Screenshot from 2022-06-23 10-29-25](https://user-images.githubusercontent.com/60329354/175359241-cc027b30-ee73-4208-99ad-95c2590364aa.png)

![Screenshot from 2022-06-23 10-29-45](https://user-images.githubusercontent.com/60329354/175359250-4615f083-fc6a-4268-8db2-376852d4108b.png)


  ## Task 2: 
  ### a) Combine both DataFrames using join operation
  (Joins elements when the ISBN matches)
  
![Screenshot from 2022-06-23 10-29-451](https://user-images.githubusercontent.com/60329354/175359278-3d37d552-1022-4e6f-8413-829ef0d55344.png)

![Screenshot from 2022-06-23 10-29-59](https://user-images.githubusercontent.com/60329354/175359313-02c4f1d4-4484-4ee4-9fb4-73514871cdf8.png)

  ### b) Use filter operation to select specific userID
  
![Screenshot from 2022-06-23 10-30-10](https://user-images.githubusercontent.com/60329354/175359350-89c43bea-bf0c-4c99-898c-ed506feac100.png)

  ## Task 3: 
  ### a) Build a recommendation model using Alternating Least Squares (ALS)
  (Randomly splits jdf into 80% for training & 20% for testData)
  ###  b) Evaluate the ALS model using Root Mean Square Error (RSME)
  
![Screenshot from 2022-06-23 10-31-14](https://user-images.githubusercontent.com/60329354/175359380-28a5ba98-a4d6-4b68-a28f-1ea53f31c348.png)

  ### c) Predict Recommendation

(top 10 book recommendations for each user)

![Screenshot from 2022-06-23 10-31-21](https://user-images.githubusercontent.com/60329354/175360274-27efa9a4-4df7-4ab8-adad-56685c5b9816.png)

![Screenshot from 2022-06-23 10-31-42](https://user-images.githubusercontent.com/60329354/175360278-6946b550-50fa-4e21-b256-71ceb084a3a2.png)

(top 10 user recommendations for each book)

![Screenshot from 2022-06-23 10-31-51](https://user-images.githubusercontent.com/60329354/175360257-b0f41dd5-e7c3-4179-9634-ce3a12bfe5e5.png)

![Screenshot from 2022-06-23 10-32-08](https://user-images.githubusercontent.com/60329354/175360249-16e952e0-3061-4385-a94d-012e828df28d.png)

(top 10 book recommendations for specific set of users(3 users))

![Screenshot from 2022-06-23 10-32-17](https://user-images.githubusercontent.com/60329354/175359433-aeea3290-8991-4f66-82df-777eb0485e23.png)

![Screenshot from 2022-06-23 10-32-32](https://user-images.githubusercontent.com/60329354/175359451-f1f283db-5d2b-4ba9-a5c6-51c94c267165.png)

(top 10 user recommendations for specific set of books(3 books))

![Screenshot from 2022-06-23 10-32-36](https://user-images.githubusercontent.com/60329354/175359471-fb0d89ae-39f4-43ef-b252-9a27a72f6067.png)

![Screenshot from 2022-06-23 10-32-53](https://user-images.githubusercontent.com/60329354/175359493-2b28718d-93b2-4047-8d88-e5b1757039ad.png)

(top 10 book recommendations for user 277378)

![Screenshot from 2022-06-23 10-32-58](https://user-images.githubusercontent.com/60329354/175359503-9468f427-ba21-487e-8c29-18bdab50c9cf.png)

![Screenshot from 2022-06-23 10-33-17](https://user-images.githubusercontent.com/60329354/175359515-c859ce9e-3b35-4d96-a51a-14d2cc0d6c2d.png)


## Links to Datasets and Guide

https://www.kaggle.com/datasets/somnambwl/bookcrossing-dataset

https://medium.com/rahasak/collaborative-filtering-based-book-recommendation-system-with-spark-ml-and-scala-1e5980ceba5e

