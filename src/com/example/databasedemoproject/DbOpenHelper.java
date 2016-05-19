package com.example.databasedemoproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper {

	public static final String TAG = "DbOpenHelper";

	public static final String DATABSE_NAME = "employee.db";
	public static final String TABLE_NAME = "employee";
	public String CREATE_TABLE = String
			.format("create table %s (id INTEGER primary key, name TEXT, designation TEXT);",
					TABLE_NAME);
	public String DELETE_TABLE = "delete table" + TABLE_NAME;

	public DbOpenHelper(Context context) {

		super(context, DATABSE_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	public long insertEmployee(String empId, String empName, String designation) {

		SQLiteDatabase database = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("id", empId);
		values.put("name", empName);
		values.put("designation", designation);

		long id = database.insert(TABLE_NAME, null, values); // this will return
																// long datatype
																// values

		if (id < 0) {
			Log.e(TAG, "insertEmployee: employee data insertion failed !");
		} else {
			Log.i(TAG, "insertEmployee: employee data insertion Sucessfull !");
		}
		return id;

	}

}
