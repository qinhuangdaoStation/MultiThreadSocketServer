import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class ServerThread implements Runnable {

	//���嵱ǰ�߳��������socket
	Socket s = null;
	//���߳��������socket����Ӧ��������
	BufferedReader br = null;
	
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		
		//��ʼ����socket��Ӧ��������
		br = new BufferedReader(
				new InputStreamReader(s.getInputStream(),"utf-8"));
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			String content = null;
			//����ѭ�������ϴ�socket�ж�ȡ�ͻ��˷��͹���������
			while((content = readFromClient())!= null){
				//����socketList�е�ÿ��socket
				//��������������ÿ��socket����һ��
				for(Socket s : MyServer.socketList){
					OutputStream os = s.getOutputStream();
					os.write((content+"\n").getBytes("utf-8"));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//��ȡ�ͻ������ݵķ���
	private String readFromClient() {
		
		try{
			
			return br.readLine();
			
		}  //��������쳣��������socket��Ӧ�Ŀͻ����Ѿ��ر�
		catch(IOException e){  
			//ɾ����socket
			MyServer.socketList.remove(s);
		}
		return null;
	}
}
