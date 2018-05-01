package Chen.Class;

public class RankObject {
    private String sym;
    private float value;
    private int followNum;
    private float riseAndFall;
    public RankObject(){;}
    public String getSym() {
        return sym;
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

    public void setValue(float value) {
        this.value = value;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public void setRiseAndFall(float riseAndFall) {
        this.riseAndFall = riseAndFall;
    }
}
