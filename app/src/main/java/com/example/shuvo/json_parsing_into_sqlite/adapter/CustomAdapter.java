package com.example.shuvo.json_parsing_into_sqlite.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shuvo.json_parsing_into_sqlite.R;
import com.example.shuvo.json_parsing_into_sqlite.databaseHelper.ItemConten;
import com.example.shuvo.json_parsing_into_sqlite.model.Item;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    List<Item> itemList = new ArrayList<>();
    Context mContext;
    Cursor mCursor;
    SQLiteDatabase sqLiteDatabase;

    public CustomAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.itemList = itemList;
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        CustomAdapter.CustomViewHolder hold = new CustomAdapter.CustomViewHolder(view);

        return (hold);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));

        if (!mCursor.moveToPosition(position)) {
            return;
        }

        holder.tittle.setText(mCursor.getString(mCursor.getColumnIndex(ItemConten.ItemEntry.POST_TITTLE)));
        holder.content.setText(mCursor.getString(mCursor.getColumnIndex(ItemConten.ItemEntry.POST_CONTENT)));

        Picasso.Builder builder = new Picasso.Builder(mContext);

        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(mCursor.getString(mCursor.getColumnIndex(ItemConten.ItemEntry.IMAGE_SRC)))
                .into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView content,tittle;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            content = itemView.findViewById(R.id.post_Content);
            tittle = itemView.findViewById(R.id.title);
        }
    }


}
