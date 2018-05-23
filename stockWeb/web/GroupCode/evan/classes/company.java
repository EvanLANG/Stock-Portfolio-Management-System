package evan.classes;

import java.sql.Timestamp;

public class company {
    private String id;
    private String comname;
    private Timestamp timestamp;
    private int open;
    private int high;
    private int low;
    private int close;
    private int volume;
    public company(){
        ;
    }
    public company(String i, Timestamp t, int o, int h, int l, int c, int v){
        id = i;
        timestamp = t;
        open = o;
        high = h;
        low = l;
        close = c;
        volume = v;
    }
    public void setId(String i){
        id = i;
    }
    public String getId(){ return id; }
    public void setTimestamp(Timestamp t){
        timestamp = t;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
    public void setOpen(int o){
        open = o;
    }
    public int getOpen(){
        return open;
    }
    public void setHigh(int h){
        high = h;
    }
    public int getHigh(){
        return high;
    }
    public void setLow(int l){
        low = l;
    }
    public int getLow(){
        return low;
    }
    public void setClose(int c){
        close = c;
    }
    public int getClose(){
        return close;
    }
    public void setVolume(int v){
        volume = v;
    }
    public int getVolume(){
        return volume;
    }
}
