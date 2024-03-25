package org.example.crapsgame.controller;

import org.example.crapsgame.model.player.Dices;

import java.util.Random;

public class Dice extends Dices {

    public int throwDice(){
        Random random = new Random();
        int diceSides = 6;
        int randomSide = random.nextInt(diceSides);
        return randomSide;
    }}



