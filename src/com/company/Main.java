package com.company;

import com.company.Model.House;
import com.company.Model.Owner;
import com.company.Model.Stuff;
import com.company.Model.Thief;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        House house = new House();
        Owner owner = new Owner(house);
        Owner owner3 = new Owner(house);
        Owner owner4 = new Owner(house);
        Owner owner2 = new Owner(house);
        Thief thief = new Thief(house);
        Thief thief2 = new Thief(house);
        Thief thief3 = new Thief(house);
        Thief thief4 = new Thief(house);

        new Thread(owner).start();
        new Thread(owner3).start();
        new Thread(owner4).start();
        new Thread(owner2).start();
        new Thread(thief).start();
        new Thread(thief2).start();
        new Thread(thief3).start();
        new Thread(thief4).start();
    }

    public List<Stuff> getOptima(List<Stuff> stuffs,int weight){
        List<Stuff> stealedStuffAll = new ArrayList<>(stuffs);

        // отсортировали по весу
        Collections.sort(stealedStuffAll, new Comparator<Stuff>() {
            public int compare(Stuff o1, Stuff o2) {
                return o2.getWeight() - o1.getWeight();
            }
        });

        int maxSumAll = 0;
        List<Stuff> optimalStuff = new ArrayList<>();

        for(int i = 0;i<stealedStuffAll.size();i++){
            List<Stuff> stealedStuffLevel = levelSearch();
            int levelSum = 0;
            for (Stuff stuff: stealedStuffLevel) {
                levelSum += stuff.getPrice();
            }
            if (levelSum>maxSumAll){
                optimalStuff.clear();
                optimalStuff.add((Stuff) stealedStuffLevel);
            }
        }
        return optimalStuff;
    }

    public List<Stuff> levelSearch(List<Stuff> stealedStuffAll,int index,int maxWeight){
        List<Stuff> levelSearchOptimal = new ArrayList<>();
        int maxSum = 0;

        List<Stuff> currLevelSearch = new ArrayList<>();
        int curSum = 0;

        for (int i = index; i<stealedStuffAll.size(); i++){
            currLevelSearch = branchLevelStuff(stealedStuffAll,i);
            for (Stuff stuff: currLevelSearch) {
                curSum += stuff.getPrice();
            }
            if(curSum>maxSum) {
                levelSearchOptimal.clear();
                levelSearchOptimal.addAll(currLevelSearch);
            }
        }
        return levelSearchOptimal;
    }
    public List<Stuff> branchLevelStuff(List<Stuff> stealedStuffAll,int index,int maxWeight){
        List<Stuff> branchLevelSearch = new ArrayList<>();
        int maxSum = 0;

        List<Stuff> curBranchLevelSearch = new ArrayList<>();
        int curWeight = 0;
        int curSum = 0;

        int lastPoint = index;

        for(int i = index; i<stealedStuffAll.size();i++){
            if(maxWeight > curWeight+stealedStuffAll.get(i).getWeight()) {
                curBranchLevelSearch.add(stealedStuffAll.get(i));
                curWeight += stealedStuffAll.get(i).getWeight();
                curSum += stealedStuffAll.get(i).getPrice();
            }else{
                i++;
            }
        }
    }

}
