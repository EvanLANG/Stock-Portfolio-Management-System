package Chen.Comparator;

import Chen.Class.RankObject;

import java.util.Comparator;

public class RiseNFallComparator  implements Comparator {
    public int compare(Object object1,Object object2 ){
        RankObject a = (RankObject) object1;
        RankObject b = (RankObject) object2;
        float value1 = a.getRiseAndFall();
        float value2 = b.getRiseAndFall();
        if(value2>value1){
            return 1;
        }else if(value2<value1){
            return -1;
        }else{
            return 0;
        }
    }
}
