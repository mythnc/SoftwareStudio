＊所有檔案皆以utf-8編碼。
＊幾乎和Lab9-1一樣，所以只寫Lab9-1沒有的部分。

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

和9-1一樣，除了下列檔案：
ReceivePanel.java - 接收遠端繪圖資料畫板，繼承NorthPanel
TransData.java - 儲存繪圖資料


執行步驟：

和9-1一樣，只是未實作undo與redo。


演算法（核心）：

和9-1一樣，除了資料紀錄用自己寫的class和沒有實作undo、redo。

本端資料紀錄：

利用線條是用點所構成的概念，每當使用者在畫板上畫圖時，
就把使用者所使用的顏色與點做紀錄。
每當使用者按下滑鼠到放開為止（Press到Release）當成一筆資料，
並配合MouseListener與MouseMotionListener來存資料。
用color記錄顏色，再用Point紀錄滑鼠經過的每個點。
實作方法在TransData.java中。

資料傳遞：
改用ObjectStream傳遞資料，把TransData包起來傳遞。

遠端繪圖：
當遠端拿到TransData的資料後，從第一筆資料到最後一筆，
每筆資料都可以知道線條的顏色，與構成線條的所有點。
利用setColor()與drawLine()便可以把遠端的圖形重繪至本端上。


架構：

和9-1一樣，除了Receiver原本由NorthPanel和SouthPanel，
現由ReceivePanel + SouthPanel組成。


遇到的困難與解決方法：

ObjectStream的接收若每次送出的資料不一樣，但還是相同的物件時，遠端會視為相同的物件（就算在本地端已經改變物件內的內容），要用reset()重設或是writeUnshared()傳遞。

