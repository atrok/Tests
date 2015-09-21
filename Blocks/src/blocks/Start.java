package blocks;

import java.util.HashMap;

public class Start {
	
	private static int FOURH=Blocks.FOURH.getValue();
	private static int SIXH=Blocks.SIXH.getValue();
	private static int NINEH=Blocks.NINEH.getValue();
	private static int TWENTYH=Blocks.TWENTYH.getValue();
	private static int hours=0;
	private static HashMap<Integer,Integer> result=new HashMap<Integer,Integer>();
	 
	
	public static void main(String[] args){
		
		if(args.length!=1){
			System.out.println("Enter amount of hours you want calculate blocks for");
			System.exit(0);
		}
		
		try{
			hours=Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
	        System.err.println("Argument" + args[0] + " must be an integer.");
	        System.exit(1);
	    }
		
		
		
		/// calculate shortest path to sum up hours
		for(int i=Blocks.values().length-1; i>=0;i--){
			
			hours=calculateFor(hours,Blocks.values()[i]);
			if (hours==0)
				break;
		}
		
		/* results representation */
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("Blocks:"+FOURH+"  |  "+SIXH+"  |  "+NINEH+"  |  "+TWENTYH+"  ");
		System.out.println("--------------------------------------------------------------");
		System.out.print(args[0]+"    | ");
		int sum=0;
		for (Blocks b: Blocks.values()){
			int res=0;
			if (result.containsKey(b.getValue()))
					res=result.get(b.getValue());
			if (0!=res)
				sum=sum+res*b.getPrice();
			System.out.print(res+"  |  ");
		}
		
		System.out.print("price: "+sum);
	}
	
	private static int  calculateFor(int hours, Blocks divider){
		int div=divider.getValue();
		int i=0;
		while (hours-div>=div || (hours-div)==0){
			hours=hours-div;
			i++;
		}
		result.put(div, i);
		return hours; //we need to return either 0 or value of hours 
	}

}

