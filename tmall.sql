use tmall_springboot
create table [user](
	id bigint IDENTITY(1,1),
	name nvarchar(20) DEFAULT NULL,
	password nvarchar(20) DEFAULT NULL,
	salt nvarchar(255) DEFAULT NULL,
	PRIMARY KEY(id)
)
go
create table [category](
	id bigint identity(1,1),
	name nvarchar(255) DEFAULT NULL,
	PRIMARY KEY(id)
)
go
create table [property](
	id bigint identity(1,1),
	cid bigint DEFAULT NULL,
	name nvarchar(255) DEFAULT NULL,
	PRIMARY KEY(id),
	CONSTRAINT fk_property_category FOREIGN KEY(cid) REFERENCES category(id)
)
go
CREATE TABLE [product](
	id bigint IDENTITY(1,1),
	name nvarchar(255) DEFAULT NULL,
	subTitle nvarchar(255) DEFAULT NULL,
	originalPrice float DEFAULT NULL,
	promotePrice float DEFAULT NULL,
	stock int DEFAULT NULL,
	cid bigint DEFAULT NULL,
	createDate datetime DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_product_category FOREIGN KEY(cid) REFERENCES category(id)
)
go
CREATE TABLE [property_value](
	id bigint IDENTITY(1,1),
	pid bigint DEFAULT NULL,
	ptid bigint DEFAULT NULL,
	value nvarchar(255) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_propertyvalue_property FOREIGN KEY(ptid) REFERENCES property (id),
	CONSTRAINT fk_propertyvalue_product FOREIGN KEY(pid) REFERENCES product(id)
)
go
CREATE TABLE [product_image](
	id bigint IDENTITY(1,1),
	pid bigint DEFAULT NULL,
	type nvarchar(255) DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_productimage_product FOREIGN KEY(pid) REFERENCES product(id)
)
go
CREATE TABLE [review](
	id bigint IDENTITY(1,1),
	content text DEFAULT NULL,
	uid bigint DEFAULT NULL,
	pid bigint DEFAULT NULL,
	createDate datetime DEFAULT NULL,
	PRIMARY KEY (id),
	CONSTRAINT fk_review_product FOREIGN KEY(pid) REFERENCES product(id),
	CONSTRAINT fk_review_user FOREIGN KEY(uid) REFERENCES [user](id)
)
go
CREATE TABLE [order] (
	 id bigint IDENTITY(1,1),
	 orderCode nvarchar(255) DEFAULT NULL,
	 address nvarchar(255) DEFAULT NULL,
	 post nvarchar(255) DEFAULT NULL,
	 receiver nvarchar(255) DEFAULT NULL,
	 mobile nvarchar(255) DEFAULT NULL,
	 userMessage nvarchar(255) DEFAULT NULL,
	 createDate datetime DEFAULT NULL,
	 payDate datetime DEFAULT NULL,
	 deliveryDate datetime DEFAULT NULL,
	 confirmDate datetime DEFAULT NULL,
	 refundDate datetime DEFAULT NULL,
	 uid bigint DEFAULT NULL,
	 status nvarchar(255) DEFAULT NULL,
	 total float DEFAULT NULL,
	 refund int DEFAULT NULL,
	 PRIMARY KEY (id),
	 CONSTRAINT fk_order_user FOREIGN KEY(uid) REFERENCES [user](id)
)
go
CREATE TABLE [order_item](
	 id bigint IDENTITY(1,1),
	 pid bigint DEFAULT NULL,
	 oid bigint DEFAULT NULL,
	 uid bigint DEFAULT NULL,
	 number bigint DEFAULT NULL,
	 PRIMARY KEY (id),
	 CONSTRAINT fk_orderitem_user FOREIGN KEY (uid) REFERENCES [user](id),
	 CONSTRAINT fk_orderitem_product FOREIGN KEY (pid) REFERENCES [product](id),
	 CONSTRAINT fk_orderitem_order FOREIGN KEY (oid) REFERENCES [order](id)
)
go
create index idx_cid_property on [property](cid)
create index idx_cid_product on [product](cid)
create index idx_pid_property_value on [property_value](pid)
create index idx_ptid_property_value on [property_value](ptid)
create index idx_pid_product_image on [product_image](pid)
create index idx_pid_review on [review](pid)
create index idx_uid_review on [review](uid)
create index idx_uid_order on [order](uid)
create index idx_uid_order_item on [order_item](uid)
create index idx_pid_order_item on [order_item](pid)
create index idx_oid_order_item on [order_item](oid)