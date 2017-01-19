package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Story story=new Story(6);
        story.events[0]=new MyEvent() {
            @Override
            public int listen(int x) {
                return 1;
            }
        };
        story.events[0].text="В конце каждого действия вы делаете выбор. Нажмите ноль, чтобы проверить свое состояние." +
                "1)Продолить";
        story.events[1]=new MyEvent() {
            @Override
            public int listen(int x) {
                if(x<1||x>3)x=new Scanner(System.in).nextInt();
                switch (x){
                    case 1:{
                        if(story.player.inv[0]){
                            System.out.println("Дверь со скрипом отворилась.");
                            return -1;
                        }
                        else System.out.println("Заперто");
                        break;
                    }
                    case 2:return 2;
                    case 3:return 3;
                }
                return 1;
            }
        };
        story.events[1].text="Вы находитесь в каком-то помещении, и вам страстно хочется из него выбраться." +
                "Перед вами - расходящийся в две стороны коридор, за вашей спиной - дверь." +
                "1)Попробовать открыть дверь" +
                "2)Пойти направо по коридору" +
                "3)Пойти налево по коридору";
        story.events[2]=new MyEvent() {
            @Override
            public int listen(int x) {
                if(x<1||x>4)x=new Scanner(System.in).nextInt();
                if(this.f){
                    this.f=false;
                    if(x==3){
                        System.out.println("-Спасибо, мистер Дудец!" +
                                "Вам показалось, или ваши кости стали крепче? (+1 hp)");
                        this.chH(story.player,1);
                        return 2;
                    }
                    if(x==4){
                        System.out.println("Ваш лоб со звоном ударяется об пол.");
                        return 2;
                    }
                }
                if(!f&&x==3||x==4){
                    System.out.println("Ничего не произошло. На вас находит щемящее чувсво упущенного момента.");
                    return 2;
                }
                if(x==1)return 4;
                return 1;
            }
        };
        story.events[2].text="На стене висит изображение черепа, который дует в трубу." +
                "1)Идти в конец коридора" +
                "2)Возвращаться к дверям"+
                "3)Поблагодарить череп" +
                "4)Пасть ниц";
        story.events[4]=new MyEvent() {
            @Override
            public int listen(int x) {
                while (!(x==1||x==2))x=new Scanner(System.in).nextInt();
                if(x==1){
                    if(this.f){
                        this.f=false;
                        System.out.println("В темноте вы подскользнулись на лестнице и скатились куборем вниз.(-30 hp)");
                        this.chH(story.player,-30);
                    }
                    return 5;
                }
                return 1;
            }
        };
        story.events[4].text="Впереди - тускло освещенная лестница" +
                "1)Спускаться" +
                "2)Вернуться к дверям";
        story.events[5]=new MyEvent() {
            @Override
            public int listen(int x) {
                while (!(x==1||x==2||x==3))x=new Scanner(System.in).nextInt();
                switch (x){
                    case 1:{
                        if(!story.player.inv[1])this.chInv(story.player,1);
                        else System.out.println("Больше пирожков вам не влезет.");
                        return 5;
                    }
                    case 2:{
                        if(Math.random()<0.5){
                            System.out.println("Пирожки укрепили ваш дух. (+1 hp)");
                            this.chH(story.player,1);
                        }
                        else {
                            System.out.println("Вы отравились пирожками. (-20 hp)");
                            this.chH(story.player,-20);
                        }
                        return 5;
                    }
                }
                return 4;
            }
        };
        story.events[5].text="Внизу лежит груда пирожков." +
                "1)Взять пирожок с собой" +
                "2)Съесть пирожок" +
                "3)Влезть по лестнице вверх";
        story.events[3]=new MyEvent() {
            @Override
            public int listen(int x) {
                if(f){while (!(x==1||x==2||x==3))x=new Scanner(System.in).nextInt();}
                else {while (x!=1)x=new Scanner(System.in).nextInt();}
                switch (x){
                    case 2:{
                        int hp=30;
                        while (!(hp<=0||story.player.h<=0)){
                            System.out.println("вы - "+story.player.h+" чел - "+hp);
                            System.out.println("И вновь продолжается бой.");
                            hp-=(int)(Math.random()*10);
                            this.chH(story.player,-(int)(Math.random()*15));
                        }
                        System.out.println("вы - "+story.player.h+" чел - "+hp);
                        if(story.player.h<=0) System.out.println("Их было слишком много");
                        else System.out.println("Это было даже слишком легко.");
                        this.chInv(story.player,0);
                        story.events[3].text="Чел лежит с пробитой головой. По какой-то причине смотреть на это неприятно." +
                                "1)Вернуться к дверям.";
                        this.f=false;
                        return 3;
                    }
                    case 3:{
                        if(story.player.inv[1]){
                            System.out.println("Вы отдаете пирожок и получаете ключи.");
                            this.chInv(story.player,0);
                            this.chInv(story.player,1);
                            story.events[3].text="Чел со вкусом ест пирожок" +
                                    "1)Вернуться к дверям";
                        }
                        else System.out.println("Вы никак не можете найти пирожок. Потерялся он что ли?");
                        this.f=false;
                        return 3;
                    }
                }
                return 1;
            }
        };
        story.events[3].text="Вы входите в комнату, в которой стоит какой-то чел и вертит связку ключей." +
                "-Парень, одолжи ключи ненадолго." +
                "-Мне бы поесть чего. Принесешь мне пирожок, дам ключи, а иначе проваливай." +
                "1)Вернуться к дверям" +
                "2)Вызвать чела на дуэль" +
                "3)Искать в карманах пирожок";
        while (story.endStory())story.play();
    }
}
