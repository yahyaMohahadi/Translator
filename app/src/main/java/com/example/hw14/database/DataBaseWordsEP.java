package com.example.hw14.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseWordsEP extends SQLiteOpenHelper {

    public static final String DATA_BASE_FILE_NAME = "words_english_persian.db";
    public static final int VERSION = 1;

    public static class COLS {
        public static final String TABLE_NAME = "english_persian";
        public static final String COL_ENGLISH = "persian";
        public static final String COL_PERSIAN = "english";
    }

    public DataBaseWordsEP(@Nullable Context context) {
        super(context, DATA_BASE_FILE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE  " + COLS.TABLE_NAME + " ("
                        + COLS.COL_ENGLISH + " TEXT NOT NULL ,"
                        + COLS.COL_PERSIAN + " TEXT NOT NULL " +
                        ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
