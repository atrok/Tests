package test;

import static org.junit.Assert.*;
import blocks.Start;

public class Test {

	@org.junit.Test
	public void testSingleArgStart() {
		
		try{
			Start.main(new String[]{"2067"});
			
		}catch(Exception e){
			e.printStackTrace();
			fail("exception is not expected at this place of code");
		}
	}

	@org.junit.Test
	public void testDoubleArgStart() {
		
		
		try{
			Start.main(new String[]{"2067","m"});
			
			
		}catch(Exception e){
			e.printStackTrace();
			fail("exception is not expected at this place of code");
		}
	}

}
