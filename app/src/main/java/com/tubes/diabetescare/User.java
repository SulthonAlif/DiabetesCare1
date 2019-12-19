package com.tubes.diabetescare;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class User implements Serializable {
    private String namaUser;
    private String emailUserr;
    private String passUserr;
    private String alamatUser;
    private String tempatLahirUser;
    private String tanggalLahirUser;
    private String jenisKelaminUser;
    private String hpUser;
    private String agamaUser;
    private String key;

    User(String nm, String mail,String ps, String almt, String tl, String tll, String jku, String hp, String agama) {
        namaUser = nm;
        emailUserr = mail;
        passUserr = ps;
        alamatUser = almt;
        tempatLahirUser = tl;
        tanggalLahirUser = tll;
        jenisKelaminUser = jku;
        hpUser = hp;
        agamaUser = agama;

    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getAlamatUser() {
        return alamatUser;
    }

    public void setAlamatUser(String alamatUser) {
        this.alamatUser = alamatUser;
    }

    public String getTempatLahirUser() {
        return tempatLahirUser;
    }

    public void setTempatLahirUser(String tempatLahirUser) {
        this.tempatLahirUser = tempatLahirUser;
    }

    public String getTanggalLahirUser() {
        return tanggalLahirUser;
    }

    public void setTanggalLahirUser(String tanggalLahirUser) {
        this.tanggalLahirUser = tanggalLahirUser;
    }

    public String getJenisKelaminUser() {
        return jenisKelaminUser;
    }

    public void setJenisKelaminUser(String jenisKelaminUser) {
        this.jenisKelaminUser = jenisKelaminUser;
    }

    public String getHpUser() {
        return hpUser;
    }

    public void setHpUser(String hpUser) {
        this.hpUser = hpUser;
    }

    public String getAgamaUser() {
        return agamaUser;
    }

    public void setAgamaUser(String agamaUser) {
        this.agamaUser = agamaUser;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmailUserr() {
        return emailUserr;
    }

    public void setEmailUserr(String emailUserr) {
        this.emailUserr = emailUserr;
    }

    public String getPassUserr() {
        return passUserr;
    }

    public void setPassUserr(String passUserr) {
        this.passUserr = passUserr;
    }

    @NonNull
    @Override
    public String toString() {
        return " "+namaUser+"\n" +
                " "+emailUserr+"\n" +
                " "+passUserr+"\n" +
                " "+alamatUser +"\n" +
                " "+tempatLahirUser +"\n" +
                " "+tanggalLahirUser +"\n" +
                " "+jenisKelaminUser +"\n" +
                " "+hpUser +"\n" +
                " "+agamaUser;
    }

}
