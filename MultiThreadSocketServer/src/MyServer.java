import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class MyServer {

	//���屣������socket��arraylist����
	public static ArrayList<Socket> socketList =
			new ArrayList<Socket>();
			
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(30000);
		while(true){
			//���д������������һֱ�ȴ����˵�����
			Socket s = ss.accept();
			socketList.add(s);
			//ÿ���ͻ������Ӻ�����һ��ServerThread�߳�Ϊ�ÿͻ��˷���
			new Thread(new ServerThread(s)).start();;
		}
	}

}
