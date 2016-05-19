package com.example.databasedemoproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EmployeeAdapter extends BaseAdapter {
	List<Employee> mList = new ArrayList<>();
	Context mContext;
	String TAG = "EmployeeAdapter";

	public EmployeeAdapter(Context mContext) {
		this.mContext = mContext;
	}

	public void addEmploees(List<Employee> empployeeList) {
		mList = new ArrayList<>(empployeeList);
		notifyDataSetChanged();// refresh adapter after modifying the list
	}

	@Override
	public int getCount() {

		return mList.size();
	}

	@Override
	public Employee getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		// infalte
		View view = LayoutInflater.from(mContext).inflate(
				R.layout.view_records_row, null);

		TextView designation = (TextView) view
				.findViewById(R.id.txt_designation);
		TextView id = (TextView) view.findViewById(R.id.txt_id);
		TextView name = (TextView) view.findViewById(R.id.txt_name);

		Button btnUpdate = (Button) view.findViewById(R.id.btn_update);

		Button btnDelete = (Button) view.findViewById(R.id.btn_delete);

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// perform action

				DbOpenHelper dbOpenHelper = new DbOpenHelper(mContext);
				SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

				long ide = db.delete(DbOpenHelper.TABLE_NAME, "id=?",
						new String[] { getItem(position).getEmpId() });

				/*
				 * try{
				 * 
				 * long ide = db.delete(DbOpenHelper.TABLE_NAME, "id ?", );
				 * 
				 * //db.execSQL("DELETE FROM " + DbOpenHelper.TABLE_NAME+
				 * " WHERE "+getItem(position).getEmpId()); }catch(Exception
				 * ex){ ex.printStackTrace(); }
				 */

				if (ide < 0) {
					Log.e(TAG, "deleteEmployee: employee data deleted failed !");

				} else {
					Log.i(TAG,
							"deleteEmployee: employee data deleted Sucessfull !");
					Toast.makeText(mContext, "Deletd successfully",
							Toast.LENGTH_LONG).show();

					mList.remove(position);
					notifyDataSetChanged();

				}

			}
		});

		Employee employee = getItem(position);

		// setting data
		id.setText(employee.getEmpId());
		name.setText(employee.getEmpName());
		designation.setText(employee.getEmpDesignation());

		return view;
	}

}
