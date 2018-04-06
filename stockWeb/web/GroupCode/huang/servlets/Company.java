package huang.servlets;

class Company {
    private String Symbol;
    private float open;
    private float high;
    private float low;
    private float close;
    private long volume;

    Company(String s, float o, float c, float h, float l, long v){
        this.Symbol = s;
        this.open = o;
        this.high = h;
        this.low = l;
        this.close = c;
        this.volume = v;
    }
}
