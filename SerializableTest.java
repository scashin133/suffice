import java.io.Serializable;
import java.util.Scanner;


public class SerializableTest implements Serializable {
	
	Scanner scanner = null;
	
	public SerializableTest(){
		scanner = new Scanner("test");
		
	}
	
	public void makeSerializable(){
		scanner = null;
	}

}
