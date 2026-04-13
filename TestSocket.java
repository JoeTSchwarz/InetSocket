import java.net.*;
import joesocket.InetSocket;
public class TestSocket {
   public static void main(String... argv) throws Exception {
     if (argv.length != 2) {
       System.out.println("java TestSocket HostID RetryNumber");
       System.exit(0);
     }
     new TestSocket(argv);
   }
   //
   public TestSocket(String... argv) throws Exception {
     InetSocket soc = new InetSocket(argv[0], Integer.parseInt(argv[1]));
     System.out.println("Client IPv6:"+joesocket.IPv6.getIPv6());
     System.out.println("Host_IP:    "+soc.getHostIP()+
                      "\nHostPort:   "+soc.getHostPort()+
                      "\n\nQ or q to terminate the communication"+
                      "\n\nX or x to exit the communication and server"); 
     byte[] bb = new byte[512];
     while (true) try {
       int n = System.in.read(bb);
       soc.getOutputStream().write(bb, 0, n);
       n = soc.getInputStream().read(bb);
       System.out.println(new String(bb,0,n));
       if (bb[n-1] == 'q' || bb[n-1] == 'Q' || bb[n-1] == 'x' || bb[n-1] == 'X') break;
     } catch (Exception ex) { }
     try {
        soc.close();
     } catch (Exception ex) { }
     System.exit(0);
  }
}