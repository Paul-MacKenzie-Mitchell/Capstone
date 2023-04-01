drop database if exists recipes;
create database recipes;
use recipes;

-- DDL
create table app_user (
	app_user_id int not null primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled bit not null default 1,
    first_name varchar(75) not null,
    last_name varchar(75) not null,
    email varchar(320) not null unique,
    dob date not null
);

create table app_role (
	app_role_id int not null primary key auto_increment,
    role_name varchar(50) not null unique
);

create table recipe (
	recipe_id int not null primary key auto_increment,
    title varchar(100) not null,
    instructions varchar(2048) not null,
    recipe_description varchar(500) null,
    cook_time integer not null,
    prep_time integer not null,
    calories integer null,
    servings integer not null,
    image_url varchar(2048) null
);

create table tags (
	tag_id int not null primary key auto_increment,
    tag_name varchar(25) not null unique,
    default_image varchar(2048) not null
);

create table food (
	food_id int not null primary key auto_increment,
    food_name varchar(50) not null unique,
    food_category varchar(100) not null,
    food_description varchar(500) not null
);

create table meal (
	meal_id int not null primary key auto_increment,
    app_user_id int not null,
    `date` date not null,
    `time` time(0) not null,
    meal_category varchar(50) null,
    constraint fk_meal_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id)
);

create table app_user_role (
	app_user_id int not null,
    app_role_id int not null default 1,
    constraint fk_app_user_role_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_app_user_role_app_role_id
		foreign key (app_role_id)
        references app_role(app_role_id)
);

create table recipebook (
	app_user_id int not null,
    recipe_id int not null,
    constraint pk_recipebook
		primary key (app_user_id, recipe_id),
    constraint fk_recipebook_app_user_id
		foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_recipebook_recipe_id
		foreign key (recipe_id)
        references recipe(recipe_id)
);

create table ingredients (
	recipe_id int not null,
    food_id int not null,
	amount decimal(7,2) not null,
    measurement_unit varchar(100) null,
    constraint fk_ingredients_recipe_id
		foreign key (recipe_id)
        references recipe(recipe_id),
	constraint fk_ingredients_food_id
		foreign key (food_id)
        references food(food_id)
);

create table meal_components (
	meal_id int not null,
    recipe_id int null,
    food_id int null,
	amount decimal(7,2) not null,
    measurement_unit varchar(100) null,
    constraint fk_meal_components_meal_id
		foreign key (meal_id)
        references meal(meal_id),
	constraint fk_meal_components_recipe_id
		foreign key (recipe_id)
        references recipe(recipe_id),
	constraint fk_meal_components_food_id
		foreign key (food_id)
		references food(food_id),
	constraint uq_meal_id_recipe_id
		unique (meal_id, recipe_id),
	constraint uq_meal_id_food_id
		unique (meal_id, food_id)
);

create table recipe_tags (
	recipe_id int not null,
    tag_id int not null,
    constraint fk_recipe_tags_recipe_id
		foreign key (recipe_id)
        references recipe(recipe_id),
	constraint fk_recipe_tags_tag_id
		foreign key (tag_id)
        references tags(tag_id)
);

-- DML (default data insertion)

insert into app_user (username, password_hash, first_name, last_name, email, dob)
	values
		-- user 1 (password: mwenge)
		('mmaliro', 
			'$2a$12$zYzSWHD1kbz0J/CIFoeB2u47jx/K2J1KCRDGTmWIhGU.Jun0X3.ve', 
            'Mwenge', 
            'Maliro', 
            'mwenge@maliro.com', 
            '2000-01-01'), 
		-- user 2 (password: hanako)
        ('hlb2000', 
			'$2a$12$I/vtDT1qSkGCdWpbOl32ZOJQk7rZHvpweShQr.AtQpE5/eeIWcMuO', 
            'Hanako', 
            'Boucher', 
            'hanako@boucher.com', 
            '2000-12-31'), 
		-- admin (password: paul)
        ('pm', 
			'$2a$12$LTkdX5A697u/57UvDNj14ekTGCbu9tFH5IwaEsuFalEWL91uQGI2C', 
			'Paul', 
			'Mitchell', 
			'paul@mitchell.com', 
			'1990-01-01');

insert into app_role (role_name) values ('USER'), ('ADMIN');

insert into app_user_role (app_user_id, app_role_id)
	values
		(1, 1),
        (2, 1),
        (3, 1),
        (3, 2);
        
select * from app_user au left outer join app_user_role aur on aur.app_user_id = au.app_user_id;
select * from app_role;


