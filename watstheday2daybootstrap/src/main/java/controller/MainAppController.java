package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainAppCtrl")
public class MainAppController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8927635117296359422L;
	
	private String day = "";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("EFEW @#$# I rched here" + req.getContextPath());
		Calendar cal = Calendar.getInstance();
		cal.setTime(Calendar.getInstance().getTime());
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
			BufferedReader buf = new BufferedReader(
					new InputStreamReader(classLoader.getResourceAsStream("watstheday.txt")));
			while (true) {
				lineJustFetched = buf.readLine();
				System.out.println(" @@#@ lineJustFetched =" + lineJustFetched);
				if (null == lineJustFetched) {
					day = "It's A Beautiful Day";
					break;
				} else {
					wordsArray = lineJustFetched.split("\t");
					length = wordsArray.length;
					for (String string : wordsArray) {
						System.out.println(" !@#@!# string =" + string);
					}

					for (int i = 0; i < length; i++) {
						System.out.println("@#$@$ "+clickedDate + wordsArray[i]);
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
					System.out.println("WEFEWF");
					day = "It's A Beautiful Day!";
				}
			}
			buf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp.setContentType("text/html;charset=UTF-8");
	    resp.getWriter().write(day);
	}
	
}
