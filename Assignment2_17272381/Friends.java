package Assignment2_17272381;

public class Friends implements Runnable {

	String name;
	int id;
	int[] plan;
	int[][] reportedplans;
	int[] majorityPlan;
	int finalPlan;
	Friends[] friends;
	String role;
	MessageByNote message;
	//Message message;
	static final int ATTACK = 1; 
	static final int RETREAT = -1;
	static final int CRASH = 0;
	
	public Friends (String name,int id, Friends[] friends , String role,MessageByNote message) {
		this.name = name;
		this.id = id;
		this.friends = friends;
		this.role = role;
		this.message = message;
		this.finalPlan = 0;
	}

	
	@Override
	public void run() {
		System.out.println(this.name + " Start a new Algorithm");
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

			Thread.sleep(1000);

			for (Friends friend : friends) {
				if (!friend.equals(this)) {
					message.receive(this, friend );
				    
				}				
			}
			
			Thread.sleep(1000);

			
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this)) {
						message.send(this, friend , otherfriend , this.plan[otherfriend.id]);
					}
						
				}
				
			}
			
			Thread.sleep(1000);
			
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) ) {
						message.receive(this, friend , otherfriend);
					}
						
				}
				
			}
			
			Thread.sleep(1000);
			decideFinalPlan () ;
			
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void CrashFailureRun() {
		try {
			for (Friends friend : friends) {
				if (!friend.equals(this) && Math.random() > 0.5) {
					message.send(this, friend ,this.plan[id]);
					
				}
					
			}
			
			Thread.sleep(1000);

			for (Friends friend : friends) {
				if (!friend.equals(this) ) {
					message.receive(this, friend );
				    
				}
					
					
			}
			
			Thread.sleep(1000);

			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) && Math.random() > 0.5) {
						message.send(this, friend , otherfriend , this.plan[otherfriend.id]);
					}
						
				}
				
			}

			Thread.sleep(1000);
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) ) {
						message.receive(this, friend , otherfriend);
					}
						
				}
				
			}
				
			Thread.sleep(1000);
			decideFinalPlan () ;
			
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void ByzantineFailureRun() {
		
		int initialPlan = plan[id];
		int arbitraryPlan = plan[id] == ATTACK ? RETREAT : ATTACK;
		try {
			for (Friends friend : friends) {
				if (!friend.equals(this) ) {
					plan[id] = Math.random() > 0.5 ? initialPlan : arbitraryPlan;
					message.send(this, friend ,this.plan[id]);
				}
					
			}
			
			Thread.sleep(1000);

			for (Friends friend : friends) {
				if (!friend.equals(this)) {
					message.receive(this, friend );
				    
				}
					
					
			}
			

			Thread.sleep(1000);
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this)) {
						plan[id] = Math.random() > 0.5 ? initialPlan : arbitraryPlan;
						message.send(this, friend , otherfriend , this.plan[otherfriend.id]);
					}
						
				}
				
			}

			Thread.sleep(1000);
			for (Friends friend : friends) {
				for (Friends otherfriend : friends) {
					if (!friend.equals(this) && !friend.equals(otherfriend) && !otherfriend.equals(this) ) {
						message.receive(this, friend , otherfriend);
					}
						
				}
				
			}
			Thread.sleep(1000);
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
		System.out.println(this.name + " decide to " + finalPlan);
		
	}
	
	private int majority(Friends friend) {
		
		int majoritPlan = 0;
		majoritPlan += plan[friend.id];
		for ( int i = 0; i < friend.plan.length ; i++  ) {
			majoritPlan += reportedplans[friend.id][i];
			//System.out.println(this.name + " " + friend.name + " " + i + " " + reportedplans[friend.id][i] );
		}
		
		return majoritPlan > 0 ? ATTACK:RETREAT;
		
	}
	
	private int majority(int[] majorityPlan) {
		
		int majoritPlan = 0;
		
		for ( int i = 0; i < majorityPlan.length ; i++  ) {
			majoritPlan += majorityPlan[i];
		}
		
		return majoritPlan > 0 ? ATTACK:RETREAT;
		
		
		
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
