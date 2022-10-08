package com.connecsen.jereserve.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.bytebuddy.utility.RandomString;

public final class Utility {
	// CONSTANT POUR LA SECURITE
	public static final long EXPIRATION_TIME =365 * 24 * 60 * 60 ;
//	public static final long EXPIRATION_TIME =1 * 60 * 60 ; // 1H

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SECRET = "javainuse";
	public static final String NOTREEMAIL ="je.reserve@connecsen.com";
	public static final String dev_phone_number ="221775073511";
	// PARTIE DEMANDE
    public static final String ADD_INFORMATION = "/information";
	public static final String ADD_INFORMATION_BY_ID = "/information/{id}";
	public static final String CHANGE_STATUS_INFORMATION_BY_ID = "/information/{id}";
	public static final String GET_ALL_INFORMATIONS = "/informations";
		
	// PARTIE DEMANDE
	public static final String SMS = "/sms";
	public static final String ADD_DEMAND = "/demand";
	public static final String ADD_DEMAND_TO_PARTENAIRE = "/demand/partenaire";
	public static final String ADD_DEMAND_BY_ID = "/demand/{id}";
	public static final String CHANGE_STATUS_DEMAND_BY_ID = "/demand/{id}";
	public static final String GET_ALL_DEMANDS = "/demands";
	// PARTIE STRUCTURE
    public static final String ADD_STRUCTURE = "/structure";
	public static final String GET_STRUCTURE_BY_ID = "/structure/{id}";
	public static final String CHANGE_STATUS_STRUCTURE_BY_ID = "/structure/status/{id}";
	public static final String GET_ALL_STRUCTURES = "/structures";


		
		
    public static final String ADD_UPDATE_USER = "/user/add";
	public static final String ADD_RESERVATION_TO_USER = "/user/add/reservation/{id}";
	public static final String ADD_RESERVATION_TO_USER_AND_RESSOURCE = "/user/add/reservation/user/{idUser}/ressource/{idRessource}";
	public static final String GET_USER_BY_ID = "/users/{id}";
	public static final String DISABLE_USER_BY_ID = "/users/disable/{id}";
	public static final String GET_ALL_USERS = "/users";
	public static final String GET_ALL_USERS_ACTIVED = "/users/actived";
	public static final String SEND_SMS = "/sms";



	
	public static final String DO_CONTACTED = "/acceuil/user/contacter";
	public static final String DO_REGISTER = "/user/register";
	public static final String DO_REGISTER_BY_ADMIN = "/user/register/admin";
	public static final String DO_LOGIN = "/login";
	public static final String DO_ACTIVATION = "/activation";

	public static final String DO_FORGOT_PASSWORD = "/user/forgot";
	public static final String DO_UPDATE_PASSWORD = "/user/updatePassword";
	public static final String DO_UPDATE_PASSWORD_USER = "/user/updatePassword/user";
	//GENERATE CALENDAR
	public static int getMonthNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH)+1;
	}
	public static int getTodayNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DATE);
	}
	
	public static int getAnnneNumber(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	//GENERATE CALENDAR
	public static Map<String,Integer> getDayChoosed() {
		 Map<String,Integer> map=new HashMap<String,Integer>(); 
		    map.put("lundi",1);  
		    map.put("mardi",2);  
		    map.put("mercredi",3);  
		    map.put("jeudi",4);;
		    map.put("vendredi",5);  
		    map.put("samedi",6);  
		    map.put("dimanche",7);;
		return map;
	}
	//CHECK OBJET IS NULL
	public static boolean checkNull(Object obj) {
		return (obj !=null)? true : false;
	}
	//DATE OF TODAY
	public static String getToday() {
		Date yourDate = new Date();

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
		String date = DATE_FORMAT.format(yourDate);
		return date;
	}
	//GENERATEUR DE TOKEN POUR RECUPERER LE MOT DE PASSE
	public static String getTokenResetPassword() {
		return RandomString.make(10);
	}
	  
	// CODE ERROR AND MESSAGE
	
	public static Map<Integer,String> CodeAndMessage() {
		Map<Integer,String> map=new HashMap<Integer,String>(); 
		    map.put(200,"Successfully request");
		    map.put(201,"Successfully request add demand to User ");
		    map.put(202,"user or demande don't exist");
		    map.put(203,"this code don't exist");
		    map.put(204,"sending message failed");
		    map.put(205,"this structure don't exist");
		    map.put(500,"Error  servor"); 
		    map.put(400,"Error  send message api orange"); 

		return map;
	}
	  
			
}
