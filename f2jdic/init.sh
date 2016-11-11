#!/bin/bash

SRCDIR="./data"
DISTDIR="./data/utf8"

# convert original text to utf-8
echo "Copy files to data/utf8 and convert character code."
rm -rf $DISTDIR
mkdir $DISTDIR
find $SRCDIR -maxdepth 1 -name '*.csv' | xargs -J % cp % $DISTDIR
#nkf -w -Lu readme.txt > readme_utf8.txt
find $DISTDIR -maxdepth 1 -name '*.csv' | xargs nkf -w -Lu --overwrite
#ls $DISTDIR | xargs nkf -w -Lu --overwrite

# try to replace failed characters
echo "Replacing characters."
STR='àâçéèêîïœôû'
sed -i.bak \
  -e 's/鑽/èr/g' -e 's/馘/éd/g' -e 's/馥/ée/g' -e 's/騁/ét/g' -e 's/駑/ém/g' -e 's/駭/én/g' \
  -e 's/軋/ça/g' -e 's/駻/ér/g' -e 's/饌/éa/g' -e 's/馗/éc/g' -e 's/饕/éb/g' -e 's/皦/ût/g' \
  -e 's/駘/él/g' -e 's/駱/ép/g' -e 's/輟/ço/g' -e 's/穃/âm/g' -e 's/麥/êm/g' -e 's/騅/év/g' \
  -e 's/駸/és/g' -e 's/鑞/èl/g' -e 's/鑚/ès/g' -e 's/鑪/èm/g' -e 's/絜/în/g' -e 's/鐡/èd/g' \
  -e 's/駮/éo/g' -e 's/駛/éj/g' -e 's/馮/ég/g' -e 's/穰/ât/g' -e 's/珣/êt/g' -e 's/稈/âb/g' \
  -e 's/稷/âl/g' -e 's/穡/âp/g' -e 's/穗/ân/g' -e 's/璟/ûm/g' -e 's/鑼/èt/g' -e 's/鹹/êc/g' \
  -e 's/稟/âg/g' -e 's/騷/éz/g' -e 's/閊/èz/g' -e 's/羡/ît/g' -e 's/鈩/èn/g' -e 's/稍/âc/g' \
  -e 's/麪/êp/g' -e 's/鐵/èc/g' -e 's/鑷/èq/g' -e 's/駲/éq/g' -e 's/皞/ûr/g' -e 's/馭/éf/g' \
  -e 's/駟/éi/g' -e 's/钁/èv/g' -e 's/鰾/éâ/g' -e 's/璉/ûl/g' -e 's/稠/âf/g' -e 's/稱/âi/g' \
  -e 's/鑒/èg/g' -e 's/馼/éh/g' -e 's/甁/ûn/g' -e 's/鑵/èp/g' -e 's/騏/éu/g' -e 's/鳬/éé/g' \
  -e 's/麩/ên/g' -e 's/黐/êv/g' -e 's//é /g' -e 's//à /g' -e '/麝香/!s/麝/êl/g' -e '/陥穽/!s/穽/âv/g' \
  -e 's/絈/îm/g' -e 's/鐫/èb/g' -e 's//é-/g' -e 's//à-/g' -e '/穢す/!s/穢/âq/g' -e 's/鑁/èf/g' \
  -e '/駝鳥/!s/駝/ék/g' -e 's/精/îl/g' -e 's/輹/çu/g' \
  -e 's/炙/àt/g' \
  -e 's/麭/êq/g' \
  -e 's/麭/êq/g' \
  $DISTDIR/frU.csv

#sed -i "" -e 's/黎(?!(明|耕体))/êt/g' $DISTDIR/word.csv $DISTDIR/idiom.csv
sed -i "" -E "s/([a-z\"-\s]*|[$STR])黎([a-z\"-\s]+|[$STR])/\1êt\2/g" $DISTDIR/frU.csv

sed -i "" -e 's//‘/g' $DISTDIR/frU.csv #u0092
sed -i "" -e 's//—/g' $DISTDIR/frU.csv #u0097
sed -i "" -e 's//œ/g' $DISTDIR/frU.csv #u009c
sed -i "" -e 's//Œ/g' $DISTDIR/frU.csv #u008c
sed -i "" -e 's//à>/g' $DISTDIR/frU.csv #U+E03E

# separate data into word and idiom
echo "Separating original csv."
#rm csv/word.csv
#rm csv/idiom.csv
ruby conv.rb

# import data into sqlite3 file
echo "Storing data in database."
#rm dict.sqlite3
sqlite3 dict.sqlite3 < import.sql
