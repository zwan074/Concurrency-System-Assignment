package Assignment2_17272381;

public class TestNoFailure {
	
	static final int ATTACK = 1; 
	static final int RETREAT = -1;
	static final int CRASH = 0;
	
	public static void main(String[] args) {
		
		int BettyPlan = ATTACK;
		int CarlaPlan = ATTACK;
		int DavePlan = RETREAT;
		int AntonPlan = RETREAT;
		testSetUp t = new testSetUp();
		t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Loyal");

		BettyPlan = ATTACK;
		CarlaPlan = RETREAT;
		DavePlan = RETREAT;
		AntonPlan = RETREAT;
		t = new testSetUp();
		t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Loyal");

		
		
		BettyPlan = ATTACK;
		CarlaPlan = RETREAT;
		DavePlan = RETREAT;
		AntonPlan = ATTACK;
		t = new testSetUp();
		t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Loyal");

		BettyPlan = ATTACK;
		CarlaPlan = ATTACK;
		DavePlan = RETREAT;
		AntonPlan = ATTACK;
		t = new testSetUp();
		t.test ( BettyPlan, CarlaPlan,  DavePlan,  AntonPlan , "Loyal");
		
	}
	
	
	
	

}
