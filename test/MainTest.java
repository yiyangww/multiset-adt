

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testRunsWithoutError() {
        Main.main(new String[]{});
        assertTrue(true);
    }


    @Test
    public void testRunsWithoutFailingMessage() {

        // capture the output
        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        // IMPORTANT: Save the old System.out!
        PrintStream old = System.out;
        // Tell Java to use your special stream
        System.setOut(ps);

        Main.main(new String[]{});

        System.out.flush();
        System.setOut(old);
        // Show what happened
        String theOutput = baos.toString();
        assertFalse(theOutput.contains("adding the items failed!")
                                || theOutput.contains("removing items failed!"),
                 System.lineSeparator() + theOutput + System.lineSeparator() + "shouldn't contain" +
                                " 'adding the items failed!' or 'removing items failed!'" +
                                " if working correctly");
    }

}