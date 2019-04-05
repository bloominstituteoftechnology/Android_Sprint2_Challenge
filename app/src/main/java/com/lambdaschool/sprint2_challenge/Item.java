package com.lambdaschool.sprint2_challenge;
import android.content.Intent;
import android.graphics.Picture;
import android.net.Uri;

import java.io.Serializable;

public class Item implements Serializable {
    private int iID;
    private String strName;
    private boolean bToShop;
    private int iIcon;

    public Item(int iID, String strName, boolean bToShop,int iIcon){
        this.iID=iID;
        this.strName=strName;
        this.bToShop=bToShop;
        this.iIcon=iIcon;
    }

    public Item(String strCSV){
        String[] strarCSV=strCSV.split( "," );

        this.iID= Integer.parseInt( strarCSV[0] );
        this.strName=strarCSV[1];
        this.bToShop=Boolean.getBoolean( strarCSV[2]);
        this.iIcon=Integer.parseInt( strarCSV[3]);
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public int getiID() {
        return iID;
    }

    public void setiID(int iID) {
        this.iID = iID;
    }

    public boolean isbToShop() {
        return bToShop;
    }

    public void setbToShop(boolean bToShop) {
        this.bToShop = bToShop;
    }

    public int getiIcon() {
        return this.iIcon;
    }

    public void setiIcon(int iIcon) {
        this.iIcon = iIcon;
    }

    public String toCSV() {
        return Integer.toString( iID) +",'" + strName+"," + bToShop + ", '" + Integer.toString(iIcon);
    }


}