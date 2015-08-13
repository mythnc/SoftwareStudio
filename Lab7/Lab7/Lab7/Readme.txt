＊所有檔案皆以utf-8編碼。

目錄：

檔案與其用途
執行步驟
流程
演算法（核心）
遇到的困難與解決方法


檔案與其用途：

java source code:
LaunchWordVisual.java - 啟動檔。
LeftDraw.java - 左邊繪圖panel。
RightMenu.java - 右側選單，含有load、draw、send等swing components。
WordVisual.java - 整個視窗的框架。


執行步驟：

1. 繪圖前置
a) 按load鈕可以用瀏覽的方式讀檔。選擇要讀的檔案後，會把該檔案內的文字傳入上方的JTextPane。
b) 若不想讀檔，也可以在下方的JTextPane作輸入，
   輸入之後按send，輸入的訊息會傳遞給上方的JTextPane，附加至字串最後面，
   下方的JTextPane會重置。

2. 繪圖
按draw開始繪圖，會在左側呈現word cloud。word clud依序呈現完後，才繪製bar chart
的資料。Assignment7_Nov27.pdf的規格是要呈現前15 weight的word，但圖片顯示的是30個。
我呈現的bar數目與圖片相同（30個）。

3. 資料改變
同1.b)。在下方的JTextPane作輸入，輸入之後按send，輸入的訊息會傳遞給上方的JTextPane，
附加至字串最後面，下方的JTextPane會重置。要按draw才會開始繪圖。

4. 額外處理
用滑鼠點擊word cloud上的word，會在視窗左下角呈現該word與出現次數。
（因為bar chart不會呈現所有的資訊，這部分是補足bar chart所沒有的。）


流程

因為學姊幾乎已經把整個程式碼寫完了，只刪除一些片段讓我們填補。
所以我們要做的就是補足這些程式碼。

首先我去trace code。看WordCram與cue.language的原始碼。
之後便利用WordCram去產生word cloud，用cue.language.Counter與
cue.language.WordIterator去抓word與計算word出現的次數。
如此，利用WordCram的getWords()與Counter的getCount(String s)，便可以畫出bar chart。


演算法（核心）：

button event的處理方式:
ppt的教法是用ChangeListener、AbstractButton與ButtonModel去處理event。
但我發現單純利用ActionListener即可達到相同效果。
好處是比較直觀也比較簡單（因為是JButton，所以直接用ActionListener處理event即可）

send button:
send利用Document的insertString()把下方JTextPane的文字附加至上方的JTextPane。
之後再重置下方JTextPane。

draw button:
先從上方的JTextPane接收string，之後初始化用來產生WordCram的變數wc與
用cue.language.Counter與WordIterator去計算string中的Word與其次數，
再由processing的draw()去畫wc。
畫完之後，再用WordCram的getWords()與Counter的getCount(String s)畫bar。

mouseclick:
當滑鼠點擊word cloud上的字時，用getWordAt(mouseX, mouseY)得知是哪一個
字被按到，再用text()在視窗左下角畫出該字與字的次數。
每按一次會先把該區域填滿與背景相同的顏色，
之後才畫text。


遇到的困難與解決方法：
無。

