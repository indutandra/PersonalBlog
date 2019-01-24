package com.example.indu.personalblog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "feedsManager";
    private static final String TABLE_FEEDS = "feeds";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FEEDS_TABLE = "CREATE TABLE " + TABLE_FEEDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT" + ")";
        db.execSQL(CREATE_FEEDS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    void addFeed(Feed feed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, feed.getTitle()); // feedtitle
        values.put(KEY_DESCRIPTION, feed.getDescription()); // feeddescription

        // Inserting Row
        db.insert(TABLE_FEEDS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single feed
    Feed getFeed(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FEEDS, new String[] { KEY_ID,
                        KEY_TITLE, KEY_DESCRIPTION }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Feed feed = new Feed(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return feed
        return feed;
    }

    // code to get all feeds in a list view
    public List<Feed> getAllFeeds() {
        List<Feed> feedList = new ArrayList<Feed>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FEEDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Feed feed = new Feed();
                feed.setID(Integer.parseInt(cursor.getString(0)));
                feed.setTitle(cursor.getString(1));
                feed.setDescription(cursor.getString(2));
                // Adding feed to list
                feedList.add(feed);
            } while (cursor.moveToNext());
        }

        // return feed list
        return feedList;
    }

    // code to update the single feed
    public int updateFeed(Feed feed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, feed.getTitle());
        values.put(KEY_DESCRIPTION, feed.getDescription());

        // updating row
        return db.update(TABLE_FEEDS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(feed.getID()) });
    }

    // Deleting single feed
    public void deleteFeed(Feed feed) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FEEDS, KEY_ID + " = ?",
                new String[] { String.valueOf(feed.getID()) });
        db.close();
    }

    // Getting feeds Count
    public int getfeedsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_FEEDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
