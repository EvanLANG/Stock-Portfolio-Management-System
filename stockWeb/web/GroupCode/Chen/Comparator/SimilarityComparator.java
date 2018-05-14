package Chen.Comparator;

import Chen.Class.RankObject;

import java.util.Comparator;

public class SimilarityComparator  implements Comparator {
    public int compare(Object object1,Object object2 ){
        RankObject a = (RankObject) object1;
        RankObject b = (RankObject) object2;
        double value1 = a.getSim();
        double value2 = b.getSim();
        if(value2>value1){
            return 1;
        }else if(value2<value1){
            return -1;
        }else{
            return 0;
        }
    }
}

