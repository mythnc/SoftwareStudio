＊所有檔案皆以utf-8編碼。

目錄：

檔案與其用途
執行步驟
演算法（核心）
遇到的困難與解決方法


檔案與其用途：

java source code:
ChatClient.java - Client啓動檔。
ChatServer.java - Server啓動檔。
ChatThread.java - Thread，傳遞聊天訊息用。
ChatWindow.java - 聊天室框架。


執行步驟：

1. 開啟ChatServer，等待與Client的連線。

2. 開啟ChatClient，與Server做連線。

3. 連線成功後，在上方的JTextArea會顯示連線成功的訊息。
   利用下方的JTextField來聊天，按enter可以送出訊息。
   送出的訊息會顯示在本地與遠端的JTextArea。

4. 若其中一方關閉聊天室，JTextArea會顯示遠端關閉的訊息，
   而打字送出的訊息依然會顯示在本地端的JTextArea。


演算法（核心）：

聊天室框架：
利用JTextField與JTextArea實作。
利用JTextField與ActionEvent來改變JTextArea的顯示內容。

連線：
利用Socket實作。

資料傳遞：
利用Thread實作。
當JTextField產生Event時，
用DataOutputStream把JTextField的資料送給遠端，
本地端用DataInputStream來接收遠端送出的資料。
每當Thread接收到遠端傳送的資料時，
就去改變本地端JTextArea的內容。


遇到的困難與解決方法：
無。

