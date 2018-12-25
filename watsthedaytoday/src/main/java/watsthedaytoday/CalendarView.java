package watsthedaytoday;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class CalendarView {

	private Date date1 = null;

	private String day = "";

	private String searchField;

	private String result = "We aren't sure of such a day!";

	private String text = "";

	private File file = null;
	
	private String pattern ="[\\w'-/]+";

	public void onDateSelect(SelectEvent event) {
		System.out.println("@!#@! ...onDateSelect....");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void productDetail() {

	}

	public void click() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		int year = cal.get(Calendar.YEAR);
		int month = (cal.get(Calendar.MONTH));
		month++;
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		System.out.println("@#@ year =" + year + " month =" + month + " dayOfMonth =" + dayOfMonth);
		String clickedDate = String.valueOf(dayOfMonth).concat(" ").concat(String.valueOf(month));
		System.out.println(" !@#@ clickedDate =" + clickedDate);
		// ArrayList<String> words = new ArrayList<>();
		String lineJustFetched = null;
		String[] wordsArray = null;
		int length = 0;
		boolean flag = false;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			System.out.println("!@#!@# vertigo0..." + classLoader.getResource("default.png"));
			/*System.out.println(
					"@#@ vertigo1... " + new File(classLoader.getResource("/default.png").getFile()).isFile());*/
			/*System.out.println(
					"@#@ vertigo2... " + new File(classLoader.getResource("/default.png").getFile()).isFile());
			System.out.println(
					"@#@ vertigo3... " + new File(this.getClass().getResource("/default.png").getFile()).isFile());
			System.out.println(
					"@#@ vertigo4... " + classLoader.getResourceAsStream("default.png").available());
			System.out.println(
					"@#@ vertigo5... " + (new File (this.getClass().getResource("/default.png").getFile()).isFile()));*/
			
			classLoader.getResourceAsStream("watstheday.txt");
			BufferedReader buf = new BufferedReader(
					new InputStreamReader(classLoader.getResourceAsStream("watstheday.txt")));
			while (true) {
				lineJustFetched = buf.readLine();
				System.out.println(" @@#@ lineJustFetched =" + lineJustFetched);
				if (null == lineJustFetched) {
					day = "A Beautiful Day";
					break;
				} else {
					wordsArray = lineJustFetched.split("\t");
					length = wordsArray.length;
					for (String string : wordsArray) {
						System.out.println(" !@#@!# string =" + string);
					}

					for (int i = 0; i < length; i++) {
						if (null != clickedDate && "" != clickedDate && clickedDate.equals(wordsArray[i])) {
							i++;
							System.out.println(" @#@! going to save =" + wordsArray[i]);
							day = wordsArray[i];
							flag = true;
							break;
						}

					}

					if (flag) {
						break;
					}

				}
				if (!flag) {
					day = "A Beautiful Day!";
				}
			}
			buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(" hello moto....");
		RequestContext requestContext = RequestContext.getCurrentInstance();
		System.out.println(" @#@# requestContext =" + requestContext.getAttributes());
		setDay(day);
		requestContext.execute("PF('dlg').show()");
	}

	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSearchedDay() {
		return result;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setSearchedDay(String searchedDay) {
		this.result = searchedDay;
	}

	public void search() {
		System.out.println(" @#$@ hello search2 =" + searchField);
		
		String lineJustFetched = null;
		String[] wordsArray = null;
		boolean exitFlag=false;
		try {		
			ClassLoader classLoader = getClass().getClassLoader();
			BufferedReader buf = new BufferedReader(
					new InputStreamReader(classLoader.getResourceAsStream("watstheday.txt")));
			while (true) {
				if(!exitFlag) {
				lineJustFetched = buf.readLine();
				System.out.println(" @@#@ lineJustFetched =" + lineJustFetched);
				if (null == lineJustFetched) {
					result = "We aren't sure of such a day!";
					break;
				} else {
					wordsArray = lineJustFetched.split("\t");
					for (String text : wordsArray) {
						//if (!exitFlag) {
							System.out.println(" !@#@!# text =" + text);
							// get only the word tokens from the text
							List<String> wordTokens = getTokens(text);
							System.out.println(" WE$#@ returned from wordTokens ..." + wordTokens.size());
							// for each token compare with searched word
							for (String string : wordTokens) {
								System.out.println(" @#E#@ enetered final m,atch comparisoin=" + string + " search="
										+ searchField);
								if (string.equalsIgnoreCase(searchField)) {
									System.out.println("@#@ found a match...");
									result = text;
									exitFlag = true;
									break;
								}
							//}
						}
					}
					
				}
			}
			}
			buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	protected List<String> getTokens(String text) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		while (m.find()) {
			tokens.add(m.group());
		}
		System.out.println(" #@#$@$#$ tokens ="+tokens.toString());
		return tokens;
	}
/*	
	private void loadFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResourceAsStream(fileName).getFile());
		System.out.println(" @#@ hey " + file.exists());
	}*/

	
	// TODO Provide a subscription for reminders through phone or email
	// TODO Provide a message which is "funny, informative and destigmatising"
	// TODO Provide links to wiki page in a dialog
	// TODO Provide links of successful celebrities or otherwise who fought their
	// way through this diseases
	// TODO Provide a link to host online blogs or provide a link to their blog
	// about their story
	// TODO Provide a list of addresses where serious breakthroughs are being talked
	// about on these fields
	// TODO Provide list of addresses where computer algorithms are helping to find
	// a breakthrough in the disease treatment
	// TODO Provide a self learning model which can ....Deep Learning Algorithm
	// TODO Provide a merchandise link to some kool t-shirts/caps/accessories to
	// shop on these medical fields

}
