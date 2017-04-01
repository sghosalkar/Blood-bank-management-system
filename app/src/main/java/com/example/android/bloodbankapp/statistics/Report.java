package com.example.android.bloodbankapp.statistics;

/**
 * Created by DELL on 19-Mar-17.
 */

public class Report {
    private int bldin;
    private int bldout;
    private String month;
    private String bldqtyin;
    private String bldqtyout;

    public Report( String month,int bldin, String bldqtyin, int bldout, String bldqtyout) {
       this.setBldin(bldin);
        this.setBldout(bldout);
        this.setMonth(month);
       this.setBldqtyin(bldqtyin);
        this.setBldqtyout(bldqtyout);

    }

    public int getBldin() {
        return bldin;
    }

    public void setBldin(int bldin) {
        this.bldin = bldin;
    }

    public int getBldout() {
        return bldout;
    }

    public void setBldout(int bldout) {
        this.bldout = bldout;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getBldqtyin() {
        return bldqtyin;
    }

    public void setBldqtyin(String bldqtyin) {
        this.bldqtyin = bldqtyin;
    }

    public String getBldqtyout() {
        return bldqtyout;
    }

    public void setBldqtyout(String bldqtyout) {
        this.bldqtyout = bldqtyout;
    }
}
