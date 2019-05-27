package Assignment2_17272381;

public class MessageByNote {
	
	public static final Space space = new Space();
	static final int ATTACK = 1; 
	static final int RETREAT = -1;
	static final int CRASH = 0;
	
	public MessageByNote() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void send (Friends me, Friends friend , int plan) {
		
		Note n = new Note(me.name + " " + friend.name, plan);
        space.postnote(n);
        System.out.println(me.name +" send " + friend.name + " to " + plan );
		
	}
	
	public synchronized void receive (Friends me, Friends friend) {
		Note n = space.removenote(new Note(friend.name + " " + me.name , null) );
		if (n.get(0) == ATTACK ||  n.get(0) == RETREAT  ) {
			me.plan[friend.id] = n.get(0) ;
		}
		else {
			me.plan[friend.id] = CRASH;
		}
		System.out.println(me.name +" receive " + friend.name + " to " + me.plan[friend.id]);
	}

	public synchronized void send (Friends me, Friends friend, Friends otherfriend , int plan) {
		Note n = new Note(me.name + " " + friend.name + " " + otherfriend.name, plan);
        space.postnote(n);
        System.out.println(me.name +" send " + friend.name + " that " + otherfriend.name + " is " + plan);
	}
	
	public synchronized void receive (Friends me, Friends friend, Friends otherfriend) {
		Note n = space.removenote(new Note(friend.name + " " + me.name + " " + otherfriend.name , null) );
		if (n.get(0) == ATTACK ||  n.get(0) == RETREAT  ) {
			me.reportedplans[otherfriend.id][friend.id] = n.get(0) ;
		}
		else {
			me.reportedplans[otherfriend.id][friend.id] = CRASH;
		}
		System.out.println(me.name +" receive " + friend.name + " that " + otherfriend.name + " is " + me.reportedplans[otherfriend.id][friend.id]);
		
	}
	
	
	
	
	
	
	

}
