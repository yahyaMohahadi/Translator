package com.example.hw14.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hw14.database.DataBaseWordsEP;

import java.util.ArrayList;
import java.util.List;

public class RepositoryEP implements Reposible {
    private static List<String> mListEnglish = new ArrayList<>();
    private static List<String> mListPersian = new ArrayList<>();

    private static SQLiteDatabase DB;

    private static RepositoryEP mRepository;

    public static RepositoryEP newInstance(Context context) {
        if (mRepository == null) {
            mRepository = new RepositoryEP();
            DB = new DataBaseWordsEP(context).getWritableDatabase();
            updateLists();

        }
        return mRepository;
    }

    //english -->persian
    @Override
    public String getTranslateEnglish(String stringEnglish) {
        String translate = "";
        String[] words = stringEnglish.split(" ");
        for (String word : words) {
            if (mListEnglish.contains(stringEnglish)) {
                translate += mListPersian.get(mListEnglish.indexOf(stringEnglish)) + " ";
            } else {
                translate += "(no result) ";
            }
        }
        return translate;
    }

    //persian -->english
    @Override
    public String getTranslatePersian(String stringPersian) {
        String translate = "";
        String[] words = stringPersian.split(" ");
        for (String word : words) {
            if (mListPersian.contains(stringPersian)) {
                translate += mListEnglish.get(mListPersian.indexOf(stringPersian)) + " ";
            } else {
                translate += "(no result) ";
            }
        }
        return translate;
    }

    @Override
    public void addTranslate(String stringEnglish, String stringPersian) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseWordsEP.COLS.COL_ENGLISH, stringEnglish.toLowerCase());
        contentValues.put(DataBaseWordsEP.COLS.COL_PERSIAN, stringPersian.toLowerCase());
        DB.insert(
                DataBaseWordsEP.COLS.TABLE_NAME,
                null,
                contentValues
        );
        updateLists();
    }

    private static void updateLists() {
        mListEnglish.removeAll(mListEnglish);
        mListPersian.removeAll(mListPersian);
        Cursor cursor = DB.rawQuery(
                "SELECT * FROM " + DataBaseWordsEP.COLS.TABLE_NAME
                , null);
        try {
            if (cursor.moveToFirst() && cursor.getCount() > 0) {
                while (!cursor.isAfterLast()) {
                    String english = cursor.getString(cursor.getColumnIndex(DataBaseWordsEP.COLS.COL_ENGLISH));
                    String persian = cursor.getString(cursor.getColumnIndex(DataBaseWordsEP.COLS.COL_PERSIAN));
                    mListPersian.add(persian.toLowerCase());
                    mListEnglish.add(english.toLowerCase());
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {

        } finally {
            cursor.close();
        }
    }

    public int numberOfWords() {
        return mListEnglish.size();
    }
}

interface Reposible {

    String getTranslateEnglish(String stringEnglish);

    String getTranslatePersian(String stringPersian);

    void addTranslate(String stringEnglish, String stringPersian);

}