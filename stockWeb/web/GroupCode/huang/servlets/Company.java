package huang.servlets;

import java.text.DecimalFormat;

public class Company {
    private String symbol;
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
}
