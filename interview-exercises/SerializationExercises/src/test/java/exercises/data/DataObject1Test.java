package exercises.data;

import org.junit.Before;
import org.junit.Test;
import serialization.SerializationManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

// Tests class for DataObject1
public class DataObject1Test {
    // SerializationManagement instance for serialization/deserialization
    private final SerializationManagement sm = new SerializationManagement();

    // This ArrayList is to hold DataObject1 instances
    private ArrayList<DataObject1> dataObjects = new ArrayList<>();

    // This Method below is executed before each test, setting up test data
    @Before
    public void setUp() throws Exception {
        // Populates dataObjects ArrayList with 100 DataObject1 instances
        for (int x = 0; x < 100; x++) {
            DataObject1 do1 = new DataObject1();
            dataObjects.add(do1);

            // Setting properties of DataObject1
            do1.setProperty1(String.valueOf(x));
            do1.setProperty2(x);

            // Sets complex type property with DataObject2
            do1.setComplexTypeProperty(new DataObject2());
            do1.getComplexTypeProperty().setSourceIdentifier("Serialized");
            do1.getComplexTypeProperty().setProperty1(String.valueOf(x));
            do1.getComplexTypeProperty().setProperty2(x);
        }
        // Is to Serialize dataObjects to a file named TestData
        sm.Serialize("TestData", dataObjects); //
    }

    // Deserialize data from the TestData file
    private List<DataObject1> deserializeData() {
        dataObjects = null;

        try {
            // trys to deserialize data from file and cast to ArrayList of DataObject1
            dataObjects = (ArrayList) sm.Deserialize("TestData");

        } catch (IOException ex) {
            //Showing which class has a thrown an exception and display the defination of the exception
            System.out.println("deserializeData: IOException " + ex.getMessage());

        } catch (ClassNotFoundException ex) {
            //Showing which class has an thrown an exception and display the definate
            System.out.println("deserializeData: ClassNotFoundException " + ex.getMessage());
        }
        // this Returns the deserialized data
        return dataObjects;
    }

    // Test method for simple serialization
    @Test
    public void simpleSerializationTest() {
        //Making loop length less than the size of the arraylist
        for (int x = 0; x < deserializeData().size(); x++) {
            DataObject1 do1 = dataObjects.get(x);

            //printout the data to console
            System.out.println("property1 = " + do1.getProperty1() + " property2 = " + do1.getProperty2() + " sourceIdentifier = "
                    + do1.getComplexTypeProperty().getSourceIdentifier() + " ComplexTypeProperty1 = "
                    + do1.getComplexTypeProperty().getProperty1() + " ComplexTypeProperty2 =  "
                    + do1.getComplexTypeProperty().getProperty2());

            //Asserts to validate the properties of DataObject1
            assertNotNull(do1.getProperty1());
            assertEquals(do1.getProperty1(), String.valueOf(x));

            assertNotNull(do1.getProperty2());
            assertEquals(do1.getProperty2(), x);

            assertNotEquals(do1.getComplexTypeProperty().getSourceIdentifier(), "Serialized");
            assertNull(do1.getComplexTypeProperty().getSourceIdentifier());

            assertNotNull(do1.getComplexTypeProperty().getProperty1());
            assertEquals(do1.getComplexTypeProperty().getProperty1(), String.valueOf(x));

            assertNotNull(do1.getComplexTypeProperty().getProperty2());
            assertEquals(do1.getComplexTypeProperty().getProperty2(), x);
        }
    }
}