# MultiThreadSocketServer
Socket编程——服务器端
  
  服务器端只负责接收客户端socket的连接请求，每当客户端socket连接到该serversocket之后，
  程序将对应socket加入socketlist集合中保存，并为该socket启动一条线程，该线程负责处理
  该socket所有的通信任务。
