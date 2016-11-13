# f2jdic-android
A simple offline french-to-japanese dictionary for android. 
Database file is based on [f2jdic](http://www.vector.co.jp/soft/win95/edu/se217092.html) by ICHIRO.

アンドロイドのシンプルなオフライン和仏辞書アプリです。辞書データはICHIROさんのf2jdicを使わせて頂いています。

Un simple dictionnaire français-japonais hors ligne pour android. Les données du dictionnaire sont basées sur f2jdic par ICHIRO.

## Development environment

* Android 5.1
* API 22
* Nexus 5 (Android 6.0.1)
* OS X El Capitan(10.11.6)

## Unimplemented functions

* Tool bar
* Menu interface, setting, about, etc..
* Editing mode
* Search results must be identical between capital and small letters
* Handling western europe characters like a=àâ, c=ç, e=éèê i=îï, oe=œ, o=ô, u=û...and so do capitals
* Conjugation of verbs

## Screenshots
<img src="device-2016-11-04-024524.png" width="350px">
<img src="device-2016-11-04-024904.png" width="350px">
a
# Usage

## Data preprocessing
To create sqlite3 database file, in f2jdic directory

```
sh ./init.sh
```

dict.sqlite3 is created in f2jdic/ and f2jdic-android/app/src/main/assets/ if you need.
In init.sh, convert.rb is called to extract necessary data from frU.csv. init.sh execute character-substitution for those were failed in char-code conversion. Then, import and create tables by import.sql.

### Words and Idioms

|_id|   fr|        jp|  en|             exp|count|modified|
|:-:|----:|---------:|---:|---------------:|----:|-------:|
|1  |avoir|持っている|have|avoir trente ans|    0|       0|
|2  |  ...|       ...| ...|             ...|   ..|      ..|
|3  |  ...|       ...| ...|             ...|   ..|      ..|

### History

|_id|word_id|word_label|viewed_at|
|:-:|------:|---------:|--------:|
|  1|      1|     avoir|123456789|
|  2|    ...|       ...|      ...|
|  3|    ...|       ...|      ...|
