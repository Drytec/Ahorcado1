package org.example.ahorcado.model.player;

public class Player {
    private int id;
    private String nickname;
    private String word;
    public Player(int id, String nickname){
        this.id=id;
        this.nickname=nickname;
    }

    public String getNickname(){
        return this.nickname;
    }


}
