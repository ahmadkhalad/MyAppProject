package com.example.akhaled.myapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class AddToolActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv1_tool,iv2_tool,iv3_tool;
    private EditText et_name,et_price,et_descripe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        iv1_tool = findViewById(R.id.image1_tool);
        iv2_tool = findViewById(R.id.image2_tool);
        iv3_tool = findViewById(R.id.image3_tool);

       iv1_tool.setOnClickListener(this);
       iv2_tool.setOnClickListener(this);
       iv3_tool.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

if (view.getId()==R.id.image1_tool||view.getId()==R.id.image2_tool||view.getId()==R.id.image3_tool) {


    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivityForResult(intent, 100);
}

        }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100&&resultCode==RESULT_OK)
        {

            iv1_tool.setImageBitmap((Bitmap)data.getExtras().get("data"));
            iv2_tool.setImageBitmap((Bitmap)data.getExtras().get("data"));
            iv3_tool.setImageBitmap((Bitmap)data.getExtras().get("data"));

        }
    }
}
