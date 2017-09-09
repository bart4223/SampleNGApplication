package Uniwork.Test.Units;

import Uniwork.Base.NGObjectJSONDeserializer;
import Uniwork.Base.NGObjectJSONSerializer;
import Uniwork.Test.NGCustomTestUnit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NGJSONTestUnit extends NGCustomTestUnit {

    private class Person {

        protected String Name;
        
        public Person(String aName) {
            Name = aName;
        }

    }

    @BeforeClass
    public static void beforeSuite() {
        StartTestSuite();
    }

    @AfterClass
    public static void afterSuite() {
        FinishTestSuite();
    }

    @Test
    public void testJSONSerialize01() throws Exception {
        StartTest();
        Person p = new Person("Bart4223");
        NGObjectJSONSerializer serializer = new NGObjectJSONSerializer(p);
        assertEquals(true, serializer.serializeObject());
        FinishTest();
    }

    @Test
    public void testJSONSerialize02() throws Exception {
        StartTest();
        Person p = new Person("Bart4223");
        NGObjectJSONSerializer serializer = new NGObjectJSONSerializer(p);
        serializer.serializeObject();
        assertEquals("{\"Name\":\"Bart4223\"}", serializer.getJSON());
        FinishTest();
    }

    @Test
    public void testJSONDeserialize01() throws Exception {
        StartTest();
        NGObjectJSONDeserializer deserializer = new NGObjectJSONDeserializer(Person.class);
        deserializer.setJSON("{\"Name\":\"Bart4223\"}");
        assertEquals(true, deserializer.deserializeObject());
        FinishTest();
    }

    @Test
    public void testJSONDeserialize02() throws Exception {
        StartTest();
        NGObjectJSONDeserializer deserializer = new NGObjectJSONDeserializer(Person.class);
        deserializer.setJSON("\"Name\":\"Bart4223\"}");
        assertEquals(false, deserializer.deserializeObject());
        FinishTest();
    }

    @Test
    public void testJSONDeserialize03() throws Exception {
        StartTest();
        NGObjectJSONDeserializer deserializer = new NGObjectJSONDeserializer(Person.class);
        deserializer.setJSON("{\"Name\":\"Bart4223\"}");
        deserializer.deserializeObject();
        Person p = (Person)deserializer.getTarget();
        assertEquals("Bart4223", p.Name);
        FinishTest();
    }

}
