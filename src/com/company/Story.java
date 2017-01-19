package com.company;

/**
 * Created by Мария on 19.01.2017.
 */
public class Story {
    private static int next;
    public static MyEvent[] events;
    public static Player player;
    public Story(int x){
        this.events=new MyEvent[x];
        this.player=new Player();
        this.next=0;
    }
    public static void play(){
        System.out.println(Story.events[next].text);
        Story.next=Story.events[next].listen(player.decide());
    }
    public static boolean endStory(){
        if(Story.player.h<=0) System.out.println("Вы погибли.");
        if(Story.next==-1) System.out.println("Вы прошли игру.");
        return !((Story.player.h<=0)||(Story.next==-1));
    }
}
