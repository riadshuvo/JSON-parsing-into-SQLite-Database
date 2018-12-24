package com.example.shuvo.json_parsing_into_sqlite.adapter;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.shuvo.json_parsing_into_sqlite.R;
import com.example.shuvo.json_parsing_into_sqlite.databaseHelper.ItemConten;
import com.example.shuvo.json_parsing_into_sqlite.databaseHelper.ItemDBHelper;
import com.example.shuvo.json_parsing_into_sqlite.model.Item;
import com.example.shuvo.json_parsing_into_sqlite.model.News;
import com.example.shuvo.json_parsing_into_sqlite.network.GetDataService;
import com.example.shuvo.json_parsing_into_sqlite.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter mAdapter;
    private RecyclerView recyclerView;
    private SQLiteDatabase mDatabase;
    private ItemDBHelper dbHelper;
    private List<Item> item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbHelper = new ItemDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        if(getAllItems().getCount() != 0 && getAllItems() != null){
            Toast.makeText(this, "Getting data from database", Toast.LENGTH_SHORT).show();
            gettingDataFromDatabase();

        }else{
            Toast.makeText(this, "Getting data from server", Toast.LENGTH_SHORT).show();
            gettingDataFromServer();
        }


    }

    private void gettingDataFromServer(){
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<News> call = service.getTittle();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                generateDataList(response.body().getItems());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    private void gettingDataFromDatabase(){

        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new CustomAdapter(this, getAllItems());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void generateDataList(List<Item> itemList) {



        for (int i = 0; i < itemList.size(); i++) {
            long value = dbHelper.insertItems(mDatabase, itemList.get(i).getPostTitle(), itemList.get(i).getPostContent(),
                    itemList.get(i).getImgSrc());
            Log.e("MainActivity", "data insertion status =" + value);

        }


        recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new CustomAdapter(this, getAllItems());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private Cursor getAllItems() {
        return mDatabase.query(
                ItemConten.ItemEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                ItemConten.ItemEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }


    public void printDataToLog(View view) {

        Cursor mCursor = getAllItems();

        int totalItemCount = mCursor.getCount();

        for (int i = 0; i < totalItemCount; i++) {

            if (!mCursor.moveToPosition(i)) {
                return;
            }

            String name = mCursor.getString(mCursor.getColumnIndex(ItemConten.ItemEntry.POST_TITTLE));
            String content = mCursor.getString(mCursor.getColumnIndex(ItemConten.ItemEntry.POST_CONTENT));
            String image = mCursor.getString(mCursor.getColumnIndex(ItemConten.ItemEntry.IMAGE_SRC));
            Log.e("MainActivity", "post title =" + name+
            "\npost content="+content+
            "\nimage src="+image);


        }
    }
}
