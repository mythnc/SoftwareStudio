＊所有檔案皆以utf-8編碼。

目錄：

檔案與其用途
執行步驟
演算法（核心）
遇到的困難與解決方法


檔案與其用途：
1)java source code

CollegeData.java - 存放學院資料。繼承StudentData.java。
DepartmentData.java -存放系所資料。繼承StudentData.java。
GradeData.java -存放年級資料。繼承StudentData.java。
LaunchVisualData.java - 啓動檔。
MakeData.java - 讀檔並把資料做分割和儲存。
StudentData.java - the superclass of other data.
UniversityData.java -存放學校資料。繼承StudentData.java。
VisualData.java - 把資料做視覺化的呈現。

2) data<dir>:
regb100_1.txt - 100年度資料。
regb101_1.txt - 101年度資料。


執行步驟：

1. 在cmd輸入"java -jar LaunchVisualData"，或是用滑鼠雙擊jar檔，
會呈現regb100_1.txt的資料。

2. 可以利用下參數的方式，讓程式讀檔作處理：
在cmd輸入"java -jar LaunchVisualData filename"，
filename可以是regb100_1.txt或regb101_1.txt。


演算法（核心）：

利用processing的pushMatrix()、popMatrix()、translate()來做圖形繪製。
只要不斷的平移坐標系，就可以繪出院系級等圖形資料。

背景：
背景的顏色表示整間學校的男女比，女白男黑。
越黑表示學校的男生越多，越白表示學校的女生越多。

圖形：
方框代表院，方框下的文字表示該院的名稱。
學院名稱下的文字（黑色線條之下），表示該學院所包含的系所。
文字右方的數字，表示該院（系所）的人數。
方框上方的長條代表系，由左至右，分別對應方框下每行的系所（第二行開始算）。
長條右邊的圓圈代表級，由下而上，分別表示1、2、3、4、5、6年級。
（從1開始到6，1對1，有多少個圈，就有多少個年級）

大小：
方框、長條與圓圈的大小，都是相對，而非絕對。
方框的大小表示每個學院的人數相對於平均學院（學校總人數 / 學院個數）人數，
越大表示高於平均，越小表示低於平均。
長條的長度表示每個系的人數相對於該學院的平均系人數（該學院總人數 / 該學院系所個數）。
越長表示高於平均，越短表示低於平均。
圓圈的大小表示每個級的人數相對於該系的平均級人數（該系所總人數 / 6）。
越大表示高於平均，越小表示低於平均。

所以把不同學院的系所長條圖互相比較，
或是不同系所的年級圓圈互相比較，是沒有意義的。

邊框：
邊框有紅藍之分。
紅色表示該院（系、級）人數，女生較多。
反之，男生較多。

色彩：
與背景顏色的意義一樣。
越白表示該院（系、級）的女生越多；越黑表示該院（系、級）的男生越多。

男女比的色彩是絕對的，都是255 * (女生 / 總人數），
所以校院系級的色彩可以互相比較。


遇到的困難與解決方法：

Q1. 在jar檔中，File()無用。
A1. 同Lab3。用getClass().getClassLoader().getResource()。
    再用InputStreamReader接。

Q2. 檔案讀不到、滑鼠雙擊無法執行，但在cmd中正常。
A2. google。讀取檔案要指定編碼格式。InputStreamReader(url.openStream())
    改成InputStreamReader(url.openStream(), "UTF-8"))。

Q3. 跟Processing不熟。
A3. 上Processing官方網站看一手資料學。
