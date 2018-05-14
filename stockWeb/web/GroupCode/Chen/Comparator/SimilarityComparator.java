package Chen.Comparator;

import java.util.Comparator;

public class SimilarityComparator  implements Comparator<Double> {
    public int compare(Double value1,Double value2 ){
        if(value2>value1){
            return 1;
        }else if(value2<value1){
            return -1;
        }else{
            return 0;
        }
    }
}

