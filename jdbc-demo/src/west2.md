# 建立表结构 
## 商品表 product 主键：product_id,product_Name
create table product (<br>
Product_id int not null comment '商品编号',<br>
Product_Name varchar(20) not null comment '商品名',<br>
Product_price double not null comment '商品价格',<br>
Product_Stock int default 0 comment '商品库存',<br>
primary key (Product_id, Product_Name)<br>
) comment '商品表';


## 订单表 order1  主键：order_id
create table order1 (<br>
Order_id int not null primary key comment '订单编号',<br>
Order_date date comment '订单日期',<br>
Order_price Double not null comment '订单价格'<br>
)comment '订单表';<br>


## 订单商品表 product_order 外键：order_id,product_id
create table product_order (<br>
Product_id int not null comment '商品编号',<br>
Order_id int not null comment '订单编号',<br>
quantity int not null comment '商品需求',<br>
foreign key (Product_id) references product(Product_id),<br>
foreign key (Order_id) references order1(Order_id)<br>
)comment '商品-订单表';

## 为部分属性添加check约束或not null约束
### 为product表添加约束
#### 设置商品id必须大于等于0
alter table product
add constraint chk_pid check (product.Product_id >= 0);
#### 设置商品价格必须大于等于0
alter table product
add constraint chk_price check (product.Product_Price >= 0);
#### 设置商品库存
alter table product
add constraint check_constraint_name check (product.Product_Stock >= 0);

### 为order1表添加约束
#### 设置订单编号必须大于等于0
alter table order1
add constraint chk_oid check (order1.Order_id >= 0);

#### 设置订单总价必须大于等于0        
alter table order1
add constraint chk_opr check (order1.Order_price >= 0);

### 为product_order表添加约束
#### 设置商品数量必须大于等于0
alter table product_order
add constraint chk_quantity check (product_order.quantity >= 0);

# 编写java程序来执行sql语句
## GetConn类->获取数据库连接
### getConn方法->获取数据库连接，调用此方法返回一个连接数据库的Connection对象
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
#### 根据订单编号查询订单商品信息（调用selectProductOrder并传递参数订单编号）
##### 执行 select * from product_order where Order_id=?;

        
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
