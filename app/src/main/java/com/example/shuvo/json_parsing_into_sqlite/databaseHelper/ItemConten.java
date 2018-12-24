package com.example.shuvo.json_parsing_into_sqlite.databaseHelper;

import android.provider.BaseColumns;

public class ItemConten {

    public ItemConten(){

    }

    public static final class ItemEntry implements BaseColumns {

        public static final String TABLE_NAME = "news";
        public static final String POST_CONTENT = "post_content";
        public static final String POST_TITTLE = "tittle";
        public static final String IMAGE_SRC = "image_src";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
