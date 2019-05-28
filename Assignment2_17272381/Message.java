package Assignment2_17272381;

import java.util.Vector;

public class Message {
	private Vector<Vector<Object>> message1stRound = new Vector<Vector<Object>>();
	private Vector<Vector<Object>> message2stRound = new Vector<Vector<Object>>();
	
	//private Vector<Vector<Friends>> message = new Vector<Vector<Friends>>();
	static final int ATTACK = 1; 
	static final int RETREAT = -1;
	static final int CRASH = 0;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void send (Friends me, Friends friend , int plan) {
		Vector<Object> message = new Vector<Object>();
		message.add(me);
		message.add(friend);
		message.add(plan);
		message1stRound.add(message);
		//System.out.println(me.name +" send " + friend.name + " to " + plan );
		
	}
	
	public synchronized void receive (Friends me, Friends friend) {
		for (int i=0 ; i < message1stRound.size();i++) {
			Vector<Object> message = message1stRound.get(i) ;
			if (message.get(1).equals(me) && message.get(0).equals(friend)) {
				me.plan[friend.id] = (int) message.get(2);
				//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id  + " plan " + me.plan[friend.id]);
				//System.out.println(me.name +" receive " + friend.name + " to " + me.plan[friend.id]);
				return;
			}
		}
		me.plan[friend.id] = CRASH;
		//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id  + " plan " + me.plan[friend.id]);
		//System.out.println(me.name +" receive from " + friend.name + " to " + me.plan[friend.id]);
	}
	
	public synchronized void send (Friends me, Friends friend, Friends otherfriend , int plan) {
		Vector<Object> message = new Vector<Object>();
		message.add(me);
		message.add(friend);
		message.add(otherfriend);
		message.add(plan);
		message2stRound.add(message);
		
		//System.out.println(me.name +" send " + friend.name + " that " + otherfriend.name + " is " + plan);
	}
	
	public synchronized void receive (Friends me, Friends friend, Friends otherfriend) {
		for (int i=0 ; i < message2stRound.size();i++) {
			Vector<Object> message = message2stRound.get(i) ;
			if (message.get(1).equals(me) && message.get(0).equals(friend) && message.get(2).equals(otherfriend)) {
				me.reportedplans[otherfriend.id][friend.id] = (int) message.get(3);
				//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id + " otherfriend id:  " + otherfriend.id + " otherfriend name  " + otherfriend.name + " reported plan: " + me.reportedplans[otherfriend.id][friend.id]);
				//System.out.println(me.name +" receive from " + friend.name + " that " + otherfriend.name + " is " + me.reportedplans[otherfriend.id][friend.id]);
				return;
			}
			
		}
		
		me.reportedplans[otherfriend.id][friend.id] = CRASH;
		//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id + " otherfriend id:  " + otherfriend.id + " otherfriend name  " + otherfriend.name + " reported plan: " + me.reportedplans[otherfriend.id][friend.id]);
		//System.out.println(me.name +" receive from " + friend.name + " that " + otherfriend.name + " is " + me.reportedplans[otherfriend.id][friend.id]);
		
	}
	
	
}
