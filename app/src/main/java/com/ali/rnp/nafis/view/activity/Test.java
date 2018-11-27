package com.ali.rnp.nafis.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.rnp.nafis.R;

public class Test extends AppCompatActivity {

    EditText edtName;
    TextView btnSave;
    EditText edtAge;
    SharedPreferences shpref;
    public static final String Mypref="Myprefers";
    public static final String Name="namekey";
    public static final String sen="agekey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        edtName= (EditText) findViewById(R.id.nam_txt);
        edtAge= (EditText) findViewById(R.id.sen_txt);
        btnSave= (TextView) findViewById(R.id.btn_save);
        shpref=getSharedPreferences(Mypref, Context.MODE_PRIVATE);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=edtName.getText().toString();
                String a = edtAge.getText().toString();
                SharedPreferences.Editor sEdit=shpref.edit();
                sEdit.putString(Name,n);
                sEdit.putString(sen,a);
                sEdit.putInt("run_1",2);
                sEdit.apply();
                Toast.makeText(Test.this,"Infos Saved",Toast.LENGTH_LONG).show();
                Intent zz=new Intent(Test.this,Main_Activity.class);
                startActivity(zz);

            }
        });

    }

}
