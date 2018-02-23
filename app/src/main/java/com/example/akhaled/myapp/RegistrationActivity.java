package com.example.akhaled.myapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.renderscript.Int4;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_fullNAme, et_email, et_passWord, et_phoneNumber;
    private ImageView iv_userRegister;
    private Button btn_signUp;


    private static final int GALLERY_REQUEST = 1;
    private static final int STORAGE_PERMISSION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        et_fullNAme = findViewById(R.id.et_fullName);
        et_email = findViewById(R.id.et_Email);
        et_passWord = findViewById(R.id.et_passWord);
        et_phoneNumber = findViewById(R.id.et_phoneNumber);

        iv_userRegister = findViewById(R.id.imageViewRegister);
        iv_userRegister.setOnClickListener(this);
        btn_signUp = findViewById(R.id.btn_register);
        btn_signUp.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        //defaultBack  super.onBackPressed();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.imageViewRegister) {
            Intent i = new Intent(Intent.ACTION_PICK);
            i.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, GALLERY_REQUEST);


        }
        else if (btn_signUp==view)
        {
            register();
            Intent i = new Intent(this,HomeActivity.class);
            startActivity(i);

        }
    }
    private void register() {


        String email = et_email.getText().toString();
        String password = et_passWord.getText().toString();
        String name = et_fullNAme.getText().toString();
        String phone = et_phoneNumber.getText().toString();

        if (email.isEmpty())

        {

            et_email.setError("Enter Email");

        }

        else if (phone.isEmpty())
        {
            et_phoneNumber.setError("Enter your Phone");
        }
        else if (name.isEmpty()) {
            et_fullNAme.setError("Enter Valid Name");
        } else if (password.isEmpty())

        {

            et_passWord.setError("Enter Password");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())

        {

            et_email.setError("Email Is Not Formated");
        }


    }





    Uri selectedImageuri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            selectedImageuri = data.getData();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                readFileFromSelectedURI();
            } else {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);

            }


        }
    }

    //for coming photo from galary
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readFileFromSelectedURI();
            } else {
                Toast.makeText(this, "cant read selected image", Toast.LENGTH_LONG).show();
            }

        }
    }

    //for set image in image view registration
    private void readFileFromSelectedURI() {


        Cursor cursor = getContentResolver().query(selectedImageuri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String imagePath = cursor.getString(0);
            cursor.close();
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            iv_userRegister.setImageBitmap(image);
        }


    }


}