package com.example.htmjs.bookapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddBooks extends AppCompatActivity {

    private ProgressDialog progressDialog;

    EditText bookName;
    EditText authorName;
    EditText loanDate;
    EditText buyDate;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        bookName = findViewById(R.id.tbKirjaNimi);
        authorName = findViewById(R.id.tbKirjailijaNimi);
        loanDate = findViewById(R.id.tbLainauspvm);
        buyDate = findViewById(R.id.tbOstopvm);

        progressDialog = new ProgressDialog(AddBooks.this);

        add = findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Lisätään kirja");
                progressDialog.show();

                String kirja = bookName.getText().toString().trim();
                String kirjailija = authorName.getText().toString().trim();
                String laina = loanDate.getText().toString().trim();
                String osto = buyDate.getText().toString().trim();

                addBooks(kirja, kirjailija, laina, osto);

            }
        });
    }

    private void addBooks(final String _book, final String _author, final String _loan, final String _buy) {

        final String addBooksUrl = "http://192.168.56.1/jerephp/KirjaSovellus/add_book.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, addBooksUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Kirjan lisäys", response);
                progressDialog.dismiss();
                Toast.makeText(AddBooks.this, "Kirjan lisäys onnistui", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Kirjan lisäys", "Error: " + error.getMessage());
                progressDialog.dismiss();
                Toast.makeText(AddBooks.this, "Kirjan lisäys epäonnistui", Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("kirja_nimi", _book);
                params.put("kirjailija_nimi", _author);
                params.put("lainauspvm", _loan);
                params.put("ostopvm", _buy);

                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }


}
