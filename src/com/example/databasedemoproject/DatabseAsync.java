package com.example.databasedemoproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DatabseAsync extends AsyncTask<String , Integer, List<Employee>>{
	
	final Context context;
	final ProgressBar progressBar;
	final EmployeeAdapter adapter;
	
	public DatabseAsync(Context context, ProgressBar progressBar, EmployeeAdapter adapter){
		this.context = context;
		this.progressBar = progressBar;
		this.adapter = adapter;
	}

	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
		progressBar.setVisibility(View.VISIBLE);
		Toast.makeText(context, "Database Started", Toast.LENGTH_SHORT ).show();			
	}
	
	@Override
	protected List<Employee> doInBackground(String... params) {
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//
		String value = params[0];
		Log.e("DatabaseSync","doInBackGroung: parametrr - "+value);
		
		
		//return getEmployeeRecords();
		List<Employee> employeeList = new ArrayList<>();

		// fill the list using database
		DbOpenHelper dbOpenHelper = new DbOpenHelper(context);
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from " + DbOpenHelper.TABLE_NAME,	null);// query//it returns cursor
		//
		int progress = 0;
		if (cursor.moveToFirst()) {
			do {
				progress++;
				int idIndex = cursor.getColumnIndex("id");
				String id = cursor.getString(idIndex);

				int nameIndex = cursor.getColumnIndex("name");
				String name = cursor.getString(nameIndex);

				int designationIndex = cursor.getColumnIndex("designation");
				String designation = cursor.getString(designationIndex);

				Employee emp = new Employee();
				emp.setEmpId(id);
				emp.setEmpName(name);
				emp.setEmpDesignation(designation);

				// add to list

				employeeList.add(emp);
				
				//to knw how many resordds
				publishProgress(progress);
				

			} while (cursor.moveToNext());
		}

		return employeeList;
	}
	
	@Override
	protected void onPostExecute(List<Employee> result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		Toast.makeText(context, "Database Ended", Toast.LENGTH_SHORT ).show();
		progressBar.setVisibility(View.GONE);
		adapter.addEmploees(result);
	}
	
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
		
		int progress = values[0];
		Log.e("AsyncTask","OnprogressUpdate: record recived "+progress);
	}		
}