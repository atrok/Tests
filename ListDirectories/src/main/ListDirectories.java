package main;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ListDirectories {

	private static String path="C:\\Windows";
	private static String folderPattern;// supposed to be YYYYMMDD 
	private static Date folderPatternDate=new Date();
	//private static Calendar mydate = new GregorianCalendar();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		if (args.length>1){// args[0] - folder name pattern; args[1] - path to folders;
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
			System.out.println("java -jar <path to jar> <yyyyMMdd> <pathToDirectory in Windows format>");
			System.out.println(Arrays.toString(args));
			System.exit(0);
		}
		File file = new File(path);
		String[] names = file.list();

		List filteredNames=new ArrayList();
		
		System.out.println("Folders content before being filtered out");
		System.out.println("----------------");
		for(String name : names)
		{
		    if (new File(path + name).isDirectory())
		    {
		        try {
					if (checkFolderName(name))
					
						filteredNames.add(name);
					System.out.println(name);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}
		
		System.out.println("Results of filtering");
		
		for(int i=0;i<filteredNames.size();i++){
			System.out.println(filteredNames.get(i));
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
			Date d= new SimpleDateFormat("yyyyMMdd").parse(name); 
			
			Calendar tempCalendar=new GregorianCalendar();
			tempCalendar.setTime(d);
			
			int y=tempCalendar.get(Calendar.YEAR);
			tempCalendar.setTimeInMillis(System.currentTimeMillis());
			int ynow=tempCalendar.get(Calendar.YEAR);
			
			if((ynow<y)||(ynow/y)>1)// 
				System.out.println("Warning: Check entered date pattern format - expected format is <yyyyMMdd>");
			
			return d;
	}
}
