import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ControlShared {
	public volatile ArrayList<Client> listaClientes;
	public volatile ArrayList<Integer> idsAtivos;
	public volatile boolean keepRunning;
	public final String localIP;
	
	private static volatile ControlShared instance;
	private static Object mutex = new Object();

	private ControlShared() throws UnknownHostException {
		 this.listaClientes = new ArrayList<Client>();
		 this.idsAtivos = new ArrayList<Integer>();
		 this.keepRunning = true;
		 this.localIP = InetAddress.getLocalHost().getHostAddress();
	}

	public static ControlShared getInstance() {
		ControlShared result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null){
					try {
						instance = result = new ControlShared();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return result;
	}
	
}
