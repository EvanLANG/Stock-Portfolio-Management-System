package evan.classes;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class readTXT {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("web\\resource\\companylist.txt"));
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> namelist = new ArrayList<String>();

        String line = null;
        while ((line = br.readLine()) != null) {
            array.add(line);
        }

        br.close();
        //System.out.println(array.get(0));
        for (String s : array) {
            String[] parts = s.split("#");
            //System.out.println(parts[0]);
            namelist.add(parts[0]);
            //System.out.println(parts[1]);
            //System.out.println(parts[2]);
            //System.out.println(parts[3]);
            //break;
        }
        for(String s : namelist){
            System.out.println(s);

        }
    }
}