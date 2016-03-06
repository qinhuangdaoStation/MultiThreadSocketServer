import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class MyServer {

	//定义保存所有socket的arraylist集合
	public static ArrayList<Socket> socketList =
			new ArrayList<Socket>();
			
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(30000);
		while(true){
			//此行代码会阻塞，将一直等待别人的连接
			Socket s = ss.accept();
			socketList.add(s);
			//每当客户端连接后启动一条ServerThread线程为该客户端服务
			new Thread(new ServerThread(s)).start();;
		}
	}

}
