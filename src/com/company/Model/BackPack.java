package com.company.Model;

import java.util.ArrayList;
import java.util.List;

public class BackPack {
    private List<Stuff> stuffs;
    private int limit_weight;
    private int current_weight;

    public BackPack() {
        stuffs = new ArrayList<>();
    }

    public BackPack(int limit_weight) {
        stuffs = new ArrayList<>();
        this.limit_weight = limit_weight;
    }

    public int getLimit_weight() {
        return limit_weight;
    }

    public void setLimit_weight(int limit_weight) {
        this.limit_weight = limit_weight;
    }

    public boolean setStuff(Stuff stuff){
        if(current_weight+stuff.getWeight()<= limit_weight) {
            stuffs.add(stuff);
            current_weight+=stuff.getWeight();
            return true;
        }else{
            return false;
        }
    }
}
