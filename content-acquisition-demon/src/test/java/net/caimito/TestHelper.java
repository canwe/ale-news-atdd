package net.caimito;

import java.io.*;

public class TestHelper {

    public static String readFile(String location) {
        try {
            InputStream in = new FileInputStream(new File(location));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            reader.close();
            return out.toString();
        } catch (Exception e) {
            System.out.println(String.format("CWD = %s", System.getProperty("user.dir"))) ;
            System.out.println(e) ;
            return "" ;
        }
    }

}
