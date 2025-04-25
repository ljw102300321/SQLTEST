# 建立表结构 
## 商品表 product 主键：product_id,product_Name
create table product (
Product_id int not null primary key ,
Product_Name varchar(20) not null primary key,
Product_Price Double not null,
Product_Stock int default 0
);
## 订单表 order1  主键：order_id
create table order1 (
Order_id int not null primary key ,
Order_date date,
Order_price Double not null
);

## 订单商品表 product_order 外键：order_id,product_id
create table product_order (
product_id int not null,
order_id int not null,
quantity int not null,
foreign key (product_id) references product(product_id),
foreign key (order_id) references order1(order_id)
);
## 为部分属性添加check约束或not null约束
### 为product表添加约束
alter table product
add constraint chk_pid check (product.Product_id >= 0);

alter table product
add constraint chk_price check (product.Product_Price >= 0);

alter table product
add constraint check_constraint_name check (product.Product_Stock >= 0);

### 为order1表添加约束
alter table order1
add constraint chk_oid check (order1.Order_id >= 0);

alter table order1
add constraint chk_opr check (order1.Order_price >= 0);

### 为product_order表添加约束
alter table product_order
add constraint chk_quantity check (product_order.quantity >= 0);

# 编写java程序来执行sql语句
## Order类->订单编号、订单时间、订单总价、订单细节
## Product类->商品编号、商品名称、商品价格、商品库存
## Select类->查询商品表、查询订单表、查询订单商品表

### selectProductOrder方法->根据商品名称或商品id或商品编号查询与之相关的订单信息
#### 根据传递的是订单编号或者商品编号或商品名执行sql
##### 订单编号：select * from product_order where Order_id=?";
##### 商品编号：select * from product_order where Product_id=?";
##### 商品名：先通过商品名找到商品id，然后执行 select * from product_order where Order_id=?";

### selectProduct方法->根据商品名字查询商品信息
##### 根据参数来决定执行哪个sql语句
##### 参数是商品名时：执行 select * from product where Product_Name=?;
##### 参数是商品编号时：执行 select * from product where Product_id=?;
### selectOrder方法->根据订单编号查询订单信息
##### 执行 select * from order1 where Order_id=?

        
## Insert类->插入商品表、插入订单表、插入订单商品表
### insertProduct方法->插入商品信息
#### 先根据参数找到商品id、商品名、商品价格、商品库存，然后执行sql语句
#### 执行：insert into product values(id1,name1,price1,stock1);

### insertOrder方法->插入订单信息
#### 先根据参数找到订单编号、订单时间、订单总价，然后执行sql语句
##### 执行：insert into order1 values(id1,time1,price1);
#### 根据订单编号和商品编号插入订单商品信息
##### 执行：insert into product_order values(id1,pid1,num1);

### insertProductOrder方法->插入订单商品信息
#### 根据参数找到订单编号、商品编号、商品数量，然后执行sql语句
##### 执行：insert into product_order values(id1,pid1,num1);

## Update类->更新商品表、更新订单表、更新订单商品表
### updateNeed方法->更新商品需求量
#### 根据参数找到商品id、商品需求量，然后执行sql语句
##### 执行：update product set stock=stock-? where Product_id=?;

### updateStock方法->更新商品库存量
#### 根据参数找到商品id、商品库存量，然后执行sql语句
##### 执行：update product set stock=? where Product_id=?;
### updateProductOrder方法->更新订单商品信息
#### 根据参数找到订单编号、商品编号、商品数量，然后执行sql语句
##### 执行：update product_order set quantity=? where Order_id=? and Product_id=?;
## Delete类->删除商品表、删除订单表、删除订单商品表
### deleteProduct方法->删除商品信息
#### 根据参数找到商品id，然后执行sql语句
##### 执行：delete from product where Product_id=?;
##### 由于有些商品在订单中，无法删除，不在任何订单中的商品才能删除

### deleteOrder方法->删除订单信息
#### 根据参数找到订单编号，然后执行sql语句
##### 执行：delete from order1 where Order_id=?;
##### 由于设置级联，会同时删除product_order表中与之相关的信息

### deleteOroductOrder方法->删除订单商品信息
#### 根据参数找到订单编号、商品编号，然后执行sql语句
##### 执行：delete from product_order where Order_id=? and Product_id=?;

# 测试结果
## 编写insertTest,deleteTest,updateTest,selectTest来测试各个方法
