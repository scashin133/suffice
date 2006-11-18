import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SerializableTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerializableTest test = new SerializableTest();
		
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("t.tmp");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        try {
			oos.writeObject(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
