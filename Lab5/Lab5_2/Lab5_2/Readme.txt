＊所有檔案皆以utf-8編碼。

目錄：

檔案
執行步驟
演算法（核心）
遇到的困難與解決方法


檔案（只計算java檔）：
GameCell.java - 用來製作每一小格所需要的長方形。
GameThread.java - 傳遞local與remote host間的資料。
GameWindow.java - 遊戲視窗，放置GameCell與WinnerMessage。 
LaunchClient.java - 啟動Client端。
LaunchServer.java - 啟動Server端。
WinnerMessage.java - 用來製作「遊戲勝利」畫面。


執行步驟：

1. 開啟Server端，等待連線。
   （等待與連線訊息在command line顯示）

2. 開啟Client端，與Server端進行連線。
   （連線訊息在command line顯示）

3. 若連線成功，分別開啟兩端各自的遊戲視窗，並隨機產生一格為黑底白框。

4. 使用者進行遊玩……點擊任意一個視窗的格子，皆會改變自己與遠端的格子。
　 （改變方式同Lab4）

5. 若成功讓遠端與本地端的視窗所有格子變為黑色，遊戲結束，顯示獲勝訊息。


演算法（核心）：

程式幾乎與Lab4無異，只多了Thread與Socket。

Socket的實作：
Server端利用Socket與ServerSocket實作。
port設為13579。
用accept()監聽連線。

Client端用Socket與Server做連線。

連線成功後，分別在Server與Client製作各自的
DataInputStream與DateOutputStream。


Thread的實作：
本地與遠端都建立一個Thread，用來接收與傳遞資料。
都在一個無窮回圈裡等待資料。

當本地的格子被滑鼠點擊時，首先計算該格與其上下左右的格子是否存在。
若存在，則改變格子的顏色，並把該格子的座標(x, y)，傳給遠端。
（利用兩個output.writeInt()實作）
遠端接收到資料後，也去改變(x, y)的顏色。
因為在本地端已經判斷該格存在，所以能夠在遠端直接做改變。

當本地端所有格子皆為黑色時，就傳遞資料告訴遠端「我全黑了」
（用output.writeBoolean(true)實作）
遠端接收到獲勝訊息之後，會判斷自己的格子是否也是全黑。
若是全黑，就回傳訊息告訴本地端「我全黑了」（實作方式相同），
並跳出無窮迴圈和顯示獲勝訊息。

本地端接受到資料後，也會做相同判斷與傳遞訊息給遠端，
但遠端已不處理所接收到的訊息，如此兩端不會一直傳遞相同資料給對方。
本地端傳遞訊息後，亦跳出無窮迴圈和顯示獲勝訊息。

所以每次在傳遞資料時，會傳
兩個output.writeInt()與一個output.writeBoolean()。
單純傳座標時，writeBoolean傳false；
單純傳全黑訊息時，writeInt皆傳-1。

若在遊戲尚未獲勝時，就關閉Server或Client的其中一端，
那縱使之後繼續玩到獲勝，也不會顯示獲勝訊息。


遇到的困難與解決方法：
無

