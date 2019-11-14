import java.util.*;
public class ITMessenger
{

	public static void main(String[] args)
	{
		ITMessageMonitor monitor = new ITMessageMonitor();
		IObserver ob1 = new Developer();
		IObserver ob2 = new BusinessAnalyst();
		IObserver ob3 = new TeamLead();
		IObserver ob4 = new Tester();

		monitor.addEventListener(ob1);
		monitor.addEventListener(ob2);
		monitor.addEventListener(ob3);
		monitor.addEventListener(ob4);

		String[] messageIdentifiers = {"all", "dev", "ba", "tl", "tt"};
		String[] messages = {"A company event is happening on 4/23/19", "Begin development",
							"Analyze our profits", "Begin a new project", "Begin product testing"};
		for(int i = 0; i < 5; i++)
		{
			monitor.setEvent(new ITMessageEvent(messages[i], messageIdentifiers[i] ));
		}
	}
}

class ITMessageMonitor {
	private ArrayList<IObserver> observerList;
	ITMessageEvent event;

	public ITMessageMonitor()
	{
		observerList = new ArrayList<IObserver>();
	}

	public void addEventListener(IObserver observer)
	{
		observerList.add(observer);
	}
	public void removeEventListener(IObserver observer)
	{
		observerList.remove(observer);
	}
	public void setEvent(ITMessageEvent e)
	{
		this.event = e;
		informListeners();
	}
	public ITMessageEvent getEvent()
	{
		return event;
	}

	private void informListeners() {
		for (IObserver item: observerList)
		{
			item.reply(getEvent());
		}
	}
}

class ITMessageEvent{
	String message;
	String identifier;

	public ITMessageEvent(String message, String identifier)
	{
		this.identifier = identifier;
		this.message = message;
	}
	public void setModifier(String s){
		identifier = s;
	}
	public void setMessage(String n){
		message = n;
	}
}

interface IObserver
{
	void reply(ITMessageEvent e);
}

class Developer implements IObserver
{
	public void reply(ITMessageEvent e)
	{
		if(e.identifier.equalsIgnoreCase("all") || e.identifier.equalsIgnoreCase("dev"))
			System.out.println("Developer: " + e.message);
	}
}

class BusinessAnalyst implements IObserver
{
	public void reply(ITMessageEvent e)
	{
		if(e.identifier.equalsIgnoreCase("all") || e.identifier.equalsIgnoreCase("ba"))
			System.out.println("Business Analyst: " + e.message);
	}
}

class TeamLead implements IObserver
{
	public void reply(ITMessageEvent e)
	{
		if(e.identifier.equalsIgnoreCase("all") || e.identifier.equalsIgnoreCase("tl"))
			System.out.println("Team Lead: " + e.message);
	}
}

class Tester implements IObserver
{
	public void reply(ITMessageEvent e)
	{
		if(e.identifier.equalsIgnoreCase("all") || e.identifier.equalsIgnoreCase("tt"))
			System.out.println("Tester: " + e.message);
	}
}