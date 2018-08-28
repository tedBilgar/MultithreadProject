package com.company.Model;

import java.util.ArrayList;
import java.util.List;

public class House {
    private List<Stuff> home_stuffs;
    private boolean is_free = true;
    private boolean is_owner = false;

    public boolean isIs_owner() {
        return is_owner;
    }

    public void setIs_owner(boolean is_owner) {
        this.is_owner = is_owner;
    }

    public House() {
        home_stuffs = new ArrayList<>();
    }

    public List<Stuff> getHome_stuffs() {
        return home_stuffs;
    }

    public void setHome_stuffs(List<Stuff> home_stuffs) {
        this.home_stuffs = home_stuffs;
    }

    public  void addStuff(Stuff stuff){
        home_stuffs.add(stuff);
    }

    public boolean isIs_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }
}