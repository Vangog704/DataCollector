package Observers;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.Session;

import DataEntitys.UserData;

public class ResponseObserver extends Thread{
	
	//Declaration vars
	private static ResponseObserver obj;
	
	private Deque<UserData> dataDeque;
	private Set<Session> Subscribers;

	//Constructors
	private ResponseObserver()
	{
		dataDeque = new ArrayDeque<UserData>();
		Subscribers = new HashSet<Session>();
	}
	
	//Methods
	public static ResponseObserver getInstanse()
	{
		if(obj == null)
		{
			obj = new ResponseObserver();
			obj.setDaemon(true);
			obj.start();
		}
		return obj;
	}
	
	@Override
	public void run() {
		while(true)
		{	
			if(!dequeIsEmpty())
				BroadCast(getResponse().toJSON().toString());
			
			try { Thread.sleep(5000); } 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void BroadCast(String message)
	{
		Iterator<Session> it = Subscribers.iterator();
		while(it.hasNext())
		{
			try {
				it.next().getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Getters & Setters------------------------
	public synchronized boolean dequeIsEmpty()
	{
		return dataDeque.isEmpty();
	}
	
	public synchronized void addSession(Session usersession)
	{
		Subscribers.add(usersession);
	}
	
	public synchronized void removeSession(Session usersession)
	{
		Subscribers.remove(usersession);
	}
	
	public synchronized void addResponse(UserData data)
	{
		dataDeque.push(data);
	}
	
	public synchronized UserData getResponse()
	{
		return dataDeque.pop();
	}
	//------------------------------------------
}
