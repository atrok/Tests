package blocks;

import java.util.Arrays;
import java.util.HashMap;
import blocks.Blocks;

public class Start {
	private static int[] nonTreatableHours=new int[]{1,2,3,5,7,11};
	
	public static void main(String[] args){

		
		if(args.length==0){
			System.out.println("Enter amount of hours you want calculate blocks for");
			//System.out.println("java -jar <jarfile> <num_of_hours> (m<mode to calculatet entries from 1 to num_of_hours, optional>)");
			System.exit(0);
		}
		
		
		
		try{
			Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
	        System.err.println("Argument" + args[0] + " must be an integer.");
	        System.exit(1);
	    }
		
		if(isIn(Integer.parseInt(args[0])))
		{
			System.out.println("Entered amount of hours ("+Integer.parseInt(args[0])+") is out of range of calculation");
			System.exit(0);
		}
		
		
		Start s=new Start();
		
		if (args.length>1){
			if(args[1].equals("m")){
			for (int i=0;i<=Integer.parseInt(args[0]);i++)
				s.new BlocksCalculation(i);
			}
		}else			
			s.new BlocksCalculation(Integer.parseInt(args[0]));
		
	}
	
public class  BlocksCalculation{

	private int FOURH=Blocks.FOURH.getValue();
	private int SIXH=Blocks.SIXH.getValue();
	private int NINEH=Blocks.NINEH.getValue();
	private int TWENTYH=Blocks.TWENTYH.getValue();
	private int hours=0;
	private HashMap<Integer,Integer> result=new HashMap<Integer,Integer>();

	 
	
	public BlocksCalculation(int h){/// calculate shortest path to sum up hours
		
		hours=h;
	
		if (!isIn(h)){
		for(int i=Blocks.values().length-1; i>=0;i--){
			
			hours=calculateFor(hours,Blocks.values()[i]);
			if (hours==0)
				break;
		}
		}
		/* results representation */
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("Available blocks |  "+FOURH+"  |  "+SIXH+"  |  "+NINEH+"  |  "+TWENTYH);
		System.out.println("--------------------------------------------------------------");
		System.out.print("Result           |  ");
		
		int sum=0;
		
		if (hours!=0){ // we found number that we cannot disassemble with use of our blocks
			System.out.print("Can't find blocks to properly evaluate the price  |");
		}else{ 
			for (Blocks b: Blocks.values()){
				int res=0;
				if (result.containsKey(b.getValue()))
						res=result.get(b.getValue());
				if (0!=res)
					sum=sum+res*b.getPrice();
				System.out.print(res+"  |  ");
			}
		}
		System.out.print("In: "+h+"    | ");
		System.out.print("price $"+sum+"\n");
	}
	
	private  int  calculateFor(int hours, Blocks divider){
		int div=divider.getValue();
		int i=0;
		while (hours>=div){
			hours=hours-div;
			i++;
		}
		if (isIn(hours)){// increase hours to previous value;
			hours=hours+div;
			i--;
		}
		result.put(div, i);
		
		return hours; //we need to return either 0 or value of hours to be evaluated with next block
	}



}
private  static boolean isIn(int n){
		for (int k: nonTreatableHours)
			if (n==k)
				return true;
		return false;
	}
}
