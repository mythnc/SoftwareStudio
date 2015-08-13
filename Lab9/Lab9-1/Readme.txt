＊所有檔案皆以utf-8編碼。

目錄：

資料夾與其用途
檔案與其用途
執行步驟
架構
演算法（核心）
遇到的困難與解決方法


資料夾與其用途：
bin: 存放class檔
src: 存放java檔（另有方便程式開發的工具檔案和資料夾，請無視之）


檔案與其用途：
（只看java檔）

DrawPanel.java - 畫圖區域，繼承NorthPanel
NorthPanel.java - 視窗北邊框架的prototype
PaintClient.java - Client啓動檔
PaintServer.java - Server啓動檔
PaintTrans.java - socket接收資料用
PaintWindow.java - 整個視窗框架
Painter.java - 畫圖用框架
Receiver.java - 用來接受遠端傳過來的圖
SouthPanel.java - 視窗南邊框架的prototype


執行步驟：

利用cmd下指令或是雙擊jar檔的方式開啓程式。

1. 開啟PaintServer，等待與Client的連線。

2. 開啟PaintClient，與Server做連線。

＊連線資訊都在cmd顯示，直接點jar檔看不到。

3. 連線成功後，Server與Client的視窗才會出現。

若不需要連線功能，可以直接開Painter
(> java Painter)

4. 按鈕簡介

右半邊

上方: 畫圖用，可以用滑鼠畫圖。
（畫筆預設顏色為黑色）
下方:
Red: 讓畫筆變紅色
Green: 變綠色
Blue: 藍色
More colors: 可以選更多其他顏色
Undo: 回復功能。（最多十次）
Redo: 回復的回復。（最多也是十次）
Clear: 清除畫板至初始狀態，undo與redo也會歸零，畫筆顏色不變。
Save: 把圖片存檔。
Send: 把圖片傳給遠端。

左半邊
上方: 接收遠端傳過來的圖。
下方: 存遠端傳過來的圖。


演算法（核心）：

整個程式利用Swing實作。

按鈕實作部分：
Red、Green、Blue: JButton + ActionListener + setColor
More colors: JColorChooser
Undo: 用circular stack存資料，最多復原10次
Redo: 用stack存，最多也是10次
Clear: 設定BufferedImage為null，再call repaint()即可
Save: 用JFileChooser和showSaveDialog實作
Send: 用ImageIO.write配合socket的OutputStream實作

資料傳遞：
用Thread配合OutputStream與InputStream，
用ImageIO.write()和ImageIO.redo()配合Stream傳收資料。

Undo與Redo:

每當使用者在畫板按下滑鼠(press)開始繪圖後，先把目前畫板的內容存入undo的stack。
當使用者按下undo，先把目前內容存入redo的stack，再pop undo stack中的內容，重繪畫板。
當使用者按下redo，也是先存目前的內容，再pop redo stack中的內容。

小畫家：

利用MouseListener、MouseMotionListener與drawLine()實作，
另外配合BufferedImage與drawImage。
一方面新增內容至BufferedImage中，
另一方面把BufferedImage的內容輸出到畫板上。

滑鼠的軌跡由無數個點所組成，再用drawLine一一把這些點畫出來。


架構：

整個視窗分成左跟右邊兩塊。
各自有上下兩個panel。
利用OO的概念，把上下兩個panel的地方，當成superclass(prototype)，
再各自去繼承它，新增自己所需要的部分。

PaintWindow: Painter + Receiver
Painter: DrawPanel + SouthPanel
Receiver: NorthPanel + SouthPanel
DrawPanel繼承自NorthPanel


遇到的困難與解決方法：

1. 傳遞本端圖片至遠端時，沒先用BufferedImage接收資料就直接傳遞，使得傳遞資料有誤。正確做法應該是要先用BufferedImage去接收，再傳遞該BufferedImage。（詳情PaintTrans.java內有註解）

2. JAVA的BufferedImage沒內建deepcopy()。在StackOverflow看到教學文，成功實做出deepcopy()。

3. undo跟redo第一次的實作方法太複雜，換個方法後，成功實作。

4. call repaint()，有時候系統的速度會影響整個程式執行，所以應該讓repaint放在method的最後才對，或是不要互相干擾。JAVA不會等repaint()執行完才會執行下一行，似乎是開一個thread給repaint執行？（猜測）
