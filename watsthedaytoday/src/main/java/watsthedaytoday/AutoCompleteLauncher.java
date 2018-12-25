package watsthedaytoday;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Startup;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Startup
@ManagedBean
@SessionScoped
public class AutoCompleteLauncher {
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}


	public static String dictFile = "/dict.txt";

	public static int count = 0;
	
	private String prefix;
	
	@ManagedProperty("#{calendarView.searchField}")
	private String userID;
	
	@Inject
	private static AutoCompleteDictionaryTrie autoCompleteDictTrie;
	
	public static AutoCompleteDictionaryTrie getAutoCompleteDictTrie() {
		return autoCompleteDictTrie;
	}

	public static void setAutoCompleteDictTrie(AutoCompleteDictionaryTrie autoCompleteDictTrie) {
		AutoCompleteLauncher.autoCompleteDictTrie = autoCompleteDictTrie;
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public AutoCompleteLauncher() {
		
		AutoCompleteDictionaryTrie tr = new AutoCompleteDictionaryTrie();
		setAutoCompleteDictTrie(
				DictionaryLoader.loadDictionary(tr, AutoCompleteLauncher.class.getResourceAsStream((dictFile))));
		System.out.println(" @#$@## "+getAutoCompleteDictTrie());
	}

	
	public List<String> completeText(String prefix){
		System.out.println("@$@#$ prefix ="+prefix);
		int length = prefix.length();
		List<String> options = new ArrayList<String>();
		if(!Character.isWhitespace(prefix.charAt(length-1))){
			 options = autoCompleteDictTrie.predictCompletions(prefix.toLowerCase(),4);
		}
	
		for (String option : options) {
			System.out.println("option=" + option);
		}
		System.out.println("@#@ options in string"+options.toString());
		return options;
	}

}
