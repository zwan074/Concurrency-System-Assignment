package Assignment2_17272381;

public class Friends implements Runnable {

	String name; // friend's name
	int id; //friend's id
	int[] plan; //friend's plan
	int[][] reportedplans; //friend's reported plans
	int[] majorityPlan; // frisnd's majorityplan
	int finalPlan; // friend's final plan
	Friends[] friends; // friends' array
	String role; // role if loyal, byzantine failures or crash failures.
	Message message; //message pass object
	
	static final int VISIT = 1; 
	static final int NOTVISIT = -1;
	static final int CRASH = 0;
	
	public Friends (String name,int id, Friends[] friends , String role) {
		this.name = name;
		this.id = id;
		this.friends = friends;
		this.role = role;
		this.message = new Message();
		this.finalPlan = 0;
	}

	
	@Override
	public void run() {
		//System.out.println(this.name + " Start a new Algorithm");
		switch (role) {
			case "Loyal" : NoFailureRun(); break;
			case "Crash failures" : CrashFailureRun();break;
			case "Byzantine failures" :ByzantineFailureRun();break;
			
		}
		
		
		
	}
	
	private void NoFailureRun() {
		try {
			
			for (Friends friend : friends) {
				if (!friend.equals(this)) {
					message.send(this, friend , this.plan[id]);
				}	
			}
			
			// wait for a while to receive messages arrived 
			Thread.sleep(1000);

			for (Friends friend : friends) {
				if (!friend.equals(this)) {
					message.receive(this, friend );
				    
				}				
			}
			
			//Thread.sleep(1000);

			
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this)) {
						message.send(this, friend , otherfriend , this.plan[otherfriend.id]);
					}
						
				}
				
			}
			
			// wait for a while to receive messages arrived 
			Thread.sleep(1000);
			
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) ) {
						message.receive(this, friend , otherfriend);
					}
						
				}
				
			}
			
			//Thread.sleep(1000);
			decideFinalPlan () ;
			
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void CrashFailureRun() {
		try {
			for (Friends friend : friends) {
				// 50% chance to crash
				if (!friend.equals(this) && Math.random() > 0.5) {
					message.send(this, friend ,this.plan[id]);
					
				}
					
			}
			// wait for a while to receive messages arrived 
			Thread.sleep(1000);

			for (Friends friend : friends) {
				if (!friend.equals(this) ) {
					message.receive(this, friend );
				    
				}
					
					
			}
			
			//Thread.sleep(1000);

			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					// 50% chance to crash
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) && Math.random() > 0.5) {
						message.send(this, friend , otherfriend , this.plan[otherfriend.id]);
					}
						
				}
				
			}
			// wait for a while to receive messages arrived 
			Thread.sleep(1000);
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) ) {
						message.receive(this, friend , otherfriend);
					}
						
				}
				
			}
				
			//Thread.sleep(1000);
			decideFinalPlan () ;
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void ByzantineFailureRun() {
		
		int initialPlan = this.plan[id];
		int arbitraryPlan = this.plan[id] == VISIT ? NOTVISIT:VISIT;
		try {
			for (Friends friend : friends) {
				if (!friend.equals(this) ) {
					//50% chance to send arbitrary message
					message.send(this, friend , Math.random() > 0.5 ? initialPlan : arbitraryPlan);
				}
					
			}
			// wait for a while to receive messages arrived 
			Thread.sleep(1000);

			for (Friends friend : friends) {
				if (!friend.equals(this)) {
					message.receive(this, friend );
				    
				}
					
					
			}
			

			//Thread.sleep(1000);
			
			
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this)) {
						//50% chance to send arbitrary message
						int otherfriendInitialPlan = this.plan[otherfriend.id];
						int otherfriendArbitraryPlan = this.plan[otherfriend.id] == VISIT ? NOTVISIT:VISIT;
						message.send(this, friend , otherfriend , Math.random() > 0.5 ? otherfriendInitialPlan : otherfriendArbitraryPlan );
					}
						
				}
				
			}
			// wait for a while to receive messages arrived 
			Thread.sleep(1000);
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) ) {
						message.receive(this, friend , otherfriend);
					}
						
				}
				
			}
			//Thread.sleep(1000);
			decideFinalPlan () ;
			
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void decideFinalPlan () {
		for (Friends friend : friends) {
			if (!friend.equals(this)) {
				
				majorityPlan[friend.id] =  majority(friend);
				//System.out.println(this.name + " " + friend.name +" majorityPlan " + majorityPlan[friend.id]);
			}
				
		}
		majorityPlan[id]  =  plan[id];
		this.finalPlan = majority(majorityPlan);
		String visit = "";
		switch (this.finalPlan) {
			case VISIT :  visit = "VISIT"; break;
			case NOTVISIT : visit = "NOT VISIT";break;
		
		}
		System.out.println(this.name + " decide to " + visit);
		
	}
	
	private int majority(Friends friend) {
		
		int majoritPlan = 0;
		int crashesNumber = 0;
		reportedplans[friend.id][friend.id]= plan[friend.id];
		for ( int i = 0; i < friend.plan.length ; i++  ) {
			majoritPlan += reportedplans[friend.id][i];
			if (reportedplans[friend.id][i] == 0) {
				crashesNumber++;
			}
			//System.out.println(this.name + " " + friend.name + " " + i + " " + reportedplans[friend.id][i] );
		}
		// if reportedplans are all crash.
		if (crashesNumber == 3)  { 
			return CRASH;
		}
		else {
			return majoritPlan > 0 ? VISIT:NOTVISIT;
		}
		
		
	}
	
	private int majority(int[] majorityPlan) {
		
		int majoritPlan = 0;
		
		for ( int i = 0; i < majorityPlan.length ; i++  ) {
			majoritPlan += majorityPlan[i];
		}
		
		return majoritPlan > 0 ? VISIT:NOTVISIT;
		
		
		
	}
	public int[] getPlan() {
		return plan;
	}


	public void setPlan(int[] plan) {
		this.plan = plan;
	}


	public int[][] getReportedplans() {
		return reportedplans;
	}


	public void setReportedplans(int[][] reportedplans) {
		this.reportedplans = reportedplans;
	}


	public int[] getMajorityPlan() {
		return majorityPlan;
	}


	public void setMajorityPlan(int[] majorityPlan) {
		this.majorityPlan = majorityPlan;
	}





	

}
