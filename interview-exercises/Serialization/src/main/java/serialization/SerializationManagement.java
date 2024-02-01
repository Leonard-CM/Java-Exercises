package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Administrator
 * This class handles serialization and deserialization of objects.
 */
public class SerializationManagement {

   //path of the file
    static final String FILE_FORMAT = System.getProperty("user.dir") + "/target/\\%1$s.dat"; //

    public void Serialize(String dataIdentifier, Object dataObject) throws FileNotFoundException, IOException {
        try {
            //opens a file for writting
            FileOutputStream file = new FileOutputStream(String.format(FILE_FORMAT, dataIdentifier));
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Writes the object to the file
            out.writeObject(dataObject);

        } catch (FileNotFoundException ex) {
            //Showing which class has an thrown an exception and display the definition of the exception
            System.out.println("Serialize: FileNotFoundException " + ex.getMessage());
        } catch (IOException ex){
            //Shows which class has a thrown an exception and display the definition of the exception
            System.out.println("Serialize: IOException " + ex.getMessage());
        }
    }

    public Object Deserialize(String dataIdentifier) throws IOException, ClassNotFoundException
    {
        ObjectInputStream in = null;
        try {
            // Opens a file for reading
            FileInputStream file = new FileInputStream(String.format(FILE_FORMAT, dataIdentifier));
            in = new ObjectInputStream(file);

        } catch (FileNotFoundException ex) {
            //Shows which class has a thrown an exception and display the definition of the exception
            System.out.println("Deserialize: FileNotFoundException " + ex.getMessage());
        } catch (IOException ex) {
            //Shows which class has a thrown an exception and display the definition of the exception
            System.out.println("Deserialize: IOException " + ex.getMessage());
        }
        // Reads and returns the deserialized object
        return in.readObject();
    }
}

