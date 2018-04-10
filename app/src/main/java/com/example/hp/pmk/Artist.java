package com.example.hp.pmk;

/**
 * Created by hp on 3/22/2018.
 */

class Artist {

    private String name;
    private String ward;
    private String address;
    private String gender;
    private String maritalstatus;
    private String occupation;
    private String mobile1;
    private String dob;
    private String district;
    private String subdistrict;
    private String memberid;
    private String pincode;
    private String primarylevel;
    private String primaryname;
    private String secondarylevel;
    private String secondaryname;
    private String team;
    private String constituency;

    public Artist(String name, String ward, String address, String gender, String maritalstatus, String occupation, String mobile1, String dob, String district, String subdistrict, String memberid, String pincode, String primarylevel, String primaryname, String secondarylevel, String secondaryname, String team, String constituency) {
        this.name = name;
        this.ward = ward;
        this.address = address;
        this.gender = gender;
        this.maritalstatus = maritalstatus;
        this.occupation = occupation;
        this.mobile1 = mobile1;
        this.dob = dob;
        this.district = district;
        this.subdistrict = subdistrict;
        this.memberid = memberid;
        this.pincode = pincode;
        this.primarylevel = primarylevel;
        this.primaryname = primaryname;
        this.secondarylevel = secondarylevel;
        this.secondaryname = secondaryname;
        this.team = team;
        this.constituency = constituency;
    }

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPrimarylevel() {
        return primarylevel;
    }

    public void setPrimarylevel(String primarylevel) {
        this.primarylevel = primarylevel;
    }

    public String getPrimaryname() {
        return primaryname;
    }

    public void setPrimaryname(String primaryname) {
        this.primaryname = primaryname;
    }

    public String getSecondarylevel() {
        return secondarylevel;
    }

    public void setSecondarylevel(String secondarylevel) {
        this.secondarylevel = secondarylevel;
    }

    public String getSecondaryname() {
        return secondaryname;
    }

    public void setSecondaryname(String secondaryname) {
        this.secondaryname = secondaryname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getConstituency() {
        return constituency;
    }

    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }
}