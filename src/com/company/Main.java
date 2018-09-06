package com.company;


import com.company.building.Director;
import com.company.models.Carrying;

/*
 The 1st arg is for a number of owners, the 2nd is for thiefs ones.
 The 3rd arg needs for limit weight of backpack of thief. If there is no 3rd args then program uses value 100.
 */
public class Main {

    public static void main(String[] args) {
        Director director = new Director();

        int ownerNum = Integer.parseInt(args[0]);
        int thiefNum = Integer.parseInt(args[1]);

        while(true){
            if( ((int) (Math.random() * 1)) == 0 && (ownerNum != 0) ){
                Carrying owner = (Carrying) director.buildOwner();

                owner.toPack(director.buildStuff());

                new Thread((Runnable) owner).start();
                ownerNum--;
            }else if (thiefNum != 0){
                new Thread(director.buildThief()).start();
                thiefNum--;
            }
            if(thiefNum == 0 && ownerNum == 0) break;
        }

    }

}
