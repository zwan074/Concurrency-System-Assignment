package Assignment2_17272381;

import java.util.concurrent.ConcurrentHashMap;

public class Message {
	private ConcurrentHashMap<String,Integer> message1stRound = new ConcurrentHashMap<>();
	private ConcurrentHashMap<String,Integer> message2ndRound = new ConcurrentHashMap<>();

	static final int CRASH = 0;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void send (Friends me, Friends friend , int plan) {
		friend.message.message1stRound.put(me.name + " " + friend.name, plan);
		System.out.println(me.name +" send " + friend.name + " to " + plan );
	}
	
	public synchronized void receive (Friends me, Friends friend) {
		
		if (message1stRound.containsKey(friend.name + " " + me.name)) {
			me.plan[friend.id] = message1stRound.get(friend.name + " " + me.name);
			System.out.println(me.name +" receive from " + friend.name + " to " + me.plan[friend.id]);
		}
		else {
			me.plan[friend.id] = CRASH;
			//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id  + " plan " + me.plan[friend.id]);
			System.out.println(me.name +" receive from " + friend.name + " to " + me.plan[friend.id]);
			
		}
		
	}
	
	public synchronized void send (Friends me, Friends friend, Friends otherfriend , int plan) {
		friend.message.message2ndRound.put(me.name +" " + friend.name + " " + otherfriend.name, plan);
		System.out.println(me.name +" send " + friend.name + " that " + otherfriend.name + " is " + plan);
	}
	
	public synchronized void receive (Friends me, Friends friend, Friends otherfriend) {
		if (message2ndRound.containsKey(friend.name + " " + me.name + " " + otherfriend.name)) {
			me.reportedplans[otherfriend.id][friend.id] = message2ndRound.get(friend.name + " " + me.name + " " + otherfriend.name);
			//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id + " otherfriend id:  " + otherfriend.id + " otherfriend name  " + otherfriend.name + " reported plan: " + me.reportedplans[otherfriend.id][friend.id]);
			System.out.println(me.name +" receive from " + friend.name + " that " + otherfriend.name + " is " + me.reportedplans[otherfriend.id][friend.id]);
			
		}
		else {
			me.reportedplans[otherfriend.id][friend.id] = CRASH;
			//System.out.println(me.name +" friend name: " + friend.name + " friend id: " + friend.id + " otherfriend id:  " + otherfriend.id + " otherfriend name  " + otherfriend.name + " reported plan: " + me.reportedplans[otherfriend.id][friend.id]);
			System.out.println(me.name +" receive from " + friend.name + " that " + otherfriend.name + " is " + me.reportedplans[otherfriend.id][friend.id]);
			
		}
		
		
		
	}
	
	
}
