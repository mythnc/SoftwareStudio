step:
1. 使用者輸入字串
若輸入exit表示離開程式。
輸入字串不可為空字串。

2. 判斷字串是否為回文
利用for loop與兩個變數i, j。
i從該字串的頭開始往後跑、j從字串的尾開始往前跑，
逐一比對index i與index j是否相等，若相等，比到i >= j為止。
若不相等，停止比對，跳出for loop。

另用mark紀錄比對結果，若mark為true表示為回文，
為false表示不為回文。
mark預設為true。

3. 輸出判斷結果
為回文輸出「this is a palindrome.」
不為回文輸出「this isn't a palindrome.」
空字串輸出「you must input one character at least.」

遇到的困難：
不可以寫while (1) ...

解決方法：
改用while (true) ...
