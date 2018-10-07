package watsthedaytoday;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DictionaryLoader {
	
	public static AutoCompleteDictionaryTrie loadDictionary(Dictionary d, InputStream is)
    {
        // Dictionary files have 1 word per line
		File file = null;
		AutoCompleteDictionaryTrie trieDict=null;
        BufferedReader reader = null;
        
        try {
            String nextWord;
            reader = new BufferedReader(new InputStreamReader(is));
            while ((nextWord = reader.readLine()) != null) {
                d.addWord(nextWord);
            }
            trieDict=(AutoCompleteDictionaryTrie)d;
            System.out.println(" #@ d="+d.size());
           
        } catch (IOException e) {
            System.out.println("@#$@ Problem loading dictionary file: " + file.getName());
            e.printStackTrace();
        }
        return trieDict;
    }
 
}
