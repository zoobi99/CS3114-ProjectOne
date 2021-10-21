import student.TestCase;
import java.io.IOException;
import java.util.ArrayList;
//tests input parser
/**
 * 
 * @author zoobi (Zoobi@vt.edu)
 * @author 
 * @version
 * This class tests the input parser class.
 *
 */
public class InputParserTest extends TestCase {

    private InputParser parser;

    /**
     * This is the setup method for the 
     * test class.
     */
    public void setUp() throws IOException {
        parser = new InputParser("input_1.txt");
       
    }

    /**
     * This method ensures that the parser correctly
     * parses through the input txt file.
     */
    public void testParser() {
        // System.out.print(parser)
        ArrayList<CovidUpdate> list = parser.getMasterList();
        assertNotNull(parser.getMasterList());
  
    }
    
  
    

}
