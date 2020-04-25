package com.example.android_lab03_client;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phoneNum;
    private EditText webAddress;
    private EditText emailAddress;
    private EditText registerText;
    private final String ACTION_REGISTER = "com.action.android_lab03.register";
    private static final int REGISTER_ACTIVITY_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNum = (EditText) findViewById(R.id.editTextPhoneNum);
        webAddress = (EditText) findViewById(R.id.editTextWebAddress);
        emailAddress = (EditText) findViewById(R.id.editTextEmailAddress);
        registerText = (EditText) findViewById(R.id.editTextName);
    }

    public void dialNumber(View view) {
        String phoneNumData = "tel:" + phoneNum.getText().toString();
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(phoneNumData));
        if( dialIntent.resolveActivity(getPackageManager()) != null )
            startActivity(dialIntent);
    }

    public void goToSite(View view) {
        Uri webpage = Uri.parse(webAddress.getText().toString());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        if( webIntent.resolveActivity(getPackageManager()) != null )
            startActivity(webIntent);
    }

    public void sendMail(View view) {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailAddress.getText().toString());
        if( emailIntent.resolveActivity(getPackageManager()) != null )
            startActivity(emailIntent);
    }

    public void registerFullName(View view) {
        Intent registerIntent = new Intent(ACTION_REGISTER);
        if( registerIntent.resolveActivity(getPackageManager()) != null )
            startActivityForResult(registerIntent, REGISTER_ACTIVITY_REQUEST_CODE);
        else
            Toast.makeText(getApplicationContext(), "No app found", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String gender = data.getStringExtra("gender");
        String firstName = data.getStringExtra("firstName");
        String lastName = data.getStringExtra("lastName");

        if(requestCode == REGISTER_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK){
            registerText.setText(gender + " " + firstName + " " + lastName);
        }
    }
}
