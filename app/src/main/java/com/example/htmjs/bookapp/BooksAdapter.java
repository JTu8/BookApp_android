package com.example.htmjs.bookapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends BaseAdapter {

    private ArrayList<Books> booksList;
    private Context context;
    private LayoutInflater layoutInflater;

    public BooksAdapter(Context _context, ArrayList<Books> _books) {
        super();
        context = _context;
        booksList = _books;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int i) {
        return booksList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = layoutInflater.inflate(R.layout.books_list, null);
        Books b = booksList.get(i);

        TextView bookName = view.findViewById(R.id.tvKirjaNimi);
        bookName.setText(b.getKirja_nimi());

        TextView authorName = view.findViewById(R.id.tvKirjailijaNimi);
        authorName.setText(b.getKirjailija_nimi());

        TextView loanDate = view.findViewById(R.id.tvLainauspvm);
        loanDate.setText(b.getLainauspvm());

        TextView buyDate = view.findViewById(R.id.tvOstopvm);
        buyDate.setText(b.getOstopvm());

        TextView readDate = view.findViewById(R.id.tvLukemispvm);
        readDate.setText(b.getLukemispvm());

        TextView returnDate = view.findViewById(R.id.tvPalautuspvm);
        returnDate.setText(b.getPalautuspvm());

        return view;
    }
}
