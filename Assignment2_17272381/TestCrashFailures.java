package Assignment2_17272381;

public class TestCrashFailures {
	
	static final int VISIT = 1; 
	static final int NOTVISIT = -1;
	static final int CRASH = 0;
	
	public static void main(String[] args) {
		//can change friends' plan here
		int BettyPlan = NOTVISIT;
		int CarlaPlan = VISIT;
		int DavePlan = VISIT;
		int AntonPlan = NOTVISIT;
		//test 100 days
		int i = 0;
		while (i < 100 ) {
			System.out.println("Day " + i);
			testSetUp t = new testSetUp();
			t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Crash failures");
		
			i++;
		}
		
	}

}
