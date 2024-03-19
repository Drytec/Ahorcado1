package org.example.crapsgame.model.player;

public class Player {
    private int id;
    private String nickname;
    public Player(int id,String nickname){
        this.id=id;
        this.nickname=nickname;
    }

    public String getNickname() {
        return nickname;
    }
    public int getId (){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
