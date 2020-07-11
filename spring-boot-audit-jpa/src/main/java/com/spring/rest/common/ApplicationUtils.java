/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.rest.common;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;




//import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Asus
 */
public class ApplicationUtils {

	public static final String WEEK_DAYS[] = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
			"Friday" };

	public static String provideLocalFormateWeekDays(String days) {
		return days.substring(0, Math.min(days.length(), 3));
	}

//	private static final Logger logger = Logger.getLogger(ApplicationUtils.class);

	public static String provideStringDateTimeForBirthDate(String stringDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa").parse(stringDate);
		} catch (ParseException e) {
//			logger.error(e);
		}
		return new SimpleDateFormat("yyyy-MM-yy").format(date);
	}

	public static Date parseStringToDate(String stringDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
		} catch (ParseException e) {
//			logger.error(e);
		}
		return date;
	}

	public static String parseDateToString(Date stringDate) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").format(stringDate);
		} catch (Exception e) {
			return null;
		}

	}

	public static boolean isNullOrEmpty(String str) {
		if (str != null && !str.trim().isEmpty())
			return true;
		return false;
	}

	public static Date parseStringToTime(String stringTime) {
		Date date = null;
		try {
			date = new SimpleDateFormat("hh:mm a").parse(stringTime);
		} catch (ParseException e) {
//			logger.error(e);
		}
		return date;
	}

	public static String provideStringDateTimeToStringDate(String stringDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(stringDate);
		} catch (ParseException e) {
//			logger.error(e);
		}
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}

	public static String convertDateToLocalDateTimeWithName(Date date) {
		try {
			/* output is 17-02-2019 05:33 PM */
			return new SimpleDateFormat("dd-MM-yyyy hh:mm aaa").format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToLocalDateTime(Date date) {
		try {
			/* output is 17-02-2019 05:33 PM */
			return new SimpleDateFormat("dd-MM-yyyy hh:mm aaa").format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date convertStringToDate(String stringDate) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy hh:mm aaa").parse(stringDate);
		} catch (ParseException e) {
//			logger.error(e);
		}
		return date;
	}

	public static String provideCurrentAge(String date) {
		Period period = providePerodDate(date);
		String age = null;
		String year = null;
		String day = null;
		String month = null;
		if (period.getYears() != 0) {
			year = string(period.getYears()) + " years ";
		}
		if (period.getMonths() != 0) {
			month = string(period.getMonths()) + " months ";
		}
		if (period.getDays() != 0) {
			day = string(period.getDays()) + " days ";
		}
		age = year + month + day;
		age = age.replaceAll("null", "");
		return age;
	}

	public static String string(Integer integer) {
		return Integer.toString(integer);
	}

	public static Period providePerodDate(String date) {
		LocalDate today = LocalDate.now();
		LocalDate birthday = LocalDate.parse(date);
		Period period = Period.between(birthday, today);
		return period;
	}

	public static Date currentDate() {
		return new Date();
	}

	public static String getMonthNumberByMonthName(String monthName) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monthName));
		} catch (ParseException ex) {
		}

		String monthNo = String.valueOf((cal.get(Calendar.MONTH) + 1));

		if (String.valueOf(monthNo).length() < 2) {
			monthNo = "0" + monthNo;
		}

		return monthNo;
	}

	public static String getCoreSettingTypeName(int typeId) {
		String typeName = "";

		switch (typeId) {
		case 2101:
			typeName = "Academic Year";
			break;
		case 2102:
			typeName = "Class";
			break;
		case 2103:
			typeName = "Shift";
			break;
		case 2104:
			typeName = "Section";
			break;
		case 2105:
			typeName = "Group";
			break;
		case 2106:
			typeName = "Student Category";
			break;
		case 2107:
			typeName = "Academic Session";
			break;
		case 2601:
			typeName = "Designation";
			break;
		case 2201:
			typeName = "Exam";
			break;
		case 2202:
			typeName = "Subject";
			break;
		case 2301:
			typeName = "Period";
			break;
		case 2302:
			typeName = "Staff Category";
			break;
		case 2401:
			typeName = "Fund";
			break;
		case 2501:
			typeName = "Fee head";
			break;
		case 2502:
			typeName = "Fee sub head";
			break;
		case 2503:
			typeName = "Waiver";
			break;
		default:
			break;
		}

		return typeName;
	}

	public static List<Date> getDateListByMonthAndYear(String monthName, String year) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.MONTH, (Month.valueOf(monthName.trim().toUpperCase()).getValue() - 1));

		cal.set(Calendar.YEAR, Integer.parseInt(year));

		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		List<Date> dateList = new ArrayList<>();

		for (int i = 0; i < maxDay; i++) {

			cal.set(Calendar.DAY_OF_MONTH, i + 1);

			Date date = cal.getTime();

			dateList.add(date);
		}

		return dateList;

	}

	public static List<Date> getDateListByYear(String year) {

		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Integer.parseInt(year));

		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_YEAR);

		List<Date> dateList = new ArrayList<>();

		for (int i = 0; i < maxDay; i++) {

			cal.set(Calendar.DAY_OF_YEAR, i + 1);

			Date date = cal.getTime();

			dateList.add(date);
		}
		return dateList;
	}

	public static int getDayOfMonth(Date aDate) {

		if (aDate != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(aDate);
			return cal.get(Calendar.DAY_OF_MONTH);
		} else {
			return 0;
		}

	}

	public static double providePercentWithTwoDecimalDigits(int partNumber, int fullNumber) {
		if (fullNumber == 0) {
			return 0;
		}
		return Double.parseDouble(new BigDecimal(
				(Double.parseDouble(String.valueOf(partNumber)) / Double.parseDouble(String.valueOf(fullNumber))) * 100)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	public static String convertDateToStringTimeWithAmPm(Date date) {
		return new SimpleDateFormat("hh:mm:ss a").format(date);
	}

	public static String provideDayNameByDate(Date date) {
		return new SimpleDateFormat("EEEE").format(date);
	}

	public static String provideDayNameByStringDate(String day) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(day);
		} catch (ParseException e) {
//			logger.error(e);
		}
		return new SimpleDateFormat("dd-MM-yyyy").format(date);
	}

	public static String getImagePath(String imageFolder) {
		Properties prop = new Properties();
		InputStream inputStream = ApplicationUtils.class.getClassLoader()
				.getResourceAsStream("imagepath/image-path.properties");
		try {
			prop.load(inputStream);
			return prop.getProperty(imageFolder);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}

	public static double convertTwoDecimalPlacedNumber(double input) {
		double output = 0;
		if (input != 0) {
			output = Double.parseDouble(new DecimalFormat("###0.##").format(input));
		}
		return output;
	}

	public static int provideSequenceStartNumber(List<Integer> sortedList, int numOfDays) {

		int counter = 0;

		int startDay = 0;

		int previousDay = 5000;

		for (int i = 0; i < sortedList.size(); i++) {
			int currentDay = sortedList.get(i);

			if ((previousDay + 1) == currentDay) {
				counter++;
			} else {
				counter = 0;
			}

			if (counter == 1) {
				startDay = sortedList.get(i);
			}

			if (counter == (numOfDays - 1)) {
				return startDay;
			}
			previousDay = sortedList.get(i);
		}

		return 0;
	}

	public static String provideStartAndEndDate(List<Integer> sortedList, int numOfDays, String year,
			String monthName) {
		String absentDays = null;
		String twoDigitStarDays = null;
		String twoDigitEndDays = null;

		if (provideSequenceStartNumber(sortedList, numOfDays) != 0) {
			int startDay = provideSequenceStartNumber(sortedList, numOfDays);
			int endDay = startDay + (numOfDays - 1);

			if (String.valueOf(startDay).length() < 2) {
				twoDigitStarDays = "0" + startDay;
			} else {
				twoDigitStarDays = String.valueOf(startDay);
			}

			if (String.valueOf(endDay).length() < 2) {
				twoDigitEndDays = "0" + endDay;
			} else {
				twoDigitEndDays = String.valueOf(endDay);
			}

			absentDays = twoDigitStarDays + "-" + getMonthNumberByMonthName(monthName) + "-" + year + "to"
					+ twoDigitEndDays + "-" + getMonthNumberByMonthName(monthName) + "-" + year;
		}

		return absentDays;
	}

	public static int[] calculateAge(Date birthDate) {

		long differencelOng = TimeUnit.DAYS.convert((new Date().getTime() - birthDate.getTime()), TimeUnit.DAYS);

		long days = differencelOng / 86400000;

		int year = (int) days / 365;

		int month = (int) (days - (365 * year)) / 30;

		// int day = (int) (days - (365 * year + (month * 30)));
		int[] dateDiff = new int[2];
		dateDiff[0] = year;
		dateDiff[1] = month;

		return dateDiff;
	}

	public static Date formatDate(Date date) {
		Date parsedDate = null;
		SimpleDateFormat mdyFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dmy = mdyFormat.format(date);
		System.out.println(dmy);

		try {
			parsedDate = mdyFormat.parse(dmy);
		} catch (ParseException e) {
			System.out.println(e);
		}
		return parsedDate;
	}

	/*
	 * public static String provideInstitueId(HttpServletRequest request) {
	 * SecurityContext securityContext = SecurityContextHolder.getContext();
	 * OAuth2Authentication requestingUser = (OAuth2Authentication)
	 * securityContext.getAuthentication(); if (requestingUser.isClientOnly()) {
	 * return request.getParameter("instituteId"); } else { return
	 * UserInfoUtils.getLoggedInUserInstitute(); } }
	 */
	/*
	 * public static boolean isClient(HttpServletRequest request) {
	 * 
	 * boolean clientStatus = false;
	 * 
	 * if (request != null) { SecurityContext securityContext =
	 * SecurityContextHolder.getContext(); OAuth2Authentication requestingUser =
	 * (OAuth2Authentication) securityContext.getAuthentication(); if
	 * (requestingUser.isClientOnly()) { clientStatus = true; } }
	 * 
	 * return clientStatus;
	 * 
	 * }
	 */

	public static String prepareAddress(String divisionName, String districtName, String thanaName, String postOffice,
			String postalCode, String villageName, String roadNo, String houseNo) {

		String divName = null;
		String disName = null;
		String thanName = null;
		String poOffice = null;
		String postCode = null;
		String villName = null;
		String rodNo = null;
		String hoseNo = null;

		if (divisionName != null) {
			divName = divisionName.trim();
		}

		if (districtName != null) {
			disName = districtName.trim();
		}

		if (thanaName != null) {
			thanName = thanaName.trim();
		}

		if (postOffice != null) {
			poOffice = postOffice.trim();
		}

		if (postalCode != null) {
			postCode = postalCode.trim();
		}

		if (villageName != null) {
			villName = villageName.trim();
		}

		if (roadNo != null) {
			rodNo = roadNo.trim();
		}

		if (houseNo != null) {
			hoseNo = houseNo.trim();
		}

		return "Bangladesh" + " ;" + divName + " ;" + disName + " ;" + thanName + " ;" + poOffice + " ;" + postCode
				+ " ;" + villName + " ;" + rodNo + " ;" + hoseNo + " ;";
	}

	public static String checkMoblNo(String moblNo) {
		String phoneNo = "";
		int moblLenth = moblNo.length();
		if (moblLenth == 11 && moblNo.startsWith("01")) {
			phoneNo = "88" + moblNo;
		} else if (moblLenth == 13 && moblNo.startsWith("8801")) {
			phoneNo = moblNo;
		} else if (moblLenth == 14 && moblNo.startsWith("+8801")) {
			phoneNo = moblNo;
		}
		return phoneNo;
	}

	/*
	 * public static boolean isClientValid(String requestForm) {
	 * 
	 * if (BCrypt.checkpw(StaticValueProvider.CLIENT_ID1, requestForm)) { return
	 * true; } else { return false; } }
	 */

	

	

	public static String convertCamelcaseString(String str) {

		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				if (ch[i] >= 'a' && ch[i] <= 'z') {

					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			} else if (ch[i] >= 'A' && ch[i] <= 'Z')

				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		String st = new String(ch);
		return st;
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static String isNotNullString(String stringValue) {
		if (stringValue != null) {
			return stringValue;
		} else {
			return null;
		}
	}

	public static boolean isNotNullObject(Object object) {
		if (object != null) {
			return true;
		}
		return false;
	}

}
