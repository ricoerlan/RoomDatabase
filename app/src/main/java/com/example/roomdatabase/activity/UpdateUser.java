package com.example.roomdatabase.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.MainActivity;
import com.example.roomdatabase.R;

public class UpdateUser extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button btUpdate, btDelete;
    EditText etNama, etNIM, etKejuruan, etAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        dbHelper = new DataHelper(this);

        etNama = (EditText) findViewById(R.id.etNama);
        etNIM = (EditText) findViewById(R.id.etNim);
        etKejuruan = (EditText) findViewById(R.id.etKejuruan);
        etAlamat = (EditText) findViewById(R.id.etAlamat);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            etNama.setText(cursor.getString(0).toString());
            etNIM.setText(cursor.getString(1).toString());
            etKejuruan.setText(cursor.getString(2).toString());
            etAlamat.setText(cursor.getString(3).toString());
        }
        btUpdate = (Button) findViewById(R.id.btUpdate);
        btDelete = (Button) findViewById(R.id.btDelete);
        btUpdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update biodata set nama='"+
                        etNama.getText().toString() +"', Nama='" +
                        etNIM.getText().toString()+"', NIM='"+
                        etKejuruan.getText().toString() +"', Kejuruan='" +
                        etAlamat.getText().toString()+"' alamat=''");
                Toast.makeText(getApplicationContext(),
                        "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        btDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}