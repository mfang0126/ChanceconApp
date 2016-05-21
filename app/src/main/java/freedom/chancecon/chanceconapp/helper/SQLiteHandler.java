package freedom.chancecon.chanceconapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Freedom on 23/04/16.
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "android_api";

    private static final String TABLE_USER = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";

    //Gallery
    private static final String TABLE_GALLERY = "gallery";

    private static final String IMAGE_ID = "image_id";
    private static final String IMAGE_URL = "image_url";
    private static final String IMAGE_INFO = "image_info";
    private static final String IMAGE_TITLE = "image_title";
    private static final String IMAGE_GROUP = "image_group";
    private static final String IMAGE_CREATED_AT = "created_at";
    private static final String IMAGE_EMAIL = "email";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_LOGIN_TABLE);

        //gallery
        String CREATE_GALLERY_TABLE = "CREATE TABLE " + TABLE_GALLERY + "("
                + IMAGE_ID + " INTEGER PRIMARY KEY," + IMAGE_TITLE + " TEXT,"
                + IMAGE_INFO + " TEXT," + IMAGE_URL + " TEXT,"
                + IMAGE_GROUP + " TEXT UNIQUE," + IMAGE_EMAIL + " TEXT UNIQUE,"
                + IMAGE_CREATED_AT + " TEXT" + ")";
        db.execSQL(CREATE_GALLERY_TABLE);

        Log.d(TAG, "Database tables created");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GALLERY);

        onCreate(db);
    }

    public void addUser(String name, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_EMAIL, email);
        values.put(KEY_UID, uid);
        values.put(KEY_CREATED_AT, created_at);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.d(TAG, "New user inserted into SQLite: " + id);
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("name", cursor.getString(1));
            user.put("email", cursor.getString(2));
            user.put("uid", cursor.getString(3));
            user.put("created_at", cursor.getString(4));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Fetching user from SQLite: " + user.toString());

        return user;
    }

    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Deleted all user info from SQLite");
    }

    public HashMap<String, String> getGalleryDetails() {
        HashMap<String, String> gallery = new HashMap<>();
        String selectQuery = "SELECT * FROM " + TABLE_GALLERY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            gallery.put("image_title", cursor.getString(1));
            gallery.put("image_info", cursor.getString(2));
            gallery.put("image_url", cursor.getString(3));
            gallery.put("image_group", cursor.getString(4));
            gallery.put("email", cursor.getString(5));
            gallery.put("created_at", cursor.getString(6));
        }

        cursor.close();
        db.close();
        Log.d(TAG, "Fetching gallery from SQLite: " + gallery.toString());

        return gallery;
    }
}

