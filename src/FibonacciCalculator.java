import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class FibonacciCalculator implements Runnable {
	
	 private int No;
	    public int answer;
	 
	    public FibonacciCalculator(int _No) {
	        this.No = _No;
	    }
	 
	    public void run() {
	        if (No == 0){ 
	            answer = 0;
	        }else if( No == 1 || No == 2 ) {
	            answer = 1;
	        }else {
	            try {
	                /*
	                 * Below we are invoking 2 threads to compute separate values
	                 * This will for a tree structure
	                 */
	            	//ExecutorService executor = Executors.newFixedThreadPool(30);
	            	
	            	FibonacciCalculator fc1 = new FibonacciCalculator(No-1);
	            	FibonacciCalculator fc2 = new FibonacciCalculator(No-2);
	            	Thread thread1 = new Thread(fc1);
	            	Thread thread2 = new Thread(fc2);
	            	//executor.execute(thread1);
	            	//executor.execute(thread2);
	            	thread1.start();
	            	thread2.start();
	                thread1.join();
	                thread2.join();
	                answer = fc1.answer + fc2.answer;
	                
	            }catch(Exception ex) { 
	                ex.printStackTrace();
	            }
	        }
	    }
	 
	    public static void main(String[] args){
	        try {
	        	for(int index = 0; index <10 ; index++){
	        		
	            int inputNum = index;
	            FibonacciCalculator FibbonacciCalc = new FibonacciCalculator(inputNum);
	            int nThread = 30;
	           // ExecutorService executor1 = Executors.newFixedThreadPool(nThread);
	            Thread mainThread = new Thread(FibbonacciCalc);
	            mainThread.start();
	            mainThread.join();
	          // executor1.execute(mainThread);
	          //  executor1.shutdown();
	            System.out.println("Fibonacci number at "+inputNum+" position is:"+FibbonacciCalc.answer);
	        	}
	        }
	        catch(Exception ex1) { 
                ex1.printStackTrace();
            }
	    }
}