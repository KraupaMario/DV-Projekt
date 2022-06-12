package test;
import java.net.InetAddress;
import java.net.UnknownHostException; 


public class test {



	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		try {  
		      InetAddress ia = InetAddress.getLocalHost();  
		      String str = ia.getHostAddress();  
		      System.out.println(str);  
		    } catch (Exception e) {  
		      e.printStackTrace();  
		    }
}
}
