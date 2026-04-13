import java.net.*;
import joesocket.InetServerSocket;
public class TestServer {
  public static void main(String... argv) throws Exception {
    if (argv.length != 2) {
      System.out.println("java TestServer HostID PortNumber");
      System.exit(0);
    }
    new TestServer(argv);
  }
  //
  private InetServerSocket svr;
  public TestServer(String... argv) throws Exception {
    svr = new InetServerSocket(argv[0], Integer.parseInt(argv[1]));
    System.out.println("MyServer:"+argv[0]+"@"+argv[1]+" is started..."+
                       "Press CTRL+C to exit");
    try {
      while (true) {
        (new Test(svr.accept())).start();
      }
    } catch (Exception ex) { }
  }
  //
  private class Test extends Thread {
    public Test(Socket soc) {
      this.soc = soc;
    }
    private Socket soc;
    public void run() {
      byte[] bb = new byte[512];
      byte[] BB = new byte[512];
      while (bb[0] != 'q' && bb[0] != 'Q' && bb[0] != 'x' && bb[0] != 'X')  try {
        int n = soc.getInputStream().read(bb);
        for (int i = 0, j = n-1; i < n; ++i, --j) BB[i] = bb[j];
        System.out.println(new String(bb,0,n));
        soc.getOutputStream().write(BB, 0, n);
       } catch (Exception ex) { }
       System.out.println("Client quitted.");
       try {
        soc.close();
        if (bb[0] == 'x' || bb[0] == 'X') svr.close();
       } catch (Exception ex) { }
    }
  }
}