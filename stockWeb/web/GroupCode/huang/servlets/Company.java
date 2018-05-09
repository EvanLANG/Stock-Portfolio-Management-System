package huang.servlets;

import java.text.DecimalFormat;

public class Company {
    private String symbol;
    private String comname;
    private float current;//现价
    private float open;//今日开盘
    private float high;//今日开盘到目前为止最高
    private float low;//到目前为止最低
    private float close;//昨日闭盘
    private long volume;//今日开盘到目前累积的交易量
    private String change;//今日增长（减少）量
    private String change_percent;//今日增长（减少）百分比
    private boolean sig;//今日增长（减少）标志
    private String url;
    private int followed;
    private int rank_index;
    private int rank_follows;
    private float rank_RF;
    private float rank_value;
    public void setComname(String i){ comname = i;}
    public String getComname(){ return comname; }
    public float getHigh() {
        return high;
    }
    public void setHigh(float value) {
        high = value;
    }

    public float getCurrent() {
        return current;
    }
    public void setCurrent(float value) {
        current = value;
    }

    public float getLow() {
        return low;
    }
    public void setLow(float value) {
        low = value;
    }

    public float getOpen() {
        return open;
    }
    public void setOpen(float value) {
        open = value;
    }

    public float getClose() {
        return close;
    }
    public void setClose(float value) {
        close = value;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String value) {
        symbol = value;
    }
    public long getVolume() {
        return volume;
    }
    public void setVolume(long value) {
        volume = value;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String value) {
        url = value;
    }
    public String getChange() {
        return change;
    }
    public void setChange(float value) {
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        change = decimalFormat.format(value);
    }
    public String getChange_percent() {
        return change_percent;
    }
    public void setChange_percent(float value) {
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        change_percent = decimalFormat.format(value);
    }
    public boolean getSig() {
        return sig;
    }
    public void setSig(boolean value) {
        sig = value;
    }

    public int getFollowed() {
        return followed;
    }
    public void setFollowed(int value) { followed = value; }

    public int getRank_index() {
        return rank_index;
    }
    public void setRank_index(int value) {
        rank_index = value;
    }

    public int getRank_follows() {
        return rank_follows;
    }
    public void setRank_follows(int value) {
        rank_follows = value;
    }

    public float getRank_RF() {
        return rank_RF;
    }
    public void setRank_RF(float value) {
        rank_RF = value;
    }

    public float getRank_value() {
        return rank_value;
    }
    public void setRank_value(float value) {
        rank_value = value;
    }
}
