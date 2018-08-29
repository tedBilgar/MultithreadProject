package com.company.Model;

import com.company.lifelessModel.Stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class House {
    private List<Stuff> home_stuffs;
    private boolean is_free = true;
    private AtomicInteger ownerCounter;
    private AtomicBoolean is_owner;
    private AtomicBoolean is_thief;

    public House() {
        home_stuffs = Collections.synchronizedList(new ArrayList<>());
        ownerCounter = new AtomicInteger();
        ownerCounter.set(0);
        is_owner = new AtomicBoolean(false);
        is_thief = new AtomicBoolean(false);
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

    public boolean Is_free() {
        return is_free;
    }

    public void setIs_free(boolean is_free) {
        this.is_free = is_free;
    }



    public AtomicInteger getOwnerCounter() {
        return ownerCounter;
    }

    public void setOwnerCounter(AtomicInteger atomicInteger) {
        this.ownerCounter = atomicInteger;
    }

    public AtomicBoolean getIs_owner() {
        return is_owner;
    }

    public void setIs_owner(AtomicBoolean atomicBoolean) {
        this.is_owner = atomicBoolean;
    }

    public AtomicBoolean getIs_thief() {
        return is_thief;
    }

    public void setIs_thief(AtomicBoolean is_thief2) {
        this.is_thief = is_thief2;
    }
}
