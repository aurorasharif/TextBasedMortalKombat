package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playgame = true;
        int playerHealth = 100;
        int healthPotion = 3;
        int attackPower = 40;

        String[] enemies = {"Scorpion", "Kitana", "Goro", "SubZero", "kano", "Ermac", "Kabal", "Kotal Kahn"};

        GAME:
        while (playgame) {
            System.out.println("Welcome to the game of MortalKombat.");
            String opponent = enemies[random.nextInt(enemies.length)];
            System.out.println(opponent + " 1appeared");
            Enemy enemy = new Enemy(opponent, 100, random.nextInt(40));

            boolean inGame = true;

            while (inGame) {
                System.out.println("select what to do: \n" +
                        "\t1: Attack.!" +
                        "\t2: Drink Health" +
                        "\t3: Run!");

                int pressed = scanner.nextInt();

                switch (pressed) {

                    case 1:
                        if (playerHealth>0 && (enemy.getHealth()>0)) {
                            System.out.println("Enemy attacked!");
                            int playerDamage = random.nextInt(enemy.getDamage());
                            if (playerHealth-playerDamage<=0){
                                playerHealth=0;
                                System.out.println("you died!");
                                inGame=false;
                            }else{
                                playerHealth -= playerDamage;
                            }
                            int enemyDamage = random.nextInt(attackPower);
                            if (enemy.getHealth()-enemyDamage<=0){
                                enemy.setHealth(0);
                                System.out.println("enemy dead!");
                                inGame=false;
                            }else {
                                enemy.damageTaken(enemyDamage);
                            }

                            System.out.println("inflicted damage upon enemy: " + enemyDamage);
                            System.out.println("enemy health left: " + enemy.getHealth());
                            System.out.println("received " + playerDamage + "damage \n" +
                                    "Health left: " + playerHealth);
                        }
                        break;

                    case 2:
                        if (healthPotion > 0 && (playerHealth + 30 <= 100)) {
                            playerHealth += 30;
                            System.out.println("potion taken. health is now: " + playerHealth);
                            healthPotion--;
                        } else {
                            System.out.println("Can't now");
                        }
                        break;

                    case 3:
                        System.out.println("Ran like a coward!");
                        inGame = false;
                        break;
                } playgame=false;
            }
        }
    }
}

