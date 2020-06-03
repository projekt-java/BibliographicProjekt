package com.pk.writer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RtfWriterTest{
    private RtfWriter rtfWriter;

    public RtfWriterTest() {
        rtfWriter = new RtfWriter();
    }

//    When this test pass the code inside it will be redundant when writing next tests
//    @Test
//    void ItExists()
//    {
//        rtfWriter = new RtfWriter();
//    }
    @Test
    void IsImplementingAnyInterface()
    {
           //Arrange
           var interfaces = rtfWriter.getClass().getInterfaces();
           //Act
           //Assert
           assertNotEquals(0,interfaces.length);
    }
    @Test
    void IsImplementingBookWriterInterface()
    {
           //Arrange
           var interfaces = rtfWriter.getClass().getInterfaces();
           //Act
            var expected = "interface com.pk.writer.BooksWriter";
           //Assert
           assertEquals(expected,interfaces[0].toString());
    }
}
