package com.company;

import java.util.Scanner;

/**
 * Created by Мария on 18.01.2017.
 */
public class Player {
    public int h;
    public boolean inv[];
    public Player(){
        this.h=30;
        this.inv=new boolean[2];
        for(int i=0;i<this.inv.length;i++){
            inv[i]=false;
        }
    }
    public int decide(){
        int x=new Scanner(System.in).nextInt();
        while (x==0){
            System.out.println("Здоровье"+this.h);
            System.out.println("Инвентарь:");
            for(int i=0;i<this.inv.length;i++){
                if(this.inv[i])Trans.translate(i);
                }
                x=new Scanner(System.in).nextInt();
            }
        return x;
    }
}
