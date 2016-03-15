package pbru.yaemsak.chonlakan.showpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by chonlakan on 5/1/2559.
 */
public class ManagTABLE {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase WritSqLiteDatabase, readSqLiteDatabase;

    public static final String TABLE_USER = "userTABLE";
    public static final String COLUMN_id = "_id";
    public static final String COLUMN_User = "User";
    public static final String COLUMN_Password = "Password";
    public static final String COLUMN_Name = "Name";
    public static final String COLUMN_Surname = "Surname";
    public static final String COLUMN_Address = "Address";
    public static final String COLUMN_Email = "Email";
    public static final String COLUMN_Point = "Point";

    public static final String TABLE_promotion = "promotionTABLE";
    public static final String COLUMN_NamePromotion = "NamePromotion";
    public static final String COLUMN_Condition = "Condition";
    public static final String COLUMN_PictPromotion = "PictPromotion";
    public static final String COLUMN_TimeStart = "TimeStart";
    public static final String COLUMN_TimeEnd = "TimeEnd";

    public static final String COLUMN_Place = "Place";
    public static final String COLUMN_Lat = "Lat";
    public static final String COLUMN_Lng = "Lng";

    public static final String TABLE_REWARD = "rewardTABLE";
    public static final String COLUMN_Reward_Name = "Reward_Name";
    public static final String COLUMN_Use_Point = "Use_Point";
    public static final String COLUMN_Pict_Reward = "Pict_Reward";


    public ManagTABLE(Context context) {

        //Connected database
        objMyOpenHelper = new MyOpenHelper(context);
        WritSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();


    }//Constructor

    public long addReward(String strRewardName,
                          String strUsePoint,
                          String strPictReward) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_Reward_Name, strRewardName);
        contentValues.put(COLUMN_Use_Point, strUsePoint);
        contentValues.put(COLUMN_Pict_Reward, strPictReward);

        return WritSqLiteDatabase.insert(TABLE_REWARD, null, contentValues);
    }

    public String[] searchUser(String strUser) {
        try {

            String[] resultStrings = null;
            Cursor objCursor = readSqLiteDatabase.query(TABLE_USER,
                    new String[]{COLUMN_id, COLUMN_User, COLUMN_Password,
                            COLUMN_Name, COLUMN_Surname, COLUMN_Address,
                            COLUMN_Email, COLUMN_Point},
                    COLUMN_User + "=?",
                    new String[]{String.valueOf(strUser)},
                    null, null, null, null);

            if (objCursor != null) {

                if (objCursor.moveToFirst()) {

                    resultStrings = new String[objCursor.getColumnCount()];
                    for (int i = 0; i < objCursor.getColumnCount(); i++) {
                        resultStrings[i] = objCursor.getString(i);
                    }

                }//if2
            }//if
            objCursor.close();//คืนหน่วยความจำที่เรียกใช้
            return resultStrings;


        } catch (Exception e) {
            return null;
        }


        //return new String[0];
    }


    public long addNewValueToPromotion(String strPromotion,
                                       String strCondition,
                                       String strPictPromotion,
                                       String strTimeStart,
                                       String strTimeEnd,
                                       String strPlace,
                                       String strLat,
                                       String strLng) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_NamePromotion, strPromotion);
        objContentValues.put(COLUMN_Condition, strCondition);
        objContentValues.put(COLUMN_PictPromotion, strPictPromotion);
        objContentValues.put(COLUMN_TimeStart, strTimeStart);
        objContentValues.put(COLUMN_TimeEnd, strTimeEnd);
        objContentValues.put(COLUMN_Place, strPlace);
        objContentValues.put(COLUMN_Lat, strLat);
        objContentValues.put(COLUMN_Lng, strLng);
        //Add
        return WritSqLiteDatabase.insert(TABLE_promotion, null, objContentValues);
    }

    /*protected long addNewValueToPlace(String strPlace,
                                      String strLat,
                                      String strLng) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_Place, strPlace);
        objContentValues.put(COLUMN_Lat, strLat);
        objContentValues.put(COLUMN_Lng, strLng);
        //Add
        return WritSqLiteDatabase.insert(TABLE_place, null, objContentValues);
    }*/

    public long addNewValueToUser(String strUser,
                                  String strPassword,
                                  String strName,
                                  String strSurname,
                                  String strAddress,
                                  String strEmail,
                                  String strPoint) {

        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_User, strUser);
        objContentValues.put(COLUMN_Password, strPassword);
        objContentValues.put(COLUMN_Name, strName);
        objContentValues.put(COLUMN_Surname, strSurname);
        objContentValues.put(COLUMN_Address, strAddress);
        objContentValues.put(COLUMN_Email, strEmail);
        objContentValues.put(COLUMN_Point, strPoint);
        //Add
        return WritSqLiteDatabase.insert(TABLE_USER, null, objContentValues);
    }

}//Main class
