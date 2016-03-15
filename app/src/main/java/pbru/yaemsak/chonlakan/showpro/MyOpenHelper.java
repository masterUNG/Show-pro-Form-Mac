package pbru.yaemsak.chonlakan.showpro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chonlakan on 5/1/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper {

    //Explicit
    public static final String DATABASE_NAME = "ShowPro.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_USER_TABLE = "create table userTABLE (" +
            "_id integer primary key, " +
            "User text," +
            "Password text," +
            "Name text," +
            "Surname text," +
            "Address text," +
            "Email text," +
            "Point text);";
    private static final String CREATE_PROMOTION_TABLE = "create table promotionTABLE(" +
            "_id integer primary key," +
            "NamePromotion text," +
            "Condition text," +
            "PictPromotion text," +
            "TimeStart text," +
            "TimeEnd text," +
            "Place text," +
            "Lat text," +
            "Lng text);";

    /*private static final String CREATE_PLACE_TABLE = "create table placeTABLE(" +
            "_id integer primary key," +
            "Place text," +
            "Lat text," +
            "Lng text);";       */

    private static final String CREATE_REWARD_TABLE = "create table rewardTABLE(" +
            "_id integer primary key," +
            "Reward_Name text," +
            "Use_Point text," +
            "Pict_Reward text);";


    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }// Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PROMOTION_TABLE);
       // db.execSQL(CREATE_PLACE_TABLE);
        db.execSQL(CREATE_REWARD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}// main class
