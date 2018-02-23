package com.example.akhaled.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
//
//import org.apache.http.*;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.akhaled.myapp.R.layout.activity_login;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_email, et_password;
    private Button btn_login, btn_register;
    //
    private String send_num;

    private ProgressDialog bar;
    private ImageView iv_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_login);


      iv_login = findViewById(R.id.imageViewLogin);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);


        bar = new ProgressDialog(this);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            login();

        } else if (view.getId() == R.id.btn_register) {
            register();

        }

    }

    private void register() {
bar.setMessage("please wait ....");
bar.show();
        Intent reg_Intent = new Intent(this, RegistrationActivity.class);
        bar.dismiss();
        startActivity(reg_Intent);
    }

    private void login() {
        checkOnEmailAndPass();

        RequestParams params = new RequestParams();
        params.put("email",et_email.getText().toString());
        params.put("password",et_password.getText().toString());

        bar.setMessage("Please Wait....");
        bar.show();

        AsyncHttpClient client = new AsyncHttpClient();
      //  client.setTimeout(600000000);

        client.post("https://ahmedosama25.000webhostapp.com/Login.php",params,new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(String content) {


                try {

                    JSONObject jsonObject=new JSONObject(content);
                    JSONArray jsonArray=jsonObject.getJSONArray("server_response");
                    JSONObject jo=jsonArray.getJSONObject(0);
                    String code = jo.getString("code");

                    if (code.equals("login_true")){
                        bar.dismiss();
                        Toast.makeText(LoginActivity.this, "Success Log", Toast.LENGTH_SHORT).show();
                        Intent homeIntent=new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(homeIntent);
                    }
                    else if(code.equals("login_false")){

                        Toast.makeText(LoginActivity.this, "Sorry...Try Again", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(LoginActivity.this, "Error Occurred ('server`s json might be invalid')", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Throwable error, String content) {


                if(statusCode==404){
                    bar.dismiss();
                    Toast.makeText(LoginActivity.this, "Requested Resource Not Found", Toast.LENGTH_SHORT).show();
                }
                else if (statusCode==500){
                    bar.dismiss();

                    Toast.makeText(LoginActivity.this, "Something went Wrong at Server end ", Toast.LENGTH_SHORT).show();
                }
                else {
                    bar.dismiss();

                    Toast.makeText(LoginActivity.this, "Un Expected Error", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void checkOnEmailAndPass() {

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty())

        {

            et_email.setError("Enter Email");

        } else if (password.isEmpty())

        {

            et_password.setError("Enter Password");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())

        {

            et_email.setError("Email Is Not Formated");
        } else

        {


           // Toast.makeText(this, "you logged In", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
