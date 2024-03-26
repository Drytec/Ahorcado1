package org.example.crapsgame.controller;

import java.util.Random;

public class Dice {

    public int throwDice(){
        Random random = new Random();
        int diceSides = 6;
        int randomSide = random.nextInt(diceSides);
        return randomSide;
    }}



