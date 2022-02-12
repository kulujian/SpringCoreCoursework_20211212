-- 交易 TX (Transcation) 所需要的資料表
-- book (書籍資料)、stock(庫存資料)、wallet(客戶雲端錢包)
-- 建立 book (書籍資料)
create table if not exists book(
	bid integer not null auto_increment,
	bname varchar(20) not null,
	price integer,
	ct timestamp default current_timestamp,
	primary key (bid)
);
-- 建立 stock(庫存資料)
create table if not exists stock(
	sid integer not null auto_increment,
	bid integer not null,
	amount integer default 0, -- 庫存數量
	primary key (sid),
	foreign key (bid) references book(bid)
);
-- 建立 wallet(客戶雲端錢包)
create table if not exists wallet(
	wid integer not null auto_increment,
	wname varchar(20) not null,
	money integer default 0,
	primary key (wid)
);

-- HomeWork 建立交易紀錄 order_log 資料表
-- vincent 在 2022/02/08 14:07:51, 買了 java 書, 02 本, 共 300 元。
-- vincent 在 2022/02/08 15:17:53, 買了 python 書, 02 本, 共 200 元。
-- vincent 在 2022/02/08 16:27:55, 買了 java 書, 04 本, 共 600 元。
-- 注意：若 book 的 price 欄位有變動，order_log 則不影響
-- 試問：資料應如何創建？(需支援 TX)

create table if not exists order_log(
	lid integer not null auto_increment,
	logTime timestamp default current_timestamp,
	customerNum integer not null,
	customerName varchar(20) not null,
	productCode integer not null,
	productName varchar(20) not null,
	productAmount integer not null,
	productSumMoney integer not null,
	primary key (lid)	
);