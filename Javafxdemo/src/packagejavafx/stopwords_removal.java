package packagejavafx;

import java.io.*;
import java.util.ArrayList;


public class stopwords_removal {
    static FileWriter fr;
    public static String[] stopWords = {
"without", "see", "unless", "due", "also", "must", "might", "like", "]", "[", "}", "{", "<", ">", "?", "\"", "\\", "/", ")", "(", "will", "may", "can", "much", "every", "the", "in", "other", "this", "the", "many", "any", "an", "or", "for", "in", "an", "an ", "is", "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any", "are", "aren’t", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both", "but", "by", "can’t", "cannot", "could",
        "couldn’t", "did", "didn’t", "do", "does", "doesn’t", "doing", "don’t", "down", "during", "each", "few", "for", "from", "further", "had", "hadn’t", "has", "hasn’t", "have", "haven’t", "having",
        "he", "he’d", "he’ll", "he’s", "her", "here", "here’s", "hers", "herself", "him", "himself", "his", "how", "how’s", "i ", " i", "i’d", "i’ll", "i’m", "i’ve", "if", "in", "into", "is",
        "isn’t", "it", "it’s", "its", "itself", "let’s", "me", "more", "most", "mustn’t", "my", "myself", "no", "nor", "not", "of", "off", "on", "once", "only", "ought", "our", "ours", "ourselves",
        "out", "over", "own", "same", "shan’t", "she", "she’d", "she’ll", "she’s", "should", "shouldn’t", "so", "some", "such", "than",
        "that", "that’s", "their", "theirs", "them", "themselves", "then", "there", "there’s", "these", "they", "they’d", "they’ll", "they’re", "they’ve",
        "this", "those", "through", "to", "too", "under", "until", "up", "very", "was", "wasn’t", "we", "we’d", "we’ll", "we’re", "we’ve",
        "were", "weren’t", "what", "what’s", "when", "when’s", "where", "where’s", "which", "while", "who", "who’s", "whom",
        "why", "why’s", "with", "won’t", "would", "wouldn’t", "you", "you’d", "you’ll", "you’re", "you’ve", "your", "yours", "yourself", "yourselves",
        "Without", "See", "Unless", "Due", "Also", "Must", "Might", "Like", "Will", "May", "Can", "Much", "Every", "The", "In", "Other", "This", "The", "Many", "Any", "An", "Or", "For", "In", "An", "An ", "Is", "A", "About", "Above", "After", "Again", "Against", "All", "Am", "An", "And", "Any", "Are", "Aren’t", "As", "At", "Be", "Because", "Been", "Before", "Being", "Below", "Between", "Both", "But", "By", "Can’t", "Cannot", "Could",
        "Couldn’t", "Did", "Didn’t", "Do", "Does", "Doesn’t", "Doing", "Don’t", "Down", "During", "Each", "Few", "For", "From", "Further", "Had", "Hadn’t", "Has", "Hasn’t", "Have", "Haven’t", "Having",
        "He", "He’d", "He’ll", "He’s", "Her", "Here", "Here’s", "Hers", "Herself", "Him", "Himself", "His", "How", "How’s", "I ", " I", "I’d", "I’ll", "I’m", "I’ve", "If", "In", "Into", "Is",
        "Isn’t", "It", "It’s", "Its", "Itself", "Let’s", "Me", "More", "Most", "Mustn’t", "My", "Myself", "No", "Nor", "Not", "Of", "Off", "On", "Once", "Only", "Ought", "Our", "Ours", "Ourselves",
        "Out", "Over", "Own", "Same", "Shan’t", "She", "She’d", "She’ll", "She’s", "Should", "Shouldn’t", "So", "Some", "Such", "Than",
        "That", "That’s", "Their", "Theirs", "Them", "Themselves", "Then", "There", "There’s", "These", "They", "They’d", "They’ll", "They’re", "They’ve",
        "This", "Those", "Through", "To", "Too", "Under", "Until", "Up", "Very", "Was", "Wasn’t", "We", "We’d", "We’ll", "We’re", "We’ve",
        "Were", "Weren’t", "What", "What’s", "When", "When’s", "Where", "Where’s", "Which", "While", "Who", "Who’s", "Whom",
        "Why", "Why’s", "With", "Won’t", "Would", "Wouldn’t", "You", "You’d", "You’ll", "You’re", "You’ve", "Your", "Yours", "Yourself", "Yourselves"
        };

public static ArrayList<String> wordsList = new ArrayList<String>();

public static void stopwords(int n){
        
        wordsList.clear();
        BufferedReader r = null;File f;
        f = new File("F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\textdoc"+n+".txt");
        String fpath="F:\\RVCE\\DAA\\Javafxdemo\\src\\data\\output"+n+".txt";
        try
        {
                r=new BufferedReader(new FileReader(f));
        }
        catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        try{
        String s= null;
                s=r.readLine();
         while(s!=null)
         {
        s=s.trim().replaceAll("\\s+", " ");
        //s=s.replaceAll("\\.+","").replaceAll("\\,+","");
                 s=s.replaceAll("\\p{Punct}+","");
        String[] words = s.split(" ");

        for (String word : words)
        {
           wordsList.add(word);
        }
        PrintWriter writer=new PrintWriter(fpath);
               writer.print("");
               writer.close();
        //remove stop words here from the temp list
        for (int i = 0; i < wordsList.size(); i++) {
// get the item as string
            for (int j = 0; j < stopWords.length; j++) {
                if (stopWords[j].contains(wordsList.get(i))) {
                    wordsList.remove(i);
                    i--;
                    break;
                }
            }
        }
        for (String str : wordsList) {
                appendUsingFileWriter(fpath,str+" ");
        }
        s=r.readLine();
        }
        r.close();
        //f= new FileWriter(fpath, false);
                }
        catch(IOException e)
        {
                e.printStackTrace();
        }
        catch(IndexOutOfBoundsException a)
        {
            a.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
}


        public static void appendUsingFileWriter(String filePath, String text) {
                File file = new File(filePath);
                 fr = null;
                try {
                        // Below constructor argument decides whether to append or override
                        fr = new FileWriter(file, true);
                        fr.write(text);

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

