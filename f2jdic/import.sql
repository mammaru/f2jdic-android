drop table if exists WORD;
drop table if exists IDIOM;
drop table if exists HISTORY;

create table WORD
(
  _id INTEGER NOT NULL PRIMARY KEY,
  label TEXT,
  trans TEXT,
  exp TEXT,
  count INTEGER,
  modified INTEGER
);

create table IDIOM
(
  _id INTEGER NOT NULL PRIMARY KEY,
  label TEXT,
  trans TEXT,
  exp TEXT,
  count INTEGER,
  modified INTEGER
);

create table HISTORY
(
  _id INTEGER NOT NULL PRIMARY KEY,
  word_id INTEGER,
  word_label TEXT,
  viewed_at TIMESTAMP CURRENT_TIMESTAMP
);

.separator ,
.import ./data/utf8/word.csv WORD
.import ./data/utf8/idiom.csv IDIOM
