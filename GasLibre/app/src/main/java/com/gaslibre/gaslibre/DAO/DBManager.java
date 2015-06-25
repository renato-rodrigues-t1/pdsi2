/*
 * Criado por: renato
 * 
 */

package com.gaslibre.gaslibre.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
	
	// db version
		public static final int DATABASE_VERSION = 1;

		// db name
		public static final String DATABASE_NAME = "gasLibre";

        //creating tables
        public static final String TABLE_USER= "tab_user";

        //defining columns
		//user
		public static final String TABLE_USER_ID = "user_id";
		public static final String TABLE_USER_NAME = "user_name";
        public static final String TABLE_USER_EMAIL = "email";
        public static final String TABLE_USER_SENHA = "user_senha";

		// sql to create tables
        //user
		public static final String CRIATE_TABLE_USER = "create table "
				+ TABLE_USER + " ( " 
				+ TABLE_USER_ID + " integer primary key AUTOINCREMENT,"
				+ TABLE_USER_NAME + " text not null,"
                + TABLE_USER_EMAIL + "  text, "
                + TABLE_USER_SENHA + " text "
				+ ");";

		public DBManager(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(CRIATE_TABLE_USER);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
}