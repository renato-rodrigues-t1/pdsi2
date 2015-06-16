/*
 * Criado por: renato
 * 
 */

package DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager extends SQLiteOpenHelper {
	
	// db version
		public static final int DATABASE_VERSION = 1;

		// db name
		public static final String DATABASE_NAME = "macaw";

        //creating tables
        public static final String TABLE_CONVERSATION= "tab_conversation";
        public static final String TABLE_INTEREST= "tab_interest";
        public static final String TABLE_LANGUAGE= "tab_language";
        public static final String TABLE_MATCH= "tab_match";
        public static final String TABLE_MESSAGE= "tab_message";
        public static final String TABLE_REPORT= "tab_report";
        public static final String TABLE_USER= "tab_user";

        //defining columns
		//user
		public static final String TABLE_USER_ID = "user_id";
		public static final String TABLE_USER_ID_SERVER = "user_id_server";
        public static final String TABLE_USER_ID_FACEBOOK= "user_id_facebook";
		public static final String TABLE_USER_NAME = "user_name";
        public static final String TABLE_USER_EMAIL = "email";
        public static final String TABLE_USER_COUNTRY = "user_country";
        public static final String TABLE_USER_PICTURE = "user_picture";
        public static final String TABLE_USER_DATE_OF_BIRTH = "user_date_of_birth";
        public static final String TABLE_USER_DESCRIPTION = "user_description";
		public static final String TABLE_USER_CREATED = "user_created";
		public static final String TABLE_USER_MODIFIED = "user_modified";
        public static final String TABLE_USER_FIRST_LOGIN = "user_first_login";
        public static final String TABLE_USER_LAST_LOGIN = "user_last_login";

		// sql to create tables
        //user
		public static final String CRIATE_TABLE_USER = "create table "
				+ TABLE_USER + " ( " 
				+ TABLE_USER_ID + " integer primary key AUTOINCREMENT,"
				+ TABLE_USER_ID_SERVER + " text null,"
                + TABLE_USER_ID_FACEBOOK+ " text, "
				+ TABLE_USER_NAME + " text not null,"
                + TABLE_USER_EMAIL + "  text, "
                + TABLE_USER_COUNTRY + " text, "
                + TABLE_USER_PICTURE + " text, "
                + TABLE_USER_DATE_OF_BIRTH + " text, "
                + TABLE_USER_DESCRIPTION + " text, "
                + TABLE_USER_CREATED + " text, "
                + TABLE_USER_MODIFIED + " text, "
                + TABLE_USER_FIRST_LOGIN + " text, "
                + TABLE_USER_LAST_LOGIN + " text "
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