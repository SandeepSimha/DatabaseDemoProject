package com.example.databasedemoproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmployeeDatabaseActivity extends Activity implements
		OnClickListener {

	EditText etDesignation;
	EditText etId;
	EditText etName;
	Button btnSave;
	Button btnViewRecords;
	final String TAG = "EmployeeDatabseActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_databse);

		etId = (EditText) findViewById(R.id.et_id);
		etName = (EditText) findViewById(R.id.et_name);
		etDesignation = (EditText) findViewById(R.id.et_designation);

		btnSave = (Button) findViewById(R.id.btn_save);
		btnViewRecords = (Button) findViewById(R.id.btn_view_records);

		Log.i(TAG, "oncreate method");

		btnSave.setOnClickListener(this);
		btnViewRecords.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_save) {
			onClickSave();

		} else if (v.getId() == R.id.btn_view_records) {
			onClickViewRecords();

		}

	}

	private void onClickViewRecords() {

		startActivity(new Intent(this, DatabseViewRecordsActivity.class));

	}

	private void onClickSave() {

		String empId = etId.getText().toString().trim();
		String name = etName.getText().toString().trim();
		String designation = etDesignation.getText().toString().trim();

		DbOpenHelper dbOpenHelper = new DbOpenHelper(this);
		long id = dbOpenHelper.insertEmployee(empId, name, designation);

		if (id < 0) {
			Toast.makeText(this, "Insertion failed!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this, "Data saved sucessfully!", Toast.LENGTH_LONG)
					.show();
		}

		// reset data

		etId.setText("");
		etName.setText("");
		etDesignation.setText("");
		etId.requestFocus();

	}

}
