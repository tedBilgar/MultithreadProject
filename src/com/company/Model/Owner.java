package com.company.Model;

import java.util.ArrayList;
import java.util.List;

public class Owner implements Runnable{
    private List<Stuff> stuffs = new ArrayList<>();
    private House house;

    public Owner(House house) {
        this.house = house;
        for(int i =0;i<10;i++){
            stuffs.add(new Stuff((int)(Math.random()*(100-10+1)+10),(int) (Math.random()*(100-10+1))+10));
        }
    }

    public Owner() {
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
    public synchronized void deployStuffs(){
        while(!house.isIs_free()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        house.setIs_free(false);

        for (Stuff stuff: stuffs) {
            house.addStuff(stuff);
            System.out.println(stuff);
        }

        house.setIs_free(true);
        notifyAll();
    }

    @Override
    public void run() {
        this.deployStuffs();
    }
}
