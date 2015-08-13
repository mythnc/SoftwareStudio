＊所有的檔案皆以UTF-8編碼。


目錄：
檔案
執行步驟
演算法
遇到的困難與解決方法


檔案（不計算class檔）：
GameCell.java - 用來製作每一小格所需要的長方形。
GameWindow.java - 遊戲視窗，放置GameCell與WinnerMessage。
LaunchGame.java - 啟動檔。
WinnerMessage.java - 用來製作「遊戲勝利」畫面。


執行步驟：

1. 程式產生4 * 5格的GameCell，每一格都是一個GameCell。

2. 隨機在4 * 5格中，選定一格為黑色，其餘為白色。

3. 每當使用者點選任意格子時，該格子與其上下左右的格子（如果存在），
   皆會改變顏色（黑變白、白變黑）。

4. 當所有格子變為黑色時，遊戲結束，使用者勝利。

5. 使用者勝利後，讓視窗顯示獲勝訊息：You Win!!!


演算法（核心部份）：

利用GridLayout的特性與setBackground()、setColor()、drawRect()來實作格子：
黑色格子的背景為黑色，前景為白色，白色格子則相反。
再利用getWidth()與getHeight()來畫長方形。
（因為GridLayout會自動調整其component的大小，
所以能放心的使用getWidth()與getHeight()）

變色判定：
用二維陣列來存放格子。
每當使用者用滑鼠點擊某個格子時，
該格子與其東西南北的格子，皆做顏色改變。
利用兩個一維陣列表示要作顏色改變的格子：
movex[] = {0, 0, 1, -1, 0};
movey[] = {0, 1, 0, 0, -1};
如此，(x, y)的格子，加上(0, 0)、(0, 1)、(1, 0)、(-1, 0)、(0, -1)，
就表示了自己、北、東、南、西等五個格子。

顏色紀錄：
利用一個相對應的二維陣列做顏色紀錄。
每當使用者做滑鼠點擊，格子的顏色改變後，
就檢查陣列中是否全部為黑色，若為黑色，表示使用者獲勝，遊戲結束。


遇到的困難與解決方法：
Q: 不知道如何刪除與更新JFrame中的component。
A: google。得知removeAll()是不行的。要用getContentPane().removeAll()。
   並利用validate()更新顯示。

