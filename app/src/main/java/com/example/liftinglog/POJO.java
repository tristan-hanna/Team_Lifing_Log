package com.example.liftinglog;

public class POJO {
    long xvalue;
    int yvalue;

    public POJO(){
    }

    public POJO(long xvalue, int yvalue){
        this.xvalue = xvalue;
        this.yvalue = yvalue;
    }

    public long getXvalue(){
        return xvalue;
    }

    public int getYvalue(){
        return yvalue;
    }
}
