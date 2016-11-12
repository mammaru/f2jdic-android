drop table if exists WORD;
drop table if exists IDIOM;
drop table if exists HISTORY;

create table WORD
(
  _id INTEGER NOT NULL PRIMARY KEY,
  label TEXT NOT NULL,
  trans TEXT,
  exp TEXT,
  count INTEGER NOT NULL,
  modified INTEGER NOT NULL
);

create table IDIOM
(
  _id INTEGER NOT NULL PRIMARY KEY,
  label TEXT NOT NULL,
  trans TEXT,
  exp TEXT,
  count INTEGER NOT NULL,
  modified INTEGER NOT NULL
);

create table HISTORY
(
  _id INTEGER NOT NULL PRIMARY KEY,
  word_id INTEGER NOT NULL,
  word_label TEXT NOT NULL,
  viewed_at INTEGER NOT NULL
);

.separator ,
.import ./data/utf8/word.csv WORD
.import ./data/utf8/idiom.csv IDIOM
