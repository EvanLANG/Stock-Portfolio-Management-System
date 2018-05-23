package Chen.Class;

public class RankObject {
    private String sym;
    private String name;
    private String sector;
    private String industry;
    private float value;
    private double sim;
    private int followNum;
    private float riseAndFall;
    private String url;
    public RankObject(){;}
    public String getSym() {
        return sym;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public int getFollowNum() {
        return followNum;
    }

    public float getRiseAndFall() {
        return riseAndFall;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public void setRiseAndFall(float riseAndFall) {
        this.riseAndFall = riseAndFall;
    }

    public void setSim(double sim) {
        this.sim = sim;
    }

    public double getSim() {
        return sim;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String value) {
        url = value;
    }

    public String getIndustry() { return industry; }
    public void setIndustry(String value) {
        industry = value;
    }

    public String getSector() { return sector; }
    public void setSector(String value) {
        sector = value;
    }
}
