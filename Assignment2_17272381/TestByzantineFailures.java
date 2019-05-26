package Assignment2_17272381;

public class TestByzantineFailures {
	
	static final int ATTACK = 1; 
	static final int RETREAT = -1;
	static final int CRASH = 0;
	
	public static void main(String[] args) {
		int BettyPlan = ATTACK;
		int CarlaPlan = ATTACK;
		int DavePlan = RETREAT;
		int AntonPlan = RETREAT;
		int i = 0;
		while (i < 100 ) {
			testSetUp t = new testSetUp();
			t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Byzantine failures");
		
			i++;
		}
	}
}
