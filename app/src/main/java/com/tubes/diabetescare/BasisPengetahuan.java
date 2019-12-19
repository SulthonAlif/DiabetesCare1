package com.tubes.diabetescare;

import java.io.Serializable;

public class BasisPengetahuan implements Serializable {
    private String riwayatOrtu;
    private String riwayatGDR;
    private String riwayatHiper;
    private String polaMakan;
    private String aktFisik;
    private String intenOrg;
    private String imt;
    private String usia;
    private String risiko;

    public BasisPengetahuan(){

    }

    public BasisPengetahuan(String riwayatOrtu,String riwayatGDR,String riwayatHiper,
                            String polaMakan,String aktFisik,String intenOrg,
                            String imt,String usia){
        this.riwayatOrtu = riwayatOrtu;
        this.riwayatGDR = riwayatGDR;
        this.riwayatHiper = riwayatHiper;
        this.polaMakan = polaMakan;
        this.aktFisik = aktFisik;
        this.intenOrg = intenOrg;
        this.imt = imt;
        this.usia = usia;
    }

    public String getRiwayatOrtu() {
        return riwayatOrtu;
    }

    public void setRiwayatOrtu(String riwayatOrtu) {
        this.riwayatOrtu = riwayatOrtu;
    }

    public String getRiwayatGDR() {
        return riwayatGDR;
    }

    public void setRiwayatGDR(String riwayatGDR) {
        this.riwayatGDR = riwayatGDR;
    }

    public String getRiwayatHiper() {
        return riwayatHiper;
    }

    public void setRiwayatHiper(String riwayatHiper) {
        this.riwayatHiper = riwayatHiper;
    }

    public String getPolaMakan() {
        return polaMakan;
    }

    public void setPolaMakan(String polaMakan) {
        this.polaMakan = polaMakan;
    }

    public String getAktFisik() {
        return aktFisik;
    }

    public void setAktFisik(String aktFisik) {
        this.aktFisik = aktFisik;
    }

    public String getIntenOrg() {
        return intenOrg;
    }

    public void setIntenOrg(String intenOrg) {
        this.intenOrg = intenOrg;
    }

    public String getImt() {
        return imt;
    }

    public void setImt(String imt) {
        this.imt = imt;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getRisiko() {
        return risiko;
    }

    public void setRisiko(String risiko) {
        this.risiko = risiko;
    }
}
