import java.util.Random;

public class SentenceGenerator 
{
	
	public static void main(String[] args) {
		SentenceCreator s = new SentenceCreator();
		WordGenerator subject = new SubjectGenerator();
		WordGenerator verb = new VerbGenerator();
		WordGenerator object = new ObjectGenerator();
		WordGenerator adverb = new AdverbGenerator();
		s.add(adverb);
		s.add(object);
		s.add(verb);
		s.add(subject);
		s.completeTask();
	}
}

class SentenceCreator
{
	WordGenerator head;
	int length;
	Sentence s;
	
	public SentenceCreator()
	{
		head = null;
		length = 0;
		s = new Sentence();
	}
	
	public void add(WordGenerator w)
	{
		if(head == null) {
			head = w;
			length++;
		}
		else{
			w.setNext(head);
			head = w;
			length++;
		}
	}
	
	public void completeTask() 
	{	
		WordGenerator cursor = head;
		while(cursor != null){
			s = cursor.processRequest(s);
			cursor = cursor.getNext();
			
		}
		System.out.println(s.sentence);
	}
}

class SubjectGenerator extends WordGenerator
{
	private String[] subjects = {"A dog", "A cat", "A bird", "Dave", "Jim", "Joe", "The turtle", "The cow"};
	@Override
	Sentence processRequest(Sentence s) 
	{
		Random rand = new Random();
		int index = rand.nextInt(7);
		Sentence sent = s;
		sent.setSentence(subjects[index]);
		return sent;
	}
}

class VerbGenerator extends WordGenerator
{
	private String[] verbs = {"chases", "closes", "follows", "throws", "charges", "licks", "meets", "berates"};
	@Override
	Sentence processRequest(Sentence s) 
	{
		Random rand = new Random();
		int index = rand.nextInt(7);
		Sentence sent = s;
		sent.setSentence(verbs[index]);
		return sent;
	}
}

class ObjectGenerator extends WordGenerator
{
	private String[] objects = {"a dog", "a cat", "a bird", "Dave", "Jim", "the box", "the turtle", "the cow"};
	@Override
	Sentence processRequest(Sentence s) 
	{
		Random rand = new Random();
		int index = rand.nextInt(7);
		Sentence sent = s;
		sent.setSentence(objects[index]);
		return sent;
	}
}

class AdverbGenerator extends WordGenerator
{
	private String[] adverbs = {"amusingly", "swiftly", "quickly", "hastily", "slowly", "furiously", "strangely", "aggressively"};
	@Override
	Sentence processRequest(Sentence s) 
	{
		Random rand = new Random();
		int index = rand.nextInt(7);
		Sentence sent = s;
		sent.setSentence(adverbs[index]);
		return sent;
	}
}

abstract class WordGenerator
{
	private WordGenerator next;	
	
	public WordGenerator()
	{
		next = null;
	}
	
	public WordGenerator getNext() 
	{
		return next;
	}

	public void setNext(WordGenerator next) 
	{
		this.next = next;
	}
	
	abstract Sentence processRequest(Sentence s);
}

class Sentence
{
	String sentence = "";
	
	public void setSentence(String word)
	{
		sentence += word  + " ";
	}
}

