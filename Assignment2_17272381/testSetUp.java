package Assignment2_17272381;

public class testSetUp {
	
	Friends Betty ;
	Friends Carla ;
	Friends Dave ;
	Friends Anton ;
	
	public testSetUp () {
		
		
		
		
	}
	
	
	public void test (int BettyPlan,int CarlaPlan, int DavePlan, int AntonPlan , String AntonRole ) {
		//Initialize objects
		Friends[] friends = new Friends[4] ;
		Betty = new Friends("Betty" , 0 , friends , "Loyal" );
		Carla = new Friends("Carla" , 1 , friends , "Loyal" );
		Dave = new Friends("Dave" , 2 , friends , "Loyal" );
		Anton = new Friends("Anton" , 3 , friends , AntonRole );
		
		friends[0] = Betty;
		friends[1] = Carla;
		friends[2] = Dave;
		friends[3] = Anton;
		
		Betty.setPlan(new int[friends.length]);
		Betty.setMajorityPlan(new int[friends.length]);
		Betty.setReportedplans(new int[friends.length][friends.length] );
		Betty.plan[Betty.id] = BettyPlan;
		
		Carla.setPlan(new int[friends.length]);
		Carla.setMajorityPlan(new int[friends.length]);
		Carla.setReportedplans(new int[friends.length][friends.length] );
		Carla.plan[Carla.id] = CarlaPlan;
		
		Dave.setPlan(new int[friends.length]);
		Dave.setMajorityPlan(new int[friends.length]);
		Dave.setReportedplans(new int[friends.length][friends.length] );
		Dave.plan[Dave.id] = DavePlan;
		
		Anton.setPlan(new int[friends.length]);
		Anton.setMajorityPlan(new int[friends.length]);
		Anton.setReportedplans(new int[friends.length][friends.length] );
		Anton.plan[Anton.id] = AntonPlan;
		
		(new Thread(Betty)).start();
		(new Thread(Carla)).start();
		(new Thread(Dave)).start();
		(new Thread(Anton)).start();
		
		//wait everyone made decision
		while ( this.Betty.finalPlan == 0 || this.Carla.finalPlan == 0 || this.Dave.finalPlan == 0) {
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Check if consensus success or not
		if ( this.Betty.finalPlan == this.Carla.finalPlan 
				&& this.Betty.finalPlan == this.Dave.finalPlan 
				&& this.Dave.finalPlan == this.Betty.finalPlan 
				&& this.Dave.finalPlan == this.Carla.finalPlan
				&& this.Carla.finalPlan == this.Betty.finalPlan 
				&& this.Carla.finalPlan == this.Dave.finalPlan )  {
				System.out.println("Consensus success");
		
					
				}
		
		else {
			System.out.println("Consensus fail");
		}
	}
}
