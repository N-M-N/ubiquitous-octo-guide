
import java.util.*;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testLogEntry();
	}
	public static void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {

    }
}
