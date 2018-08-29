package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class House {
    private List<Stuff> home_stuffs;
    private boolean is_free = true;
    private boolean is_thief;
    private int curOnwerCount;
    private AtomicInteger atomicInteger;
    private AtomicBoolean is_owner;
    private AtomicBoolean is_thief2;

    public House() {
        home_stuffs = Collections.synchronizedList(new ArrayList<>());
        curOnwerCount = 0;
        atomicInteger = new AtomicInteger();
        atomicInteger.set(0);
        is_owner = new AtomicBoolean(false);
        is_thief2 = new AtomicBoolean(false);
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

    public boolean Is_thief() {
        return is_thief;
    }

    public void setIs_thief(boolean is_thief) {
        this.is_thief = is_thief;
    }

    public int setAndGetOnwerCount (boolean is_up ) {
        if (is_up) curOnwerCount++;
        else curOnwerCount--;
        return curOnwerCount;
    }
    public int getCurOnwerCount(){
        return curOnwerCount;
    }

    public AtomicInteger getAtomicInteger() {
        return atomicInteger;
    }

    public void setAtomicInteger(AtomicInteger atomicInteger) {
        this.atomicInteger = atomicInteger;
    }

    public AtomicBoolean getAtomicBoolean() {
        return is_owner;
    }

    public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
        this.is_owner = atomicBoolean;
    }

    public AtomicBoolean getIs_thief2() {
        return is_thief2;
    }

    public void setIs_thief2(AtomicBoolean is_thief2) {
        this.is_thief2 = is_thief2;
    }
}
