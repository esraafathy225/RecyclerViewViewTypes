package com.esraa.hp.recyclerviewviewtypes;


public class Model {


    public static final int TEXT_TYPE=0;
    public static final int IMAGE_TYPE=1;
    public static final int AUDIO_TYPE=2;

    private int type;
    private int data;
    private String text;



    public Model(int type, String text, int data) {
        this.type=type;
        this.data=data;
        this.text=text;

    }

    public int getData() {
        return data;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
