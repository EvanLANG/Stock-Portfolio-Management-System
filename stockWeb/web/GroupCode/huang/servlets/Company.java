package huang.servlets;

public class Company {
    private String symbol;
    private float open;
    private float high;
    private float low;
    private float close;
    private long volume;
    private String url;

    public float getHigh() {
        return high;
    }
    public void setHigh(float value) {
        high = value;
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
}
