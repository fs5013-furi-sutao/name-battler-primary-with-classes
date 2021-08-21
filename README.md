# ネームバトラー初級編のサンプルコード（クラスを使用したバージョン）

クラスを使用したバージョンです。

ネームバトラー初級編の課題内容については、以下のページで確認してください。

21. 作成課題 ⭐ ネームバトラー（初級編） | Java Bootcamp:  
https://fs5013-furi-sutao.github.io/java-bootcamp/primary/21-name-battler

## ゲームの仕様

### 仕様 1. ステータスの種類

各プレーヤのステータスには以下の種類がある

- 名前: name
- 体力: hp
- 攻撃力: str
- 防御力: def
- 運: luk

### 仕様 2. 名前の入力制限

### 仕様 2-1. 文字数の制限

入力できる文字数の最小値・最大値は、定数クラスで設定できる

入力できる文字数の最小値・最大値を外れた場合は次のメッセージを表示して、再入力を求める

```
名前は半角 1 文字以上 24 文字までで入力してください（全角1文字は半角2文字で換算）
```

#### 文字数のカウント方法

文字数のカウントは、全角1文字は半角2文字で換算してカウントする

### 仕様 2-2. 同一名の制限

同じ名前が入力された場合は、次のメッセージを表示して再入力を求める

```
同じ名前は使用しないでください
```

### 仕様 3. 名前からステータスを算出する

各ステータスの取得には、ハッシュダイジェスト（ハッシュ値）を使う

```
70ee0caa47d5dc9fff880d1553305a12023753e2
```

### 仕様 4-1. ハッシュ値とは
 
ハッシュ値には以下の特徴がある

- ハッシュ値は１６進数の４０文字の文字列である
- どんな長さの文字列であっても必ず４０文字の文字列になる
- 同じ文字列であれば必ず同じハッシュ値になる
- １文字違っただけでも全然違うハッシュ値になる

実際に名前からハッシュ値を取得してみると、以下のようになる。

| 名前                     | 名前から取得したハッシュ値               |
| :----------------------- | :--------------------------------------- |
| 最強の戦士の血を引くもの | 2e8c925d7f95af418a8ba2504fb3457ba1d6eb6a |
| 伝説の勇者の友達         | 74c938ea2583ff510430ac0a98c3de5a0efd5751 |

| 基数    | 対応する値         |
| :------ | :----------------- |
| 16 進数 | 2e 8c 92 5d 7f…    |
| 10 進数 | 46 140 146 93 127… |

ハッシュ値を 2 文字毎に分けて、16 進数として考え、その値を 10 進数に変換する ことで、0 ～ 255（00 ～ FF）の値を複数取り出すことができる。

#### ハッシュ値を表示するサンプルコード

``` java
import java.math.BigInteger;
import java.security.MessageDigest;

public class HashDigest {
    public static void main(String[] args) {
        HashDigest app = new HashDigest();
        String[] names = {
            "最強の戦士の血を引くもの",
            "伝説の勇者の友達",
        };

        String[] digests = {
            app.getHashDigest(names[0]),
            app.getHashDigest(names[1]),
        };

        for (int i = 0; i < digests.length; i++) {
            System.out.printf(
                "「%s」のハッシュ値は「%s」",
                names[i], digests[i]
            );
        }
    }

    public String getHashDigest(String name) {
        try {
            byte[] result =
                MessageDigest.getInstance("SHA-1")
                .digest(name.getBytes());
            return String.format(
                "%040x",
                new BigInteger(1, result)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

### 仕様 4-2. generateNumber() メソッド

名前からハッシュ値を取得して、16 進数の文字列を取得し、10 進数の数値に変換して返す generateNumber() メソッドを使う

``` java
public static final int MAX_HASH_VALUE = 255;
public static final int HEX = 16;
public static final int HASH_CHUNK_LENGTH = 2;

/**
* ハッシュダイジェストから数値を取り出す
* @param name 名前
* @param index 何番目の数値を取り出すか
* @return 数値(0 ～ 255)
*/
public static int generateNumber(String name, int index) {
    try {
        String digest = getHashDigest(name);
        int hexCunk = index * HASH_CHUNK_LENGTH;
        String hex = digest.substring(hexCunk, hexCunk + HASH_CHUNK_LENGTH);

        return Integer.parseInt(hex, HEX);

    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public static String getHashDigest(String name) {
    try {
        byte[] result = MessageDigest.getInstance("SHA-1")
                .digest(name.getBytes());
        return String.format("%040x", new BigInteger(1, result));

    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}
```

#### generateNumber() の使い方

名前を変えると取得できる数値が変わる。 何番目の数値を取り出すかで、取り出した数値が変わる。

``` java
HashDigest app = new HashDigest();
String[] names = {
    "最強の戦士の血を引くもの",
    "伝説の勇者の友達",
};

System.out.println(app.generateNumber(names[0], 0));
System.out.println(app.generateNumber(names[0], 1));
System.out.println(app.generateNumber(names[0], 2));
```

``` console
46
140
146
```
### 仕様 5. 各ステータスの算出方法

### 仕様 5-1. 体力 HP を算出する

インデックス 0 で generateNumber() から値を取得する

### 仕様 5-2. 攻撃力 STR を算出する

次の計算式で攻撃力を算出する

```
strCalcBase * baseRate + strLiftValue
```

#### baseRate

```
インデックス 1 で generateNumber() から取得した値 / 255
```

#### strCalcBase

```
80
```

#### strLiftValue

```
20
```

### 仕様 5-3. 防御力 DEF を算出する

次の計算式で攻撃力を算出する

```
defCalcBase * baseRate
```

#### baseRate

```
インデックス 2 で generateNumber() から取得した値 / 255
```

#### defCalcBase

```
50
```

### 仕様 5-4. 運 LUK を算出する

次の計算式で攻撃力を算出する

```
lukCalcBase * baseRate
```

#### baseRate

```
インデックス 3 で generateNumber() から取得した値 / 255
```

#### lukCalcBase

```
50
```

### 仕様 6. ダメージの算出

次の計算式で敵に与えるダメージを算出する

```
baseValue * calcRate()
```

#### baseValue

攻撃者の攻撃力 STR - 敵の防御力 DEF

#### calcRate() メソッド

次の計算結果を返す

```
seed * lossRate + rateLift
```

#### seed

0 ～ 4 の範囲の乱数

#### lossRate

```
0.1
```

#### rateLift

```
0.6
```

### 仕様 7. 会心の一撃が発生する条件

次の条件が true の場合に会心の一撃が発生する

```
criticalHitRange > randomValue
```

#### lukRange

300 - 攻撃者の運 LUK

#### criticalHitRange

```
lukRange / 調整値 10
```

#### randomValue

0 ～ lukRange - 1 の範囲の乱数

## ゲームの流れ

### 1. プレイヤー名の入力

各プレイヤーの名前をユーザが入力する

```
プレイヤー 1 の名前を入力してください: 
```
``` console
プレイヤー 1 の名前を入力してください: 山本一郎
```

### 2. ターンの表示

プレイヤー同士の戦闘が何ターン目かを表示する

```
ターン 1 :==========
```

### 3. ステータスの表示

全プレーヤーのステータスを一覧表示する

```
プレイヤー1: 山本一郎  [HP:  9], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:200], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP:109], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP:103], [STR: 83], [DEF:  5], [LUK:213]
```

### 4. 攻撃

### 4-1. 攻撃の表示

```
山本一郎 の攻撃！
```

### 4-2-1. 敵に与えたダメージが 0 だった場合の表示

```
攻撃をミスした
```

### 4-2-2. 敵に会心の一撃を与えた場合の表示

LUK 値の大きさに影響を受けた乱数により、攻撃が会心の一撃（相手の防御力 DEF を無視して、攻撃力 STR そのものが相手へのダメージとなる）となった場合は、その旨を表示する

```
会心の一撃！
```

### 4-3. ダメージの表示

どの敵にどれだけのダメージを与えたかを表示する

HP の変化も表示する

```
犬 に 30 のダメージ！（HP:103 ⇒  73）
```

攻撃をミスした場合もダメージを表示する

```
猿 に 0 のダメージ！（HP:200 ⇒ 200）
```

### 4-4. HP が 0 になった場合の表示

HP が 0 となった場合の表示をする

```
きじ は力尽きた...
```

### 4-5. <Press Enter Key> の表示

1つのターンが終了したら <Press Enter key> と表示して、Enter Key を押すことで次のターンに進めるようにする

### 4-6. 戦闘の最終結果を表示

ターンを繰り返し、戦闘に決着が着いた時に表示をする

最後に生き残ったプレーヤーと全プレーヤーの最終ステータスを表示する

```
猿 の勝利！！
プレイヤー1: 山本一郎  [HP:  0], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:117], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP:  0], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP:  0], [STR: 83], [DEF:  5], [LUK:213]
```

### 実行例

``` console
プレイヤー 1 の名前を入力してください: 山本一郎
プレイヤー 2 の名前を入力してください: 
名前は半角 1 文字以上 24 文字までで入力してください（全角1文字は半角2文字で換算）
プレイヤー 2 の名前を入力してください: 最強の戦士の血を引くものだよ
名前は半角 1 文字以上 24 文字までで入力してください（全角1文字は半角2文字で換算）
プレイヤー 2 の名前を入力してください: 猿
プレイヤー 3 の名前を入力してください: 山本一郎
同じ名前は使用しないでください
プレイヤー 3 の名前を入力してください: きじ
プレイヤー 4 の名前を入力してください: 犬
ターン 1 :==========
プレイヤー1: 山本一郎  [HP:  9], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:200], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP:109], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP:103], [STR: 83], [DEF:  5], [LUK:213]
山本一郎 の攻撃！
攻撃をミスした
猿 に 0 のダメージ！（HP:200 ⇒ 200）

きじ の攻撃！
山本一郎 に 24 のダメージ！（HP:  9 ⇒   0）
山本一郎 は力尽きた...

犬 の攻撃！
きじ に 65 のダメージ！（HP:109 ⇒  44）

猿 の攻撃！
犬 に 30 のダメージ！（HP:103 ⇒  73）

ターン 2 :==========
プレイヤー1: 山本一郎  [HP:  0], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:200], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP: 44], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP: 73], [STR: 83], [DEF:  5], [LUK:213]
猿 の攻撃！
きじ に 30 のダメージ！（HP: 44 ⇒  14）

きじ の攻撃！
犬 に 43 のダメージ！（HP: 73 ⇒  30）

犬 の攻撃！
きじ に 73 のダメージ！（HP: 14 ⇒   0）
きじ は力尽きた...

ターン 3 :==========
プレイヤー1: 山本一郎  [HP:  0], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:200], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP:  0], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP: 30], [STR: 83], [DEF:  5], [LUK:213]
猿 の攻撃！
犬 に 27 のダメージ！（HP: 30 ⇒   3）

犬 の攻撃！
会心の一撃！
猿 に 83 のダメージ！（HP:200 ⇒ 117）

ターン 4 :==========
プレイヤー1: 山本一郎  [HP:  0], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:117], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP:  0], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP:  3], [STR: 83], [DEF:  5], [LUK:213]
猿 の攻撃！
犬 に 20 のダメージ！（HP:  3 ⇒   0）
犬 は力尽きた...

猿 の勝利！！
プレイヤー1: 山本一郎  [HP:  0], [STR: 20], [DEF: 38], [LUK:141]
プレイヤー2: 猿        [HP:117], [STR: 39], [DEF: 40], [LUK:224]
プレイヤー3: きじ      [HP:  0], [STR: 78], [DEF:  1], [LUK:224]
プレイヤー4: 犬        [HP:  0], [STR: 83], [DEF:  5], [LUK:213]
```
