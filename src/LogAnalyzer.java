import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.*; 
/**
 * WebLogParser.parseEntry - 
 * need to parse line to create instances
 * Split String into fields (could use many indexOf and 
 * substring methods, but cumbersome)
 * WebLogParser.parseEntry will do it for you.
 * @author NM
 *
 */

public class LogAnalyzer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LogAnalyzer LogA = new LogAnalyzer();
		LogA.readFile();
		LogA.printAll();
		LogA.countUniqueIPs();
		LogA.printAllHighThanNum(400);
		LogA.uniqueIPVisitsOnDay("Mar 17");
		LogA.countUniqueIPsInRange(200,299);
		LogA.mostNumberVisitsByIP(LogA.countVisitsPerIP()); 
		LogA.iPsMostVisits(LogA.countVisitsPerIP());
	}
	//private variable records 
    private ArrayList<LogEntry> records;
	
	// Initialize records to an empty ArrayList;
    public LogAnalyzer() {
        records = new ArrayList<LogEntry> ();   
    }
    
    public void readFile() {
       	// Create a FileResource for filename
    		FileResource fr = new FileResource();
		// Iterate over its lines for each one, 
    	for (String line : fr.lines()) {
    		// Use WebLogParser.parseEntry (method to convert the line of text to a logEntry field
    		LogEntry le = WebLogParser.parseEntry(line);
    		// Add the resulting LogEntry to records
    		records.add(le);
    	}
    }
  
	public int countUniqueIPs() {
		//uniqueIPs starts as an empty list
		ArrayList<String> uniqueIPs = new ArrayList<String>();
		// for each element le in records 
		for(LogEntry le : records) {
			// ipAddr is le's ipAddress
			String ipAddr = le.getIpAddress();
			// if ipAddr is not in uniqueIPs 
			if(!uniqueIPs.contains(ipAddr)) {
			// add ipAddr to uniqueIPs
					uniqueIPs.add(ipAddr);
			}
		}		
	    System.out.println("There are "+ uniqueIPs.size() + " unique IPs");
		// return the size of unique IPs
		return uniqueIPs.size();
	}
	//Date format for String someday "MMM DD" MMM is the first three letters of the 
	// month. 
	public void uniqueIPVisitsOnDay (String someday) {
		ArrayList<String> uniquedays = new ArrayList<String>();
    	for (LogEntry le : records) {
    		String day = le.getAccessTime().toString();
    		if(day.contains(someday)) {
    			if(!uniquedays.contains(le.getIpAddress())) {
    			uniquedays.add(le.getIpAddress()); 
    			}
    		}
    	}
    	System.out.println("There are " + uniquedays.size() + " unique IPs on "+ someday);
	}
	
	public int countUniqueIPsInRange(int low, int high) {
		int rangeIPs;
		ArrayList<String> uniquerange = new ArrayList<String>();
    	for(LogEntry le : records) {
    		int sc = le.getStatusCode(); 
    		if(sc <= high && sc >= low) {
    			if(!uniquerange.contains(le.getIpAddress())) {
    			uniquerange.add(le.getIpAddress()); 
    			}
    		}
    	}
    	rangeIPs = uniquerange.size();
    	System.out.println("There are "+ rangeIPs + " unique IPs in the range"+ low + " "+ high);
    	return rangeIPs;
	}
	
	public HashMap<String, Integer> countVisitsPerIP() {
		//make an empty HashMap<String, Integer> counts
		HashMap<String,Integer> counts = new HashMap<String, Integer>();
		// For each le in records
		for(LogEntry le : records) {
			//ip is le's ipAddress
			String ip = le.getIpAddress(); 
			//check if ip is in counts
			if(!counts.containsKey(ip)) {
				//If not: put ip in with a value of 1
				counts.put(ip,1);	
			}
			// else: get value update +1
			else {
				counts.put(ip, counts.get(ip)+1);
			}
		}
		System.out.println("There are " + counts.size() + " unique IPs");
		System.out.println(counts);
		
		return counts;
	}
	
	public int mostNumberVisitsByIP(HashMap<String,Integer> counts) {
		int mostNumVisits = 0; 
		for(String ip: counts.keySet()) {
			if(counts.get(ip) > mostNumVisits) {
				mostNumVisits = counts.get(ip);
			}
		}
		System.out.println("Most visits " + mostNumVisits);
		return mostNumVisits;
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String,Integer> counts) {
		int mostNumVisits = 0; 
		System.out.print("IPs with the most visits: ");
		for(String ip: counts.keySet()) {
			if(counts.get(ip) > mostNumVisits) {
				mostNumVisits = counts.get(ip);
			}
		}
		ArrayList<String> visits = new ArrayList<String>(); 
		for(String ip : counts.keySet()) {
			if(counts.get(ip) == mostNumVisits) {
				visits.add(ip); 
			}
		}
		for(String s: visits) {
			System.out.print(s + ", ");
		}
		return visits;
	}
	
	public HashMap<String, ArrayList<String>> iPsForDays () {
		HashMap<String,ArrayList<String>> daysIPs = new HashMap<String, ArrayList<String>> (); 
		for(LogEntry le: records) {
			String day = le.getAccessTime().toString();
			String shortDay = day.substring(4, 9); 
			if(!daysIPs.containsKey(shortDay)) {
				ArrayList<String> list = new ArrayList<String> (); 
				list.add(le.getIpAddress());
				daysIPs.put(shortDay, list); 
			}
			else {
				daysIPs.get(list.add(le.getIpAddress()));
			}
		}
		return daysIPs; 
	}
	
    public void printAllHighThanNum(int num) {
    	ArrayList<Integer> allHigh = new ArrayList<Integer>();
    	for(LogEntry le : records) {
    		int sc = le.getStatusCode(); 
    		if(sc > num) {
    			if(!allHigh.contains(le.getStatusCode())) {
    			allHigh.add(le.getStatusCode());
    			System.out.println(le.getStatusCode());
    			}
    		}
    	}
    	System.out.println(allHigh.size() + "Number of statusCode higher than " + num);
    }
    public void printAll () {
	/*Provided printAll() to help you test your code. 
	 * -Implicitly uses .toString() in LogEntry
	 */
    	for (LogEntry le : records) {
    		System.out.println(le);
    	}
    }

}

