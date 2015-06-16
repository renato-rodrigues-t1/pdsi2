package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import Model.User;

public class UserDAO {
	
	private SQLiteDatabase database;
	private DBManager DBManager;
	private Context context;

	private String[] columnsTableUSER = {
			DBManager.TABLE_USER_ID,
			DBManager.TABLE_USER_NAME,
            DBManager.TABLE_USER_EMAIL,
            DBManager.TABLE_USER_SENHA
	};
	
	public UserDAO(Context context){
		DBManager = new DBManager(context);
		this.context = context; 
	}

	public void open() {
		database = DBManager.getWritableDatabase();
	}

	public void close() {
		DBManager.close();
	}
	
	public long registraUsuario(User user){

        ContentValues values= new ContentValues();
		//values.put(columnsTableUSER[0], user.getId());// naum precisa, Id eh autoincrement
        values.put(columnsTableUSER[1], user.getName());
        values.put(columnsTableUSER[2], user.getEmail());
        values.put(columnsTableUSER[3], user.getSenha());

		open();
		long result = -1;
		try{
			result = database.insertOrThrow(DBManager.TABLE_USER, null, values);
		}catch(SQLiteConstraintException e){
			result = -1;
		}catch (Exception e) {
			result = -1;
		}

		close();
		return result;
	}
	
	public long deleteUsersLocal() {//delete all the users
		open();
		int result = database.delete(DBManager.TABLE_USER , null, null);
		close();
		return result;
	}
	
	//assuming the table has only one user
	public User getUSER() {
		User user = null;
		String selectQuery =
                " select *"
				+ " from " + DBManager.TABLE_USER;
		
		open();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if(cursor.moveToFirst())
			user = new User(cursor.getString(0).toString(), cursor.getString(1).toString(), cursor.getString(2).toString(),cursor.getString(3).toString());
		close();
		return user;
	}

    public boolean update(User user){
        return false;
    }

    public boolean AutenticaUsuario(String email, String senha){
        return false;
    }

}