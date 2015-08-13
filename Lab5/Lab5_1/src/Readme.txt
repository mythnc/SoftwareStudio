＊所有檔案皆以utf-8編碼。

目錄：

檔案與其用途
執行步驟
演算法（核心）
遇到的困難與解決方法


檔案（只計算java檔）與其用途：
BubbleThread.java - 用來做Bubble Sort的thread。繼承SortThread。
InsertThread.java - 用來做Insertion Sort的thread。繼承SortThread。
SelectThread.java - 用來做Selection Sort的thread。繼承SortThread。
SortPanel.java - 把序列資料轉換成視覺效果。
SortThread.java - superclass of other sorting threads.
SortWindow.java - 此程式的視窗，載入元件與初始資料。
VisualSort.java - 啟動檔。


執行步驟：

0) 視窗呈現三組不同的亂數序列（預設為Diff Random）。

1) 按Start會開始對三個序列分別做Bubble sort、Insertion sort、Selection sort。
   ＊若序列本身已排序，按Start依然會做排序。

2) 按Diff Random會變成Same Random，再按一次會變回Diff Random。

3) 按Reset會依照目前是Diff Random或Same Random去產生隨機序列。
   如果是Diff Random下，會產生三種不同的隨機序列（預設）；
   如果是Same Random下，會產生三個一樣的隨機序列。

＊排序中按Button皆無任何效果。


演算法（核心）：

亂數序列的產生：
一開始先初始序列為0到9由小排到大的序列。
從序列的第一個index開始，到最後一個index為止，
每次利用Math.random()產生介於0~9的indexR，
讓indexR與該index做互換的動作，即swap(indexR, index)。
如此，可以讓一個有排序的序列，變成無排序的序列。

視覺效果的呈現：
1) 顏色：
利用三種顏色來呈現目前排序的情況。
灰色：未排序序列。
黑色：已排序序列。
紅色：演算法目前所處理到的位置（index）。

顏色的設定由sorting thread所給予。

2) 資料的呈現：
序列中每一個值，都用一個矩形表示。
每個矩形的寬度都一樣，只有高度隨著值的大小而增加。
所以對任意一個個數為n的序列，從index[0]到index[n]為止，
寬度都是width，但高度為base_height + height * index[n]。

因為fillrect的座標是指矩形左上角的頂點。
所以對任意一個個數為n的序列，從index[0]到index[n]為止，
x座標的改變量為baseX + 間距 * n，即x座標等距遞增。
y座標的改變量為baseY - height * index[n]，
即數值越大，y座標的值越小、越靠近上方。

所以，讓x座標逐漸等距遞增、矩形寬度固定不變，
並讓y座標與矩形高度隨著數值的大小做改變，
如此可以畫出許多同寬度、不同高度、等距、且在同一水平上的矩形。

Thread的實作：
因為每個sorting thread都有相似的功能（repaint()、sleep()等）。
所以實作出一個抽象的superclass（即SortThread），
把相似的功能都寫在此class中。
再建立不同的subclass繼承suerpclass，
各別實作自己的排序演算法（Bubble、Insertion與Selection）。

在排序過程中，利用sleep()、顏色的設定
與repaint()呈現目前的排序情形。

Button的實作：
1) start Button
每次按start時，就創造三個sort的thread，並執行這些thread。

2) Diff/Same Random Button
若顯示Diff Random時按此鍵，按鈕的字會變為Same Random，
反之，亦然。
用一個boolean variable紀錄目前的狀態。

3) reset Button
每次按reset時，依照2)中boolean variable的布林值讓array做改變。
若紀錄的內容為Diff Random，讓三個array分別產生三種不同的隨機陣列；
若為Same Random，只產生一個隨機array，並把此array的內容copy到其他array。
隨機陣列產生後之後，再做repaint()。


額外處理：
a) 為避免使用者在排序過程中(thread執行中) 亂按Button。
利用一個integer，每當thread做完排序時，讓此interger + 1，
如此當integer與thread個數相等時，表示thread皆執行完畢。
若不相等，表示尚有thread在執行，這時候按Button是不會觸發事件的。

註：
原本想用join()的方式實作，但GUI中的main是用來繪製介面的，
若等到所有的threads都處理完，才讓main繼續執行，
那GUI只會顯示改變後的最終結果。
也就是說，若用join()，
再等待threads執行完畢的期間，介面不會有所動作。

b) 使用者對已排序的資料再按一次start。
一樣用thread做排序，但因為已排序的序列為黑色，
所以需把顏色設定為初始為未排序的灰色即可。

sort的實作：
1) Bubble Sort:
分成已排序跟未排序兩組序列，每次從未排序的資料中，
由後往前遞減，每當前項大於後項時，互換兩者。
互換至最前項時，讓此項增加至已排序序列中。
若遞減至最前項都無互換，表示資料已排序，跳出迴圈。

虛擬碼：
for i = 0 to n-1
    move = false
    for j = n-1 to i
    if (a[j-1] > a[j])
        swap(j-1, j)
        move = true
    if (!move)
        break

2) Insertion Sort:
分成已排序跟未排序兩組序列，每次逐漸從未排序的資料中
增加一筆資料至已排序中。
從已排序的最後一項開始，若前項大於後項，就互換兩者。
若前項小於後項，表示序列已排序。

虛擬碼：
for i = 1 to n-1
    for j = i to 0 and a[j-1] > a[j]
        swap(j-1, j)

3) Selection Sort:
分成已排序跟未排序兩組序列，每次從未排序的序列中，
找出最小的值，並把此值增加至已排序中的最末端。

虛擬碼：
for i = 0 to n-1
    min = i;
    for j = i+1 to n
        if (a[min] > a[j])
            min = j
    swap(min, i)


遇到的困難與解決方法：
Q1. pdf中對規格的描述有所疑問(same panel at the same time)。
A1. 寫email問老師、助教們。

Q2. 用join()會讓GUI lag並只顯示最終sort後的結果。
A2. google。處理方法如同上述。

Q3. 不知道如何在inner class傳遞它的outer class。
A3. google。用OuterClassName.this即可。

