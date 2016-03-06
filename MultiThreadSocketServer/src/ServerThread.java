import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class ServerThread implements Runnable {

	//定义当前线程所处理的socket
	Socket s = null;
	//该线程所处理的socket所对应的输入流
	BufferedReader br = null;
	
	public ServerThread(Socket s) throws IOException{
		this.s = s;
		
		//初始化该socket对应的输入流
		br = new BufferedReader(
				new InputStreamReader(s.getInputStream(),"utf-8"));
	}
	public void run() {
		// TODO Auto-generated method stub
		try{
			String content = null;
			//采用循环，不断从socket中读取客户端发送过来的数据
			while((content = readFromClient())!= null){
				//便利socketList中的每个socket
				//将读到的内容向每个socket发送一次
				for(Socket s : MyServer.socketList){
					OutputStream os = s.getOutputStream();
					os.write((content+"\n").getBytes("utf-8"));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	//读取客户端数据的方法
	private String readFromClient() {
		
		try{
			
			return br.readLine();
			
		}  //如果出现异常，表明该socket对应的客户端已经关闭
		catch(IOException e){  
			//删除该socket
			MyServer.socketList.remove(s);
		}
		return null;
	}
}
