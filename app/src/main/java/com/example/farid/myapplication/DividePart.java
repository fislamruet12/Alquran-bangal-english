package com.example.farid.myapplication;

/**
 * Created by Farid on 9/25/2018.
 */

public class DividePart {
    private String arabic;
    private String bangla;
    private String english;

    public DividePart(String arabic, String bangla, String english) {
        this.arabic = arabic;
        this.bangla = bangla;
        this.english = english;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public String getBangla() {
        return bangla;
    }

    public void setBangla(String bangla) {
        this.bangla = bangla;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }
}
