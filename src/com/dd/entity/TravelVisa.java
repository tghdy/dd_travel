package com.dd.entity;

public class TravelVisa {
    private int id;
    private String deal_place;
    private String validity_period;
    private String most_stay;
    private String deal_time;
    private String interview;
    private String entry;
    private String custom_range;
    private String custom_inf1;
    private String custom_inf2;
    private String custom_inf3;
    private String custom_inf4;
    private String custom_inf5;
    private float price;
    private String visa_title;
    private String matters;
    private String description_fees;
    private String warning;
    private String visa_pic;

    public TravelVisa(String deal_place, String validity_period, String most_stay,
                      String deal_time, String interview, String entry, String custom_range,
                      String custom_inf1, String custom_inf2, String custom_inf3, String custom_inf4,
                      String custom_inf5, float price, String visa_title, String matters,
                      String description_fees, String warning, String visa_pic) {
        this.deal_place = deal_place;
        this.validity_period = validity_period;
        this.most_stay = most_stay;
        this.deal_time = deal_time;
        this.interview = interview;
        this.entry = entry;
        this.custom_range = custom_range;
        this.custom_inf1 = custom_inf1;
        this.custom_inf2 = custom_inf2;
        this.custom_inf3 = custom_inf3;
        this.custom_inf4 = custom_inf4;
        this.custom_inf5 = custom_inf5;
        this.price = price;
        this.visa_title = visa_title;
        this.matters = matters;
        this.description_fees = description_fees;
        this.warning = warning;
        this.visa_pic = visa_pic;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setDeal_place(String deal_place) {
        this.deal_place = deal_place;
    }

    public void setValidity_period(String validity_period) {
        this.validity_period = validity_period;
    }

    public void setMost_stay(String most_stay) {
        this.most_stay = most_stay;
    }

    public void setDeal_time(String deal_time) {
        this.deal_time = deal_time;
    }

    public void setInterview(String interview) {
        this.interview = interview;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setCustom_range(String custom_range) {
        this.custom_range = custom_range;
    }

    public void setCustom_inf1(String custom_inf1) {
        this.custom_inf1 = custom_inf1;
    }

    public void setCustom_inf2(String custom_inf2) {
        this.custom_inf2 = custom_inf2;
    }

    public void setCustom_inf3(String custom_inf3) {
        this.custom_inf3 = custom_inf3;
    }

    public void setCustom_inf4(String custom_inf4) {
        this.custom_inf4 = custom_inf4;
    }

    public void setCustom_inf5(String custom_inf5) {
        this.custom_inf5 = custom_inf5;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setVisa_title(String visa_title) {
        this.visa_title = visa_title;
    }

    public void setMatters(String matters) {
        this.matters = matters;
    }

    public void setDescription_fees(String description_fees) {
        this.description_fees = description_fees;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public void setVisa_pic(String visa_pic) {
        this.visa_pic = visa_pic;
    }

    public int getId() { return id; }

    public TravelVisa() {
    }

    public TravelVisa(int id, String deal_place, String validity_period,
                      String most_stay, String deal_time, String interview, String entry,
                      String custom_range, float price, String visa_title, String matters,
                      String description_fees, String warning, String visa_pic,
                      String custom_inf1,String custom_inf2,String custom_inf3,String custom_inf4,String custom_inf5) {

        this.id = id;
        this.deal_place = deal_place;
        this.validity_period = validity_period;
        this.most_stay = most_stay;
        this.deal_time = deal_time;
        this.interview = interview;
        this.entry = entry;
        this.custom_range = custom_range;
        this.price = price;
        this.visa_title = visa_title;
        this.matters = matters;
        this.description_fees = description_fees;
        this.warning = warning;
        this.visa_pic = visa_pic;
        this.custom_inf1=custom_inf1;
        this.custom_inf2=custom_inf2;
        this.custom_inf3=custom_inf3;
        this.custom_inf4=custom_inf4;
        this.custom_inf5=custom_inf5;
    }

    public String getDeal_place() {
        return deal_place;
    }

    public String getValidity_period() {
        return validity_period;
    }

    public String getMost_stay() {
        return most_stay;
    }

    public String getDeal_time() {
        return deal_time;
    }

    public String getInterview() {
        return interview;
    }

    public String getEntry() {
        return entry;
    }

    public String getCustom_range() {
        return custom_range;
    }

    public String getCustom_inf1() {
        return custom_inf1;
    }

    public String getCustom_inf2() {
        return custom_inf2;
    }

    public String getCustom_inf3() {
        return custom_inf3;
    }

    public String getCustom_inf4() {
        return custom_inf4;
    }

    public String getCustom_inf5() {
        return custom_inf5;
    }

    public float getPrice() {
        return price;
    }

    public String getVisa_title() {
        return visa_title;
    }

    public String getMatters() {
        return matters;
    }

    public String getDescription_fees() {
        return description_fees;
    }

    public String getWarning() {
        return warning;
    }

    public String getVisa_pic() {
        return visa_pic;
    }


}
