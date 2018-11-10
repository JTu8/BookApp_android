package com.example.htmjs.bookapp;

public class Books {

    private int ID;
    private String kirja_nimi;
    private String kirjailija_nimi;
    private String lainauspvm;
    private String ostopvm;
    private String lukemispvm;
    private String palautuspvm;

    public Books(int _ID, String _kirja_nimi, String _kirjailija_nimi, String _lainauspvm, String _ostopvm, String _lukemispvm, String _palautuspvm) {

        this.ID = _ID;
        this.kirja_nimi = _kirja_nimi;
        this.kirjailija_nimi = _kirjailija_nimi;
        this.lainauspvm = _lainauspvm;
        this.ostopvm = _ostopvm;
        this.lukemispvm = _lukemispvm;
        this.palautuspvm = _palautuspvm;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKirja_nimi() {
        return kirja_nimi;
    }

    public void setKirja_nimi(String kirja_nimi) {
        this.kirja_nimi = kirja_nimi;
    }

    public String getKirjailija_nimi() {
        return kirjailija_nimi;
    }

    public void setKirjailija_nimi(String kirjailija_nimi) {
        this.kirjailija_nimi = kirjailija_nimi;
    }

    public String getLainauspvm() {
        return lainauspvm;
    }

    public void setLainauspvm(String lainauspvm) {
        this.lainauspvm = lainauspvm;
    }

    public String getOstopvm() {
        return ostopvm;
    }

    public void setOstopvm(String ostopvm) {
        this.ostopvm = ostopvm;
    }

    public String getLukemispvm() {
        return lukemispvm;
    }

    public void setLukemispvm(String lukemispvm) {
        this.lukemispvm = lukemispvm;
    }

    public String getPalautuspvm() {
        return palautuspvm;
    }

    public void setPalautuspvm(String palautuspvm) {
        this.palautuspvm = palautuspvm;
    }
}
