package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Thief implements Runnable{
    private BackPack backPack;
    private House house;
    private SearchManager searchManager;
    private int limitWeight;

    public Thief(House house) {
        this.house = house;
        limitWeight = 100;
        backPack = new BackPack(limitWeight);
        searchManager = new SearchManager();
    }
    public Thief(House house,int limitWeight) {
        this.house = house;
        this.limitWeight = limitWeight;
        limitWeight = 100;
        backPack = new BackPack(limitWeight);
        searchManager = new SearchManager();
    }

    //Воровать может только один вор
    public synchronized void steal(){
        String name = Thread.currentThread().getName();

        while(house.getIs_owner().get()|| house.getIs_thief().get() || house.getHome_stuffs().isEmpty()){
            try {
                synchronized (house) {
                    house.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        house.getIs_thief().set(true);

        List<Stuff> stuffsForStealing = Collections.synchronizedList(new ArrayList<>(house.getHome_stuffs()));

        Collections.sort(stuffsForStealing, new Comparator<Stuff>() {
            public int compare(Stuff o1, Stuff o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });

        //Эмитация работы вора
        for (Stuff stuff: searchManager.getOptima(stuffsForStealing,limitWeight)) {
            if(!backPack.setStuff(stuff)) break;
            house.getHome_stuffs().remove(stuff);
            System.out.println(name + " is stealing : " + stuff);
        }

        house.getIs_thief().set(false);

        synchronized (house){
            house.notify();
        }
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал вор");
        this.steal();
    }
}
