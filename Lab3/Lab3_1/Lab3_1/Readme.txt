＊所有的檔案皆以utf-8編碼。


目錄：
檔案結構
流程圖
執行步驟
演算法
遇到的困難與解決方法


檔案結構：

原始檔案共有8個java檔與1個資料夾存放圖檔（不計算class檔）
Chart.java - 處理set friendship中的示意圖。
Friend.java - 存放user好友的資料。繼承User.java。
LaunchNcFacebook.java - 即main，輸入預設資料與啟動整個程式。
MainTopPanel.java - 處理第一個視窗中除了最底Button以外的元件。
MainWindow.java - 第一個視窗，處理Button元件並載入MainTopPanel的資料。
NcFacebook.java - 為User.java與LaunchNcFacebook.java的媒介。
SubWindow.java -- 處理第二個視窗。
User.java - 存放User的資料。


流程圖：

利用LaunchNcFacebook來啟動程式。
LaunchNcFacebook會把預設的使用者資料與好友關係丟給NcFacebook做處理。
NcFacebook接收到資料後，會利用User來建立使用者資料，並把Friend資料丟給User做處理。
User接到資料後， 會利用Friend去處理朋友資料。

NcFacebook中利用ArrayList來存取User的個人資料，
每個User中也是利用ArrayList來存取Friend的資料。

當資料建構完畢後，LaunchNcFacebook會啟動MainWindow，
並把NcFacebook中的資料(ArrayList)丟給MainWindow去處理。

MainWindow接收到資料後，本身會建立最底下的元件JButton，
並call MainTopPanel去建立除了JButton以外的元件。

當使用者點擊MainWindow的JButton，會啟動第二個Window(SubWindow)。
這時候就利用SubWindow來處理第二個Window。
SubWindow中的示意圖（最右邊），由Chart來處理。


執行步驟：

第一個視窗示意圖
a | b | c
----------
d | e | f
----------
    g

a: user list(JComboBox)
b: user's friends label(JLable)
c: friend profile label(JLable)
d: user profile label (JLabel)
e: user's friend list (JList)
f: friend profile content lable (JLabel)
g: set friend button (JButton)

0) 建立檔案，開啟第一個視窗。

1) 預設不顯示任何資料，要從a處來選擇使用者。

2) 一旦選擇使用者之後，程式會開始運算，找到使用者的資料之後，
   去改變b、d、e的內容。

3) 當使用者點選e中的選項時，程式會再去改變c與f的內容。
   f的內容根據好友度而有所不同。
   若好友設定使用者為Close會多出Phone與Marriage的資料。

4) 當使用者點選g後，會顯示第二個視窗。

第二個視窗示意圖
a | b | c

a: user list (JList)
b: user's friend list (title Border + JRadioButton)
c: chart (JPanel)

1) 預設只顯示a與c，a的預設為沒有選擇任何使用者。
   c的預設為每個人的png檔，與每個人之間的關係線條。

2) 當使用者點選a中的item時，會顯示b，並改變c中與之對應的個人圖檔
 Allen -> 手會碰嘴巴
 Peter -> 比讚
 Sam -> 笑臉
 Cathy -> 吐舌
 Mike -> 哭哭

3) 當使用者點選b中的RadioButton時，會改變資料c中與之對應的線條顏色，
　 Regular為紅色，Close為藍色。
   同時，ArrayList中相對應的資料也會改變。
   (重新選取第一個視窗e的item，f也會改變為相對應的資料)


演算法：
第一個視窗與第二個視窗使用不同的方法實作。

第一個視窗。從JComboBox(a處)中選擇user時，
會直接更新b、d、e中components的內容。
選取e時，也會動態更新c、f的內容。
也就是元件只有一份，每次會依照event的不同，
而去改變元件的內容（動態改變）。

第二個視窗，當從a處選擇user時，
會從User與Friend中載入相對應的Components與events，放入b的JPanel中，
並改變User中的圖檔(Username.png變成chooseUsername.png)，
再讓c處的Chart做repaint。
當點選b的JRadioButton時，會改變Friend中的好友度，
再讓c處的Chart做repaint。
也就是每個User會有自己的元件（事先建立），
依照event的不同，而去載入不同的元件。


遇到的困難與解決方法：
Q1. 第二個視窗難以使用第一個視窗的方法實作（除非JRadioButton先寫死成四組）
A1. 改變演算法，讓第二個視窗使用上述的方法實作。

Q2. 不知道如何讓JRadioButton的外面顯示標題。
A2. 觀看JAVA官網的教學與Demo code，找到Border用法。

Q3. 不知道當panel內的元件改變時，如何在不調整視窗大小的前提下，
　  讓panel做改變。
A3. 到助教Lab請教學姐，得到removeAll()與updateUI()的用法。

Q4. 當包成jar檔時，相對路徑與絕對路徑都會失效，使圖檔無法順利讀取。
A4  google，把讀圖方法改成「this.getClass().getClassLoader().getResource("image.png");」

