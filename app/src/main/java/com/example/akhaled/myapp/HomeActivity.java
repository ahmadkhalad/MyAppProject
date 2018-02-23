package com.example.akhaled.myapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_home, btn_add, btn_dept;
    private ListView list_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        list_products = findViewById(R.id.List_products);
        btn_home = findViewById(R.id.btn_mainMenu);
        btn_add = findViewById(R.id.btn_addProduct);
        btn_dept = findViewById(R.id.btn_go_dept);

        btn_add.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (item.getItemId() == R.id.item_home) {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.item_search) {
searchCode();
            Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.item_logout) {

            Intent intent1 = new Intent(this, LoginActivity.class);
            startActivity(intent1);
            return true;

        } else if (item.getItemId() == R.id.item_setting) {

            Toast.makeText(this, "Setting Clicked", Toast.LENGTH_SHORT).show();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void searchCode() {
        // TODO: 31/01/2018 this here code for search 
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllProducts();
    }

    private void getAllProducts() {
        // TODO: 30/01/2018 preview all products in data base 
    }

    @Override
    public void onClick(View view) {

        // TODO: 30/01/2018 to add new product

        Intent intent_addProduct = new Intent(this, AddToolActivity.class);
        startActivity(intent_addProduct);
    }
}
