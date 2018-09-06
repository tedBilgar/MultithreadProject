package com.company.models;

import com.company.Model.House;
import com.company.Model.SearchManager;
import com.company.building.ThiefBuilder;
import com.company.lifelessModel.BackPack;
import com.company.lifelessModel.Stuff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Thief implements Runnable,Carrying{
    private BackPack backPack;
    private House house;
    private SearchManager searchManager;

    public Thief(ThiefBuilder thiefBuilder){
        this.house = thiefBuilder.getHouse();
        this.backPack = thiefBuilder.getBackPack();
        this.searchManager = thiefBuilder.getSearchManager();
    }

    //Воровать может только один вор
    public synchronized void steal(){
        String name = Thread.currentThread().getName();

        /*
            потокозащищенные флаги на наличие хозев , другого вора или пустого дома
         */
        while(house.getIs_owner().get()|| house.getIs_thief().get() || house.getHome_stuffs().isEmpty()){
            try {
                synchronized (house) {
                    house.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
            Ставим флаг с дома на вора
         */
        house.getIs_thief().set(true);
        System.out.println("Пришел вор");
        List<Stuff> stuffsForStealing = Collections.synchronizedList(new ArrayList<>(house.getHome_stuffs()));

        Collections.sort(stuffsForStealing, new Comparator<Stuff>() {
            public int compare(Stuff o1, Stuff o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });

        /*
            getOptima функция нахождения самого оптимального списка с определенным весом рюкзака, заданным в параметрах
         */
        for (Stuff stuff: searchManager.getOptima(stuffsForStealing,backPack.getLimit_weight())) {
            if(!this.toPack(stuff)) break;
            house.getHome_stuffs().remove(stuff);
            System.out.println(name + " is stealing : " + stuff);
        }

        /*
            Снятие флага вора и открытие входа для хозяев
         */
        house.getIs_thief().set(false);
        System.out.println("Ушел вор");
        synchronized (house){
            house.notify();
        }
    }

    @Override
    public boolean toPack(Stuff stuff) {
         return backPack.setStuff(stuff);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал вор");
        this.steal();
        /*while(true) {
            this.steal();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
    }

}
