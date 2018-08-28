package com.company.Model;

import java.util.ArrayList;
import java.util.List;

public class Owner implements Runnable{
    private List<Stuff> stuffs = new ArrayList<>();
    private House house;

    public Owner() {
    }

    public Owner(House house) {
        this.house = house;
        for(int i =0;i<10;i++){
            stuffs.add(new Stuff((int)(Math.random()*(100-10+1)+10),(int) (Math.random()*(100-10+1))+10));
        }
    }


    public Owner(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }

    //Внести вещи в квартиру
    public void deployStuffs(){
        String name = Thread.currentThread().getName();
        try {
            //synchronized (house){
                while(!house.isIs_free()&&!house.isIs_owner()) {
                    house.wait();
                }
                house.setIs_free(false);
                house.setIs_owner(true);
                for (Stuff stuff: stuffs) {
                    house.addStuff(stuff);
                    System.out.println(name + " : " + stuff);
                }
                house.setIs_free(true);
                house.setIs_owner(false);
                synchronized (house) {
                    house.notify();
                }
            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " стартовал хозяин");
        this.deployStuffs();
    }
}
