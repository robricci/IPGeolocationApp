package com.icloud.ricci_roberto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;


import static android.util.Patterns.IP_ADDRESS;
import static com.icloud.ricci_roberto.R.string.app_name;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText addressText = (EditText) findViewById(R.id.address_edittext);
        final Button button = (Button) findViewById(R.id.button1);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String addressIn = addressText.getText().toString();

                if ((Patterns.WEB_URL.matcher(addressIn).matches()) || (Patterns.IP_ADDRESS.matcher(addressIn).matches()))
                 {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("KEY", addressIn);
                    startActivity(intent);
                }
                else {
                    Toast newToast = Toast.makeText(MainActivity.this, "URL non valido!", Toast.LENGTH_LONG);
                    newToast.setGravity(Gravity.CENTER, 0, 0);
                    newToast.show();
                }




            }
        });




    }

}
