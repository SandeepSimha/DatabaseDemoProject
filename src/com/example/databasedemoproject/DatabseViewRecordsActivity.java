package com.example.databasedemoproject;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DatabseViewRecordsActivity extends AppCompatActivity {
	ListView listView;
	EmployeeAdapter empAdap;
	ProgressBar progressBar;

	private Handler mHandler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			//
			if (msg.what == 1) {
				progressBar.setVisibility(View.VISIBLE);
				Toast.makeText(DatabseViewRecordsActivity.this,
						"Database Started", Toast.LENGTH_SHORT).show();

			} else if (msg.what == 100) {
				List<Employee> list = (List<Employee>) msg.obj;
				empAdap.addEmploees(list);

			} else if (msg.what == 2) {
				Toast.makeText(DatabseViewRecordsActivity.this,
						"Database Ended", Toast.LENGTH_SHORT).show();
				progressBar.setVisibility(View.GONE);
			}
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_view_records);

		// t=run on UI thraed
		empAdap = new EmployeeAdapter(this);
		
		listView = (ListView) findViewById(R.id.listView);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		listView.setAdapter(empAdap);
		
		// empAdap.addEmploees(getEmployeeRecords());
		// caalWorkerThread();

		DatabseAsync databaseAsync = new DatabseAsync(this, progressBar,
				empAdap);
		databaseAsync.execute("Employee Records");

	}/*

	private void callWorkerThread() {
		Thread workerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				// get list and set to adapter
				sendMessage(1, null);
				final List<Employee> list = getEmployeeRecords();

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				 * runOnUiThread(new Runnable() {
				 * 
				 * @Override public void run() { // TODO Auto-generated method
				 * stub empAdap.addEmploees(getEmployeeRecords());
				 * 
				 * } });
				 
				sendMessage(100, list);
				sendMessage(2, null);

			}
		});
		workerThread.start();

		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle("Databse Demo");
		actionBar.setDisplayHomeAsUpEnabled(true);

	}

	private void sendMessage(int requestCode, Object obj) {
		Message message = new Message();
		message.what = requestCode;// request code
		message.obj = obj;
		mHandler.sendMessage(message);
	}

*/	/*
	 * public List<Employee> getEmployeeRecords() {
	 * 
	 * List<Employee> employeeList = new ArrayList<>();
	 * 
	 * // fill the list using database DbOpenHelper dbOpenHelper = new
	 * DbOpenHelper(this); SQLiteDatabase db =
	 * dbOpenHelper.getWritableDatabase(); Cursor cursor =
	 * db.rawQuery("select * from " + DbOpenHelper.TABLE_NAME, null);//
	 * query//it returns cursor // if (cursor.moveToFirst()) { do { int idIndex
	 * = cursor.getColumnIndex("id"); String id = cursor.getString(idIndex);
	 * 
	 * int nameIndex = cursor.getColumnIndex("name"); String name =
	 * cursor.getString(nameIndex);
	 * 
	 * int designationIndex = cursor.getColumnIndex("designation"); String
	 * designation = cursor.getString(designationIndex);
	 * 
	 * Employee emp = new Employee(); emp.setEmpId(id); emp.setEmpName(name);
	 * emp.setEmpDesignation(designation);
	 * 
	 * // add to list
	 * 
	 * employeeList.add(emp);
	 * 
	 * } while (cursor.moveToNext()); }
	 * 
	 * return employeeList; }
	 */
}