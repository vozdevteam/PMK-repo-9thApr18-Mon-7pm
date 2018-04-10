package com.example.hp.pmk;

/**
 * Created by hp on 07-Apr-18.
 */

public class Member {

    private String name;
    private String mobile1;
    private String primaryname;
    private String team;
    private String constituency;

    public Member(String name, String mobile1, String primaryname, String team, String constituency) {
        this.name = name;
        this.mobile1 = mobile1;
        this.primaryname = primaryname;
        this.team = team;
        this.constituency = constituency;
    }

    public Member() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile1() {
        return mobile1;
    }

    public void setMobile1(String mobile1) {
        this.mobile1 = mobile1;
    }

    public String getPrimaryname() {
        return primaryname;
    }

    public void setPrimaryname(String primaryname) {
        this.primaryname = primaryname;
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
