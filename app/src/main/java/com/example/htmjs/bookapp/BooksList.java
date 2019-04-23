package com.example.htmjs.bookapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BooksList extends AppCompatActivity {

    private ArrayList<Books> _books;
    private BooksAdapter adapter;
    private ListView lv;

    Button toMainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        _books = new ArrayList<Books>();
        lv = findViewById(R.id.lvBooks);

        getBooks();

        toMainPage = findViewById(R.id.btnToMainPage);
        toMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BooksList.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private void getBooks() {

        final String getBooksUrl = "http://10.0.2.2:50645/api/Books";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getBooksUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Kirjojen haku", response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //JSONArray jsonArray = jsonObject.getJSONArray("");

                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        _books.add(new Books(
                                object.getInt("id"),
                                "Kirja: " + object.getString("kirja_nimi"),
                                "Kirjailija: " + object.getString("kirjailija_nimi"),
                                "Lainauspäivämäärä: " + object.getString("lainauspvm"),
                                "Osto/saantipäivämäärä: " + object.getString("ostopvm"),
                                "Lukemispäivämäärä: " + object.getString("lukemispvm"),
                                "Palautuspäivämäärä: " + object.getString("palautuspvm")
                        ));
                    }
                    adapter = new BooksAdapter(BooksList.this, _books);
                    lv.setAdapter(adapter);
                } catch (JSONException ex) {
                    ex.printStackTrace();
                    ex.getMessage();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Kirjojen haku", "Error: " + error.getMessage());
            }
        }
        );

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
