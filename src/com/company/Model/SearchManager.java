package com.company.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchManager {
    public List<Stuff> getOptima(List<Stuff> stuffs, int maxWeight){
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
            List<Stuff> stealedStuffLevel = levelSearch(stealedStuffAll,i,maxWeight);
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
        List<Stuff> bufferList = new ArrayList<>();

        int curWeight = 0;
        int curSum = 0;
        int lastPoint = index+1;

        while(lastPoint != -1){
            curSum = 0;
            curWeight = 0;

            lastPoint = branchSearch(stealedStuffAll,bufferList,index,lastPoint,maxWeight);

            currLevelSearch.clear();
            currLevelSearch.addAll(bufferList);

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
    public int branchSearch(List<Stuff> stealedStuffAll,List<Stuff> bufferList,int index,int index2,int maxWeight){
        int curWeight = 0;
        int curSum = 0;
        int lastPoint = index2;
        bufferList.clear();

        bufferList.add(stealedStuffAll.get(index));
        curWeight += stealedStuffAll.get(index).getWeight();
        curSum += stealedStuffAll.get(index).getPrice();

        for(int i = index2; i<stealedStuffAll.size();i++){
            if(maxWeight >= curWeight+stealedStuffAll.get(i).getWeight()) {
                bufferList.add(stealedStuffAll.get(i));
                curWeight += stealedStuffAll.get(i).getWeight();
                curSum += stealedStuffAll.get(i).getPrice();
                lastPoint = i+1;
            }else{
                i++;
            }
        }

        if (lastPoint>=stealedStuffAll.size()){
            lastPoint = -1;
        }
        return lastPoint;
    }
}
