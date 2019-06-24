package Assignment2_17272381;

public class TestByzantineFailures {
	
	static final int VISIT = 1; 
	static final int NOTVISIT = -1;
	
	public static void main(String[] args) {
		
		//test 100 days
		int i = 0;
		while (i < 100 ) {
			System.out.println("Day " + i);
			int BettyPlan = Math.random() > 0.5 ? VISIT : NOTVISIT;
			int CarlaPlan = Math.random() > 0.5 ? VISIT : NOTVISIT;
			int DavePlan = Math.random() > 0.5 ? VISIT : NOTVISIT;
			int AntonPlan = Math.random() > 0.5 ? VISIT : NOTVISIT;
			testSetUp t = new testSetUp();
			t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Byzantine failures");
		
			i++;
		}
	}
}
