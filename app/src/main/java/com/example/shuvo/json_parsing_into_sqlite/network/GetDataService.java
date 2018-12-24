package com.example.shuvo.json_parsing_into_sqlite.network;


import com.example.shuvo.json_parsing_into_sqlite.model.News;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/api/index.php?task=selected_news&fbclid=IwAR01HMtV7CvEC1dapk7JIEEJ3ZpEymFPXz4Bb_AEJDfkjfubZOPB-u5kv3Y")
    Call<News> getTittle();

}
