package packagejavafx;

import java.io.*;
import java.util.*;

public class ngramnew {
    static FileWriter fr;
    public static List<String> ngrams(int n, String str) {
        List<String> ngrams = new ArrayList<String>();
        String[] words = str.split(" ");
        for (int i = 0; i < words.length - n + 1; i++)
            ngrams.add(concat(words, i, i + n));
        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++)
            sb.append((i > start ? " " : "") + words[i]);
        return sb.toString();
    }

    public static void ngram(int n) {
        //for (int n = 1; n <= 3; n++) {
        BufferedReader r = null;
        File f;
        f = new File("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\output"+n+".txt");
        String fpath = "F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\ngrams"+n+".txt";
        
        try {
            r = new BufferedReader(new FileReader(f));
            PrintWriter writer=new PrintWriter("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\ngrams"+n+".txt");
               writer.print("");
               writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        try {
            String s = null;
            s = r.readLine();
            while (s != null) {
                for (String ngram : ngrams(3, s))
                    appendUsingFileWriter(fpath, ngram);
                //  System.out.println(ngram);
                //System.out.println();
                //}
                s=r.readLine();
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
         fr = null;
        try {
            // Below constructor argument decides whether to append or override
            fr = new FileWriter(file, true);
            fr.write(text+"\n");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

