package com.company;

/**
 * Created by Мария on 19.01.2017.
 */
public abstract class MyEvent {
    public String text;
    public boolean f=true;
    public abstract int listen(int x);
    public void chH(Player player,int x){
        player.h+=x;
    }
    public void chInv(Player player,int x){
        player.inv[x]=!player.inv[x];
    }

}
