package Assignment2_17272381;

public class TestByzantineFailures {
	
	static final int VISIT = 1; 
	static final int NOTVISIT = -1;
	static final int CRASH = 0;
	
	public static void main(String[] args) {
		int BettyPlan = VISIT;
		int CarlaPlan = VISIT;
		int DavePlan = NOTVISIT;
		int AntonPlan = NOTVISIT;
		int i = 0;
		while (i < 100 ) {
			System.out.println(i);
			testSetUp t = new testSetUp();
			t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Byzantine failures");
		
			i++;
		}
	}
}
