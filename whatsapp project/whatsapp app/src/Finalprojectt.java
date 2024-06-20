
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

interface Communicator {
	public void receiveMessage(int srcId, String message);

	public void saveMessage(String message);

	int getid();
}

class Car implements Communicator {
	private int id;
	Center center = new Center();
	Random random = new Random();
	private String[][] quotes = { { "programming is beautiful", "love what you are doing" },
			{ "every single day is a vacation for me", "(because) I love what I am doing" } };

	public Car() {
		this.id = IDgenerator.generatorid();
	}

	@Override
	public void receiveMessage(int srcId, String message) {
		String send = "Car " + getid() + " got message From: " + srcId + " the message is " + message;
		System.out.println(send);
		saveMessage(send);
		center.saveMessage(send);

	}

	@Override
	public void saveMessage(String message) {
		try {
			FileWriter fw = new FileWriter("Car " + getid() + ".txt", true);
			fw.write(message + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getquote(String[][] quotes) {
		int i = random.nextInt(2);
		int j = random.nextInt(2);
		return quotes[i][j];
	}

	@Override
	public int getid() {
		return id;
	}
}

class Cup implements Communicator {
	private int id;
	Center center = new Center();
	Random random = new Random();

	private String[][] quotes = { { "programming is beautiful", "love what you are doing" },
			{ "every single day is a vacation for me", "(because) I love what I am doing" } };

	public Cup() {
		this.id = IDgenerator.generatorid();
	}

	@Override
	public void receiveMessage(int srcId, String message) {
		String send = "Cup " + getid() + " got message From: " + srcId + " the message is " + message;
		System.out.println(send);
		saveMessage(send);
		center.saveMessage(send);

	}

	@Override
	public void saveMessage(String message) {
		try {
			FileWriter fw = new FileWriter("Cup " + getid() + ".txt", true);
			fw.write(message + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getquote(String[][] quotes) {
		int i = random.nextInt(2);
		int j = random.nextInt(2);
		return quotes[i][j];
	}

	@Override
	public int getid() {
		return id;
	}
}

class Cat implements Communicator {

	private int id;
	Center center = new Center();
	Random random = new Random();

	private String[][] quotes = { { "programming is beautiful", "love what you are doing" },
			{ "every single day is a vacation for me", "(because) I love what I am doing" } };

	public Cat() {
		this.id = IDgenerator.generatorid();
	}

	@Override
	public void receiveMessage(int srcId, String message) {
		String send = "Cat " + getid() + " got message From: " + srcId + " the message is " + message;
		System.out.println(send);
		saveMessage(send);
		center.saveMessage(send);
	}

	@Override
	public void saveMessage(String message) {
		try {
			FileWriter fw = new FileWriter("Cat " + getid() + ".txt", true);
			fw.write(message + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getquote(String[][] quotes) {
		int i = random.nextInt(2);
		int j = random.nextInt(2);
		return quotes[i][j];
	}

	@Override
	public int getid() {
		return id;
	}
}

class Cloud implements Communicator {
	private int id;
	Center center = new Center();
	Random random = new Random();
	private String[][] quotes = { { "programming is beautiful", "love what you are doing" },
			{ "every single day is a vacation for me", "(because) I love what I am doing" } };

	public Cloud() {
		this.id = IDgenerator.generatorid();
	}

	@Override
	public void receiveMessage(int srcId, String message) {
		String send = "Cloud " + getid() + " got message From: " + srcId + " the message is " + message;
		System.out.println(send);
		saveMessage(send);
		center.saveMessage(send);

	}

	@Override
	public void saveMessage(String message) {
		try {
			FileWriter fw = new FileWriter("Cloud " + getid() + ".txt", true);
			fw.write(message + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getquote(String[][] quotes) {
		int i = random.nextInt(2);
		int j = random.nextInt(2);
		return quotes[i][j];
	}

	@Override
	public int getid() {
		return id;
	}
}

class Center {
	private ArrayList<Communicator> communicators = new ArrayList<>();
	private ArrayList<Integer> destinationid = new ArrayList<>();

	public void sendMessage(String message) {

		String[] wholeMessage = message.split(":");
		String sourceMessage = wholeMessage[0];
		int source = Integer.parseInt(sourceMessage);
		String lastMassage = wholeMessage[2];

		if (wholeMessage[1].equals("all")) {

			for (int k = 0; k < communicators.size(); k++) {
				Communicator communicator = communicators.get(k);

				if (communicator.getid() != source) {
					communicator.receiveMessage(source, lastMassage);
				}
			}
		} else {
			String destIds = wholeMessage[1];

			String[] destArray = destIds.split(",");

			for (int i = 0; i < destArray.length; i++) {
				int number = Integer.parseInt(destArray[i]);
				destinationid.add(number);
			}

			for (int k = 0; k < communicators.size(); k++) {
				Communicator communicator = communicators.get(k);

				if (communicator.getid() != source) {
					communicator.receiveMessage(source, lastMassage);
				}
			}
		}
	}

	public void register(Communicator communicator) {
		communicators.add(communicator);
		System.out.println(communicator.getid() + " is created");
		notifyAllcomms(communicator.getid() + " is created");
	}

	public void notifyAllcomms(String message) {
		for (int i = 0; i < communicators.size() - 1; i++) {
			Communicator communicator = communicators.get(i);
			communicator.receiveMessage(communicator.getid(), message);
		}

	}

	public void saveMessage(String message) {
		try {
			FileWriter fw = new FileWriter("log.txt", true);
			fw.write(message + "\n");
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class IDgenerator {
	private static int id = 1;

	public static int generatorid() {
		return id++;
	}
}

public class Finalprojectt {

	public static void main(String[] args) {
		Center cen = new Center();
		Cat cat = new Cat();
		Cloud cloud = new Cloud();
		Car car = new Car();
		Cup cup = new Cup();
		cen.register(cat);
		cen.register(cloud);
		cen.register(car);
		cen.register(cup);
		cen.sendMessage("1:2,3:hello");
	}
	
}

/*CONSOLE:
1 is created
2 is created
Cat 1 got message From: 1 the message is 2 is created
3 is created
Cat 1 got message From: 1 the message is 3 is created
Cloud 2 got message From: 2 the message is 3 is created
4 is created
Cat 1 got message From: 1 the message is 4 is created
Cloud 2 got message From: 2 the message is 4 is created
Car 3 got message From: 3 the message is 4 is created
Cloud 2 got message From: 1 the message is hello
Car 3 got message From: 1 the message is hello
Cup 4 got message From: 1 the message is hello*/



/*Interfaces: The code defines an interface called Communicator, which declares several methods: receiveMessage, saveMessage, and getid. This interface is implemented by various classes in the code.

Classes: There are several classes defined in the code:

Car: Represents a car object that implements the Communicator interface. It has methods for receiving and saving messages.
Cup: Represents a cup object that also implements the Communicator interface. It has similar methods for receiving and saving messages.
Cat:Represents a cat object that also implements the Communicator interface. It has similar methods for receiving and saving messages.
Cloud: Represents a cloud object that also implements the Communicator interface. It has similar methods for receiving and saving messages.
Center class: The Center class acts as a central communication center. It maintains a list of Communicator objects and provides methods for sending messages to these objects. It also has methods for registering new communicators and saving messages.

IDgenerator class: This class is responsible for generating unique IDs for the communicators (car, cup, cat, cloud).

Finalprojectt class: This is the main class that contains the main method. It creates instances of Center, Cat, Cloud, Car, and Cup. It registers these objects with the Center and then sends a message using the sendMessage method of the Center object.
In the code, I used an ArrayList to store the different communicators (like cars, cups, cats, and clouds) because it allows for flexible management of the objects by easily adding or removing them. Additionally, I employed 2D string arrays to hold pairs of quotes, which enabled me to randomly select quotes for generating messages, adding variability to the communication system.
 the code sets up a communication system where different objects (car, cup, cat, cloud) can send and receive messages through the central Center object. Each object has its own ID and can save received messages to separate files.*/
