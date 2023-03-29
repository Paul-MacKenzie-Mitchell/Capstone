drop database if exists recipes_test;
create database recipes_test;
use recipes_test;

-- establishing tables and relationships
create table app_user (
	app_user_id int not null primary key auto_increment,
    username varchar(50) not null,
    password_hash varchar(2048) not null,
    enabled bit not null default 1,
    first_name varchar(75) not null,
    last_name varchar(75) not null,
    email varchar(320) not null,
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
    recipe_description varchar(500) not null,
    cook_time integer not null,
    prep_time integer not null,
    calories integer not null,
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
    food_name varchar(50) not null,
    amount decimal(7,2) not null,
    measurement_unit varchar(100) null,
    food_category varchar(100) not null,
    food_description varchar(500) not null
);

create table meal (
	meal_id int not null primary key auto_increment,
    `time` time(0) not null,
    meal_category varchar(50) null
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

-- creating known good state
delimiter //
create procedure set_known_good_state()
begin


	-- clearing current tables
    delete from meal_components;
	delete from meal;
    alter table meal auto_increment = 1;
    
    delete from ingredients;
    delete from food;
    alter table food auto_increment = 1;
    
    delete from recipe_tags;
    delete from tags;
    alter table tags auto_increment = 1;
    
    delete from recipebook;
    delete from recipe;
    alter table recipe auto_increment = 1;
    
	delete from app_user_role;
    delete from app_role;
    alter table app_role auto_increment = 1;
    delete from app_user;
    alter table app_user auto_increment = 1;
    
    
	-- inserting info for known good state
    
	-- NOTES: in individual entity tables (NOT bridge tables), test methods on each entry as follows:
		-- the first item (ID 1) should always be used to test the Update method
        -- the second item (ID 2) should always be used to test the Delete method
        -- the third itme (ID 3) should always be used to test the Find method
        -- to test your Add method, you can add a fourth item (it should end up with ID 4 if the auto_increment ID field is working correctly)
	-- see the first individual entity table below (app_user) for an example labeling each entry with the appropriate method it should be used to test\
    
    insert into app_user (username, password_hash, first_name, last_name, email, dob)
	values
		('appuser', 'p@ssw0rd', 'userfirst', 'userlast', 'user@user.com', '1998-01-01'), -- update
        ('appadmin', 'p@ssw0rd', 'adminfirst', 'adminlast', 'admin@admin.com', '2000-01-01'), -- delete
        ('adminuser', 'p@ssw0rd', 'adminuserfirst', 'adminuserlast', 'admin@admin.com', '2000-01-01'); -- find
        -- add
        
	insert into app_role (role_name)
    values
		('ADMIN'),
        ('USER');
        
	-- now I see why this may be an unnecessary/redundant table - should we make it so that every user can only have one role?
		-- e.g. no admin/users
	insert into app_user_role
    values
		(1, 1),
        (2, 2),
        (3, 1),
        (3, 2);
        
	insert into recipe (title, instructions, recipe_description, cook_time, prep_time, calories, servings, image_url)
    values
		('Chicken Alfredo Penne Pasta', 'chicken alfredo instructions', 'chicken alfredo penne pasta', 20, 15, 460, 6, 'https://img.buzzfeed.com/thumbnailer-prod-us-east-1/video-api/assets/9768.jpg'),
        ('Scrambled Eggs', 'scrambled eggs instructions', 'scrambled eggs', 5, 2, 100, 2, 'https://bellyfull.net/wp-content/uploads/2019/03/The-Best-Scrambled-Eggs-blog.jpg'),
        ('Marinated Italian Tomato Salad', 'tomato salad instructions', 'marinated italian tomato salad', 0, 10, 115, 4, 'https://loveandgoodstuff.com/wp-content/uploads/2019/07/italian-tomato-salad-6.jpg');
        
	insert into recipebook
    values
		(1, 1),
        (2, 2),
        (3, 3);
        
	insert into tags (tag_name, default_image)
    values
		('vegetarian', 'https://creazilla-store.fra1.digitaloceanspaces.com/emojis/57185/green-salad-emoji-clipart-md.png'),
        ('Italian', 'https://w7.pngwing.com/pngs/107/113/png-transparent-italian-cuisine-pasta-emoji-spaghetti-android-spaghetti-food-orange-cooking.png'),
        ('under 30 mins', 'https://creazilla-store.fra1.digitaloceanspaces.com/emojis/58241/alarm-clock-emoji-clipart-md.png');
	
    insert into recipe_tags
    values
		(1, 2),
        (2, 1),
        (2, 3),
        (3, 1),
        (3, 2);
        
	-- do we want to move the amount and measurement_unit columns into ingredients & meal_components tables?
	insert into food (food_name, amount, measurement_unit, food_category, food_description)
    values
		('chicken', 1.5, 'lbs', 'meat', 'chicken_description'),
        ('alfredo sauce', 1.0, 'cup', 'sauce', 'alfredo_description'),
        ('penne', 16.0, 'oz', 'pasta', 'penne_description'),
        ('egg', 2.0, null, 'egg', 'egg_description'),
        ('tomato', 3.0, null, 'vegetable', 'tomato_description'),
        ('red onion', 3.0, 'tbsp', 'vegetable', 'red_onion_description'),
        ('dressing', 6.5, 'tbsp', 'sauce', 'dressing_description'),
        ('fresh basil', 4.0, 'leaves', 'herb', 'basil_description'),
        ('fresh parsley', 1.0, 'tbsp', 'herb', 'parsley_description'),
        ('garlic', 4.0, 'cloves', 'vegetable', 'garlic_description'),
        ('salt', 0.5, 'tsp', 'seasoning', 'salt_description'),
        ('pepper', 1.0, 'pinch', 'seasoning', 'pepper_description'),
        ('apple', 1.0, null, 'fruit', 'apple_description');
        
	insert into ingredients
    values
		(1, 1),
        (1, 2),
        (1, 3),
        (2, 4),
        (3, 5),
        (3, 6),
        (3, 7),
        (3, 8),
        (3, 9),
        (3, 10),
        (3, 11),
        (3, 12);
	
    -- adding the user_id to the meal? otherwise how do we track whose meal it is?
    -- meal_category currently isn't nullable - do we want to make it nullable?
    insert into meal (`time`, meal_category)
    values
		('18:00:00', 'dinner'),
        ('08:00:00', 'breakfast'),
        ('12:00:00', 'lunch');
	
    insert into meal_components
    values
		(1, 1, null),
        (2, 2, null),
        (2, null, 13),
        (3, 3, null);
        

end //
delimiter ;

-- actual data (DELETE THIS when it comes time to test)
set sql_safe_updates = 0;
call set_known_good_state();
set sql_safe_updates = 1;
