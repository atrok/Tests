package main;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Start {

	private static String path="C:\\Windows";
	private static String folderPattern;// supposed to be YYYYMMDD 
	private static Date folderPatternDate=new Date();
	//private static Calendar mydate = new GregorianCalendar();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		if (args!=null){// args[0] - folder name pattern; args[1] - path to folders;
			path=args[1];
			
			if (!path.endsWith("/")) {
			    path = path+"/"; // Adding trailing slash
			}
			
			folderPattern=args[0];
			try {
				folderPatternDate=convertToDate(folderPattern);
	//			mydate.setTime(folderPatternDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("Arguments are missing, exiting");
			System.exit(0);
		}
		File file = new File(path);
		String[] names = file.list();

		for(String name : names)
		{
		    if (new File(path + name).isDirectory())
		    {
		        try {
					if (checkFolderName(name))
					System.out.println(name);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}

	}

	private static boolean checkFolderName(String name) throws ParseException{
		
		Date temp=convertToDate(name);
//		Calendar tempCalendar=new GregorianCalendar();
//		tempCalendar.setTime(temp);
		
		if (folderPatternDate.compareTo(temp)>=0)
			return true;
		return false;
		
	}
	
	private static Date convertToDate(String name) throws ParseException{
				
			if (name.length()>8)
				name=name.substring(0, 8);
			return new SimpleDateFormat("yyyyMMdd").parse(name); 
	}
}
