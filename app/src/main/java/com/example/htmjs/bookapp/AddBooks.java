package com.example.htmjs.bookapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddBooks extends AppCompatActivity {

    private ProgressDialog progressDialog;

    EditText bookName;
    EditText authorName;
    EditText loanDate;
    EditText buyDate;
    Button add;
    Button toMainPage;
    CheckBox checkBoxLoan;
    CheckBox checkBoxBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);

        bookName = findViewById(R.id.tbKirjaNimi);
        authorName = findViewById(R.id.tbKirjailijaNimi);
        loanDate = findViewById(R.id.tbLainauspvm);
        loanDate.setVisibility(View.INVISIBLE);
        buyDate = findViewById(R.id.tbOstopvm);
        buyDate.setVisibility(View.INVISIBLE);

        checkBoxLoan = findViewById(R.id.cbCheckLoanDate);
        checkBoxLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxLoan.isChecked()) {
                    loanDate.setVisibility(View.VISIBLE);
                }
                else {
                    loanDate.setVisibility(View.INVISIBLE);
                }
            }
        });

        checkBoxBuy = findViewById(R.id.cdCheckBuyDate);
        checkBoxBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBoxBuy.isChecked()) {
                    buyDate.setVisibility(View.VISIBLE);
                }
                else {
                    buyDate.setVisibility(View.INVISIBLE);
                }
            }
        });


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

        toMainPage = findViewById(R.id.btnPaasivulle);
        toMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBooks.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addBooks(final String _book, final String _author, final String _loan, final String _buy) {

        final String addBooksUrl = "http://10.0.2.2:50645/api/Books";

        final JSONObject request = new JSONObject();
        try {
            request.put("kirja_nimi", _book);
            request.put("kirjailija_nimi", _author);
            request.put("lainauspvm", _loan);
            request.put("ostospvm", _buy);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, addBooksUrl, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean error = response.getBoolean("Error");
                    String message = response.getString("Message");

                    if (!error) {
                        JSONArray books = response.optJSONArray("books");

                        Log.d("Kirjan lisäys", response.toString());
                        Log.d("Lisäys,", request.toString());
                        progressDialog.dismiss();
                        Toast.makeText(AddBooks.this, "Kirjan lisäys onnistui", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(AddBooks.this, "Kirjan lisäys epäonnistui", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Kirjan lisäys", "Error: " + error.getMessage());
                progressDialog.dismiss();
            }
        }

        );

        Volley.newRequestQueue(this).add(objectRequest);
        
    }



}
