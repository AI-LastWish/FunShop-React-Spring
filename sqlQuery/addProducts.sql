insert into brand (`name`) values ('Apple');
insert into brand (`name`) values ('Cannon');
insert into brand (`name`) values ('Sony');
insert into brand (`name`) values ('Logitech');
insert into brand (`name`) values ('Amazon');

insert into category (`name`) values ('Electronics');

insert into product (`name`, `image`, `description`, `brand_id`, `price`, `count_in_stock`, `rating`, `num_reviews`)
values ('Airpods Wireless Bluetooth Headphones', '/images/airpods.jpg', 'Bluetooth technology lets you connect it with compatible devices wirelessly High-quality AAC audio offers immersive listening experience Built-in microphone allows you to take calls while working',
1, 89.99, 10, 4.5, 12);
insert into product (`name`, `image`, `description`, `brand_id`, `price`, `count_in_stock`, `rating`, `num_reviews`)
values ('iPhone 11 Pro 256GB Memory', '/images/phone.jpg', 'Introducing the iPhone 11 Pro. A transformative triple-camera system that adds tons of capability without complexity. An unprecedented leap in battery life',
1, 599.99, 7, 4.0, 8);
insert into product (`name`, `image`, `description`, `brand_id`, `price`, `count_in_stock`, `rating`, `num_reviews`)
values ('Cannon EOS 80D DSLR Camera', '/images/camera.jpg', 'Characterized by versatile imaging specs, the Canon EOS 80D further clarifies itself using a pair of robust focusing systems and an intuitive design',
2, 929.99, 5, 3, 12);
insert into product (`name`, `image`, `description`, `brand_id`, `price`, `count_in_stock`, `rating`, `num_reviews`)
values ('Sony Playstation 4 Pro White Versions', '/images/playstation.jpg', 'The ultimate home entertainment center starts with PlayStation. Whether you are into gaming, HD movies, television, music',
3, 399.99, 11, 5, 12);
insert into product (`name`, `image`, `description`, `brand_id`, `price`, `count_in_stock`, `rating`, `num_reviews`)
values ('Logitech G-Series Gaming Mouse', '/images/mouse.jpg', 'Get a better handle on your games with this Logitech LIGHTSYNC gaming mouse. The six programmable buttons allow customization for a smooth playing experience',
4, 49.99, 7, 3.5, 10);
insert into product (`name`, `image`, `description`, `brand_id`, `price`, `count_in_stock`, `rating`, `num_reviews`)
values ('Amazon Echo Dot 3rd Generation', '/images/alexa.jpg', 'Meet Echo Dot - Our most popular smart speaker with a fabric design. It is our most compact smart speaker that fits perfectly into small space',
5, 29.99, 0, 4, 12);

select * from product;

insert into product_category (`product_id`, `category_id`) values (1, 1);
insert into product_category (`product_id`, `category_id`) values (2, 1);
insert into product_category (`product_id`, `category_id`) values (3, 1);
insert into product_category (`product_id`, `category_id`) values (4, 1);
insert into product_category (`product_id`, `category_id`) values (5, 1);
insert into product_category (`product_id`, `category_id`) values (6, 1);