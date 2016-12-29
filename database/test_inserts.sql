USE ecoteh;

/*--- 1) Photos -------------------------------------------------------------------------------*/

INSERT INTO `photos` (id, title, url) VALUES
  (1, 'Yurii Salimov', 'users/yurii_salimov.jpg'),

  (2, 'Section 1 photo', 'sections/section_1_photo.jpg'),
  (3, 'Section 2 photo', 'sections/section_2_photo.jpg'),
  (4, 'Section 3 photo', 'sections/section_3_photo.jpg'),
  (5, 'Section 4 photo', 'sections/section_4_photo.jpg'),
  (6, 'Section 5 photo', 'sections/section_5_photo.jpg'),

  (7, 'Category 1 photo', 'categories/category_1_photo.jpg'),
  (8, 'Category 2 photo', 'categories/category_2_photo.jpg'),
  (9, 'Category 3 photo', 'categories/category_3_photo.jpg'),
  (10, 'Category 4 photo', 'categories/category_4_photo.jpg'),
  (11, 'Category 5 photo', 'categories/category_5_photo.jpg'),
  (12, 'Category 6 photo', 'categories/category_6_photo.jpg'),
  (13, 'Category 7 photo', 'categories/category_7_photo.jpg'),
  (14, 'Category 8 photo', 'categories/category_8_photo.jpg'),
  (15, 'Category 9 photo', 'categories/category_9_photo.jpg'),
  (16, 'Category 10 photo', 'categories/category_10_photo.jpg'),

  (17, 'Article 1 main photo', 'articles/main_photo_1.jpg'),
  (18, 'Article 1 photo 1', 'articles/article_1_photo_1.jpg'),
  (19, 'Article 1 photo 2', 'articles/article_1_photo_2.jpg'),
  (20, 'Article 1 photo 3', 'articles/article_1_photo_3.jpg'),

  (21, 'Article 2 main photo', 'articles/main_photo_2.jpg'),
  (22, 'Article 2 photo 1', 'articles/article_2_photo_1.jpg'),
  (23, 'Article 2 photo 2', 'articles/article_2_photo_2.jpg'),
  (24, 'Article 2 photo 3', 'articles/article_2_photo_3.jpg'),

  (25, 'Article 3 main photo', 'articles/main_photo_3.jpg'),
  (26, 'Article 3 photo 1', 'articles/article_3_photo_1.jpg'),
  (27, 'Article 3 photo 2', 'articles/article_3_photo_2.jpg'),
  (28, 'Article 3 photo 3', 'articles/article_3_photo_3.jpg'),

  (29, 'Article 4 main photo', 'articles/main_photo_4.jpg'),
  (30, 'Article 4 photo 1', 'articles/article_4_photo_1.jpg'),
  (31, 'Article 4 photo 2', 'articles/article_4_photo_2.jpg'),
  (32, 'Article 4 photo 3', 'articles/article_4_photo_3.jpg'),

  (33, 'Article 5 main photo', 'articles/main_photo_5.jpg'),
  (34, 'Article 5 photo 1', 'articles/article_5_photo_1.jpg'),
  (35, 'Article 5 photo 2', 'articles/article_5_photo_2.jpg'),
  (36, 'Article 5 photo 3', 'articles/article_5_photo_3.jpg'),

  (37, 'Logo', 'company/logo.png'),
  (38, 'Favicon', 'company/favicon.png'),
  (39, 'Slide 1', 'company/slide-1.jpg'),
  (40, 'Slide 2', 'company/slide-2.jpg'),
  (41, 'Slide 3', 'company/slide-3.jpg'),

  (42, 'Mr. Alex Logo', 'company/mr_alex.png'),
  (43, 'Logo logo', 'company/another-logo.png'),
  (44, 'Apple Logo', 'company/apple.jpg'),
  (45, 'Batman Logo', 'company/batman.gif'),
  (46, 'Beats Logo', 'company/beats.png'),
  (47, 'Fanta Logo', 'company/fanta.jpg'),

  (48, 'Article 20 main photo', 'articles/main_photo_20.jpg'),
  (49, 'Article 21 main photo', 'articles/main_photo_21.jpg'),
  (50, 'Article 22 main photo', 'articles/main_photo_22.jpg'),
  (51, 'Article 23 main photo', 'articles/main_photo_23.jpg'),
  (52, 'Article 24 main photo', 'articles/main_photo_24.jpg');

/*--- 2) Videos -------------------------------------------------------------------------------*/

INSERT INTO `videos` (id, title, url) VALUES
  (1, 'some_video', 'https://www.youtube.com/embed/IB_3jZA3lxo'),
  (2, 'loboda', 'https://www.youtube.com/embed/aZirbtZKOu0'),
  (3, 'some_video', 'https://www.youtube.com/embed/IB_3jZA3lxo'),
  (4, 'loboda', 'https://www.youtube.com/embed/aZirbtZKOu0'),
  (5, 'some_video', 'https://www.youtube.com/embed/IB_3jZA3lxo'),
  (6, 'loboda', 'https://www.youtube.com/embed/aZirbtZKOu0');
;

/*--- 3) Users -------------------------------------------------------------------------------*/

INSERT INTO `users` (
  id,
  role,
  name,
  url,
  login,
  password,
  email,
  phone,
  vkontakte,
  facebook,
  twitter,
  skype,
  description,
  photo_id,
  is_valid,
  is_mailing,
  is_locked
) VALUES
  (
    1,
    'ADMIN',
    'Юрий Салимов',
    'yurii_salimov',
    'login',
    'password',
    'yuriy.alex.salimov@gmail.com',
    '+380637399290',
    'yurii.alex.salimov',
    'yurii.alex.salimov',
    NULL,
    'slayerskif',
    'Администратор сайта',
    1,
    1,
    1,
    0
  );

/*--- 4) Sections -------------------------------------------------------------------------------*/

INSERT INTO `sections` (
  id,
  title,
  url,
  description,
  keywords,
  photo_id,
  is_valid
) VALUES
  (
    1,
    'Section 1',
    'section_1',
    'Some description company section 1. Some description company section 1. Some description company section 1.
    Some description company section 1. Some description company section 1. Some description company section 1.',
    'sections 1 keywords',
    2,
    1
  ),
  (
    2,
    'Section 2',
    'section_2',
    'Some description company section 2. Some description company section 2. Some description company section 2.
    Some description company section 2. Some description company section 2. Some description company section 2.',
    'sections 2 keywords',
    3,
    1
  ),
  (
    3,
    'Section 3',
    'section_3',
    'Some description company section 3. Some description company section 3. Some description company section 3.
    Some description company section 3. Some description company section 3. Some description company section 3.',
    'sections 3 keywords',
    4,
    1
  ),
  (
    4,
    'Section 4',
    'section_4',
    'Some description company section 4. Some description company section 4. Some description company section 4.
    Some description company section 4. Some description company section 4. Some description company section 4.',
    'sections 4 keywords',
    5,
    1
  ),
  (
    5,
    'Section 5',
    'section_5',
    'Some description company section 5. Some description company section 5. Some description company section 5.
    Some description company section 5. Some description company section 5. Some description company section 5.',
    'sections 5 keywords',
    NULL,
    1
  ),
  (
    6,
    'Section 6',
    'section_6',
    'Some description company section 6. Some description company section 6. Some description company section 6.
    Some description company section 6. Some description company section 6. Some description company section 6.',
    'sections 6 keywords',
    NULL,
    0
  );

/*--- 5) Categories -------------------------------------------------------------------------------*/

INSERT INTO `categories` (
  id,
  title,
  url,
  description,
  keywords,
  photo_id,
  section_id,
  is_valid
) VALUES
  (
    1,
    'Category 1',
    'category_1',
    'Some description company category 1.',
    'category 1 keywords',
    7,
    1,
    1
  ),
  (2, 'Category 2', 'category_2', 'Some description company category 2.', 'category 2 keywords', 8, 1, 1),
  (3, 'Category 3', 'category_3', 'Some description company category 3.', 'category 3 keywords', 9, 1, 1),
  (4, 'Category 4', 'category_4', 'Some description company category 4.', 'category 4 keywords', 10, 1, 1),
  (5, 'Category 5', 'category_5', 'Some description company category 5.', 'category 5 keywords', 11, 1, 1),

  (6, 'Category 6', 'category_6', 'Some description company category 6.', 'category 6 keywords', 12, 2, 1),
  (7, 'Category 7', 'category_7', 'Some description company category 7.', 'category 7 keywords', 13, 2, 1),
  (8, 'Category 8', 'category_8', 'Some description company category 8.', 'category 8 keywords', 14, 2, 1),
  (9, 'Category 9', 'category_9', 'Some description company category 9.', 'category 9 keywords', 15, 2, 1),
  (10, 'Category 10', 'category_10', 'Some description company category 10.', 'category 10 keywords', 16, 2, 1),

  (11, 'Category 11', 'category_11', 'Some description company category 11.', 'category 11 keywords', NULL, 3, 0),
  (12, 'Category 12', 'category_12', 'Some description company category 12.', 'category 12 keywords', NULL, 3, 1),
  (13, 'Category 13', 'category_13', 'Some description company category 13.', 'category 13 keywords', NULL, 3, 0),
  (14, 'Category 14', 'category_14', 'Some description company category 14.', 'category 14 keywords', NULL, 3, 1),
  (15, 'Category 15', 'category_15', 'Some description company category 15.', 'category 15 keywords', NULL, 3, 0),

  (16, 'Category 16', 'category_16', 'Some description company category 16.', 'category 16 keywords', NULL, 4, 1),
  (17, 'Category 17', 'category_17', 'Some description company category 17.', 'category 17 keywords', NULL, 4, 0),
  (18, 'Category 18', 'category_18', 'Some description company category 18.', 'category 18 keywords', NULL, 4, 1),
  (19, 'Category 19', 'category_19', 'Some description company category 19.', 'category 19 keywords', NULL, 4, 0),
  (20, 'Category 20', 'category_20', 'Some description company category 20.', 'category 20 keywords', NULL, 4, 1),

  (21, 'Category 21', 'category_21', 'Some description company category 21.', 'category 21 keywords', NULL, 5, 0),
  (22, 'Category 22', 'category_22', 'Some description company category 22.', 'category 22 keywords', NULL, 5, 1),
  (23, 'Category 23', 'category_23', 'Some description company category 23.', 'category 23 keywords', NULL, 5, 0),
  (24, 'Category 24', 'category_24', 'Some description company category 24.', 'category 24 keywords', NULL, 5, 1),
  (25, 'Category 25', 'category_25', 'Some description company category 25.', 'category 25 keywords', NULL, 5, 0),

  (26, 'Category 26', 'category_26', 'Some description company category 26.', 'category 26 keywords', NULL, 6, 1),
  (27, 'Category 27', 'category_27', 'Some description company category 27.', 'category 27 keywords', NULL, 6, 0),
  (28, 'Category 28', 'category_28', 'Some description company category 28.', 'category 28 keywords', NULL, 6, 1),
  (29, 'Category 29', 'category_29', 'Some description company category 29.', 'category 29 keywords', NULL, NULL, 0),
  (30, 'Category 30', 'category_30', 'Some description company category 30.', 'category 30 keywords', NULL, NULL, 0);

/*--- 6) Articles -------------------------------------------------------------------------------*/

INSERT INTO `articles` (
  id, title, url, number, keywords, description, text, date, main_photo_id, category_id, is_valid
) VALUES (
  1, 'Article 1', 'article_1', 10001, 'article 1 keywords',
     'Description 1 Description 1 Description 1 Description 1 Description 1 Description 1 Description 1
      Description 1 Description 1 Description 1 Description 1 Description 1 Description 1 Description 1
      Description 1 Description 1 Description 1 Description 1 Description 1 Description 1 Description 1',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-09-06 12:18:00', 17, 1, 1
), (
  2, 'Article 2', 'article_2', 20002, 'article 2 keywords',
     'Description 2 Description 2 Description 2 Description 2 Description 2 Description 2 Description 2
      Description 2 Description 2 Description 2 Description 2 Description 2 Description 2 Description 2
      Description 2 Description 2 Description 2 Description 2 Description 2 Description 2 Description 2',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-09-05 20:17:04', 21, 2, 1
), (
  3, 'Article 3', 'article_3', 30003, 'article 3 keywords',
     'Description 3 Description 3 Description 3 Description 3 Description 3 Description 3 Description 3
      Description 3 Description 3 Description 3 Description 3 Description 3 Description 3 Description 3
      Description 3 Description 3 Description 3 Description 3 Description 3 Description 3 Description 3',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-09-04 01:44:14', 25, 3, 1
), (
  4, 'Article 4', 'article_4', 40004, 'article 4 keywords',
     'Description 4 Description 4 Description 4 Description 4 Description 4 Description 4 Description 4
      Description 4 Description 4 Description 4 Description 4 Description 4 Description 4 Description 4
      Description 4 Description 4 Description 4 Description 4 Description 4 Description 4 Description 4',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-09-03 11:41:11', 29, 4, 1
), (
  5, 'Article 5', 'article_5', 50005, 'article 5 keywords',
     'Description 5 Description 5 Description 5 Description 5 Description 5 Description 5 Description 5
      Description 5 Description 5 Description 5 Description 5 Description 5 Description 5 Description 5
      Description 5 Description 5 Description 5 Description 5 Description 5 Description 5 Description 5',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-09-02 15:53:53', 33, 5, 1
), (
  6, 'Article 6', 'article_6', 60006, 'article 6 keywords',
     'Description 6 Description 6 Description 6 Description 6 Description 6 Description 6 Description 6
      Description 6 Description 6 Description 6 Description 6 Description 6 Description 6 Description 6
      Description 6 Description 6 Description 6 Description 6 Description 6 Description 6 Description 6',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-09-01 14:44:01', NULL, 6, 0
), (
  7, 'Article 7', 'article_7', 70007, 'article 7 keywords',
     'Description 7 Description 7 Description 7 Description 7 Description 7 Description 7 Description 7
      Description 7 Description 7 Description 7 Description 7 Description 7 Description 7 Description 7
      Description 7 Description 7 Description 7 Description 7 Description 7 Description 7 Description 7',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-08-07 14:53:00', NULL, 7, 0
), (
  8, 'Article 8', 'article_8', 80008, 'article 8 keywords',
     'Description 8 Description 8 Description 8 Description 8 Description 8 Description 8 Description 8
      Description 8 Description 8 Description 8 Description 8 Description 8 Description 8 Description 8
      Description 8 Description 8 Description 8 Description 8 Description 8 Description 8 Description 8',
     'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
      Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
     '2016-08-08 14:53:00', NULL, 8, 1
),

  (9, 'Article 9', 'article_9', 90009, 'article 9 keywords',
      'Description 9 Description 9 Description 9 Description 9 Description 9 Description 9 Description 9
       Description 9 Description 9 Description 9 Description 9 Description 9 Description 9 Description 9
       Description 9 Description 9 Description 9 Description 9 Description 9 Description 9 Description 9',
      'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
       Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
      '2016-08-09 14:53:00', NULL, 9, 0),

  (10, 'Article 10', 'article_10', 10010, 'article 10 keywords',
       'Description 10 Description 10 Description 10 Description 10 Description 10 Description 10 Description 10
        Description 10 Description 10 Description 10 Description 10 Description 10 Description 10 Description 10
        Description 10 Description 10 Description 10 Description 10 Description 10 Description 10 Description 10',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-10 14:53:00', NULL, 10, 1),

  (11, 'Article 11', 'article_11', 11011, 'article 11 keywords',
       'Description 11 Description 11 Description 11 Description 11 Description 11 Description 11 Description 11
        Description 11 Description 11 Description 11 Description 11 Description 11 Description 11 Description 11
        Description 11 Description 11 Description 11 Description 11 Description 11 Description 11 Description 11',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-11 14:53:00', NULL, 11, 0),

  (12, 'Article 12', 'article_12', 12012, 'article 12 keywords',
       'Description 12 Description 12 Description 12 Description 12 Description 12 Description 12 Description 12
        Description 12 Description 12 Description 12 Description 12 Description 12 Description 12 Description 12
        Description 12 Description 12 Description 12 Description 12 Description 12 Description 12 Description 12',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-12 14:53:00', NULL, 12, 1),

  (13, 'Article 13', 'article_13', 13013, 'article 13 keywords',
       'Description 13 Description 13 Description 13 Description 13 Description 13 Description 13 Description 13
        Description 13 Description 13 Description 13 Description 13 Description 13 Description 13 Description 13
        Description 13 Description 13 Description 13 Description 13 Description 13 Description 13 Description 13',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-13 14:53:00', NULL, 13, 0),

  (14, 'Article 14', 'article_14', 14014, 'article 14 keywords',
       'Description 14 Description 14 Description 14 Description 14 Description 14 Description 14 Description 14
        Description 14 Description 14 Description 14 Description 14 Description 14 Description 14 Description 14
        Description 14 Description 14 Description 14 Description 14 Description 14 Description 14 Description 14',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-14 14:53:00', NULL, 14, 1),

  (15, 'Article 15', 'article_15', 15015, 'article 15 keywords',
       'Description 15 Description 15 Description 15 Description 15 Description 15 Description 15 Description 15
        Description 15 Description 15 Description 15 Description 15 Description 15 Description 15 Description 15
        Description 15 Description 15 Description 15 Description 15 Description 15 Description 15 Description 15',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-15 14:53:00', NULL, 15, 0),

  (16, 'Article 16', 'article_16', 16016, 'article 16 keywords',
       'Description 16 Description 16 Description 16 Description 16 Description 16 Description 16 Description 16
        Description 16 Description 16 Description 16 Description 16 Description 16 Description 16 Description 16
        Description 16 Description 16 Description 16 Description 16 Description 16 Description 16 Description 16',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-16 14:53:00', NULL, 16, 1),

  (17, 'Article 17', 'article_17', 17017, 'article 17 keywords',
       'Description 17 Description 17 Description 17 Description 17 Description 17 Description 17 Description 17
        Description 17 Description 17 Description 17 Description 17 Description 17 Description 17 Description 17
        Description 17 Description 17 Description 17 Description 17 Description 17 Description 17 Description 17',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-17 14:53:00', NULL, 17, 0),

  (18, 'Article 18', 'article_18', 18018, 'article 18 keywords',
       'Description 18 Description 18 Description 18 Description 18 Description 18 Description 18 Description 18
        Description 18 Description 18 Description 18 Description 18 Description 18 Description 18 Description 18
        Description 18 Description 18 Description 18 Description 18 Description 18 Description 18 Description 18',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-18 14:53:00', NULL, 18, 1),

  (19, 'Article 19', 'article_19', 19019, 'article 19 keywords',
       'Description 19 Description 19 Description 19 Description 19 Description 19 Description 19 Description 19
        Description 19 Description 19 Description 19 Description 19 Description 19 Description 19 Description 19
        Description 19 Description 19 Description 19 Description 19 Description 19 Description 19 Description 19',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-19 14:53:00', NULL, 19, 0),

  (20, 'Article 20', 'article_20', 20020, 'article 20 keywords',
       'Description 20 Description 20 Description 20 Description 20 Description 20 Description 20 Description 20
        Description 20 Description 20 Description 20 Description 20 Description 20 Description 20 Description 20
        Description 20 Description 20 Description 20 Description 20 Description 20 Description 20 Description 20',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-20 14:53:00', 48, 20, 1),

  (21, 'Article 21', 'article_21', 21021, 'article 21 keywords',
       'Description 21 Description 21 Description 21 Description 21 Description 21 Description 21 Description 21
        Description 21 Description 21 Description 21 Description 21 Description 21 Description 21 Description 21
        Description 21 Description 21 Description 21 Description 21 Description 21 Description 21 Description 21',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-21 14:53:00', 49, 21, 1),

  (22, 'Article 22', 'article_22', 22022, 'article 22 keywords',
       'Description 22 Description 22 Description 22 Description 22 Description 22 Description 22 Description 22
        Description 22 Description 22 Description 22 Description 22 Description 22 Description 22 Description 22
        Description 22 Description 22 Description 22 Description 22 Description 22 Description 22 Description 22',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-22 14:53:00', 50, 22, 1),

  (23, 'Article 23', 'article_23', 23023, 'article 23 keywords',
       'Description 23 Description 23 Description 23 Description 23 Description 23 Description 23 Description 23
        Description 23 Description 23 Description 23 Description 23 Description 23 Description 23 Description 23
        Description 23 Description 23 Description 23 Description 23 Description 23 Description 23 Description 23',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-23 14:53:00', 51, 23, 1),

  (24, 'Article 24', 'article_24', 24024, 'article 24 keywords',
       'Description 24 Description 24 Description 24 Description 24 Description 24 Description 24 Description 24
        Description 24 Description 24 Description 24 Description 24 Description 24 Description 24 Description 24
        Description 24 Description 24 Description 24 Description 24 Description 24 Description 24 Description 24',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-24 14:53:00', 52, 1, 0),

  (25, 'Article 25', 'article_25', 25025, 'article 25 keywords',
       'Description 25 Description 25 Description 25 Description 25 Description 25 Description 25 Description 25
        Description 25 Description 25 Description 25 Description 25 Description 25 Description 25 Description 25
        Description 25 Description 25 Description 25 Description 25 Description 25 Description 25 Description 25',
       'Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text
        Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text',
       '2016-08-25 14:53:00', NULL, 1, 0);

/*--- 7) Companies -------------------------------------------------------------------------------*/

INSERT INTO `companies` (
  id, type, title, domain, url, tagline, description, information, advantages, mobile_phone,
  landline_phone, fax, email, sender_email, sender_pass, vkontakte, facebook, twitter, skype,
  address, keywords, google_maps, work_time_from, work_time_to, logo_id, favicon_id
) VALUE
  (1, 'MAIN', 'ТЕРМО ДРУК', 'ecoteh.com.ua', 'ecoteh_com_ua',
      'Проектирование, изготовление и продажа маркировочного оборудования, комплектующие расходные материалы',
      'Частное Предприятие «ТЕРМО ДРУК». Наша компания работает на рынке Украины с 2006 года.',
      'information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information',
      'Основное направление деятельности  - продажа, проектирование, изготовление, сервисное обслуживание,
модернизация промышленного оборудования для контакной маркировки упаковки, а также продажа расходного материала
к этому обрудованию.
НОВОЕ НАПРАВЛЕНИЕ: изготовление символов для печати контактными принтерами из магния, латуни и стали.
Модернизация  принтеров горячего тиснения разных производителей под наш стандарт символа.
',
      '+38(000)000-00-00', '+38(000)000-00-00', '+38(000)000-00-00', 'email', 'email',
                                                'password',
                                                'ecoteh', 'ecoteh', 'ecoteh', 'ecoteh',
                                                'Украина, г. Киев, ул. Метрологическая, 14Б/1 (Институт Теоретической Физики, р-н парк Феофания), оф. 118.',
                                                'Термо Друк, маркировочное обордование, комплектующие расходные материалы, Контактная маркировка, лента горячего тиснения CFP 11,CFP10N, термострансферная лента (риббон) AG7,AG14,AG51,AG74, AGT96, Принтер TREIP, оборудование ECOprint, горячее тиснение, клише латунное, клише магниевое, клише стальное, изготовление символов для контакной маркировки, символ фигурный  еко-евро для печати даты, символы для печати и маркировки',
   'https://www.google.com/maps/embed?pb=!1m28!1m12!1m3!1d28805.34244494352!2d30.46526134624451!3d50.34684927580468!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!4m13!3e0!4m5!1s0x0%3A0x52ed6d3e8dada0d0!2z0KLQoNCmINCc0LDQs9C10LvQsNC9!3m2!1d50.367931999999996!2d30.458164999999997!4m5!1s0x40d4c80ac39ede4f%3A0x4e8d167c8a9aaf0e!2zMTRCLzEsINCy0YPQu9C40YbRjyDQnNC10YLRgNC-0LvQvtCz0ZbRh9C90LAsIDE00JEvMSwg0JrQuNGX0LI!3m2!1d50.339434999999995!2d30.4807195!5e0!3m2!1sru!2sua!4v1473843480450',
   '09:00', '17:00', 37, 38),

  (2, 'PARTNER', 'Mr. Alex', 'alex.com', 'alex_com',
      'Лучшее агенство по написанию сайтов',
      'description description description description description description description description description
      description description description description description description description description description
      description description description description description description description description description
      description description description description description description description description description',
      'information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information',
      NULL, '+38(063)160-01-18', NULL, NULL, 'info@alex.com', NULL, NULL,
                                       'yurii.alex.salimov', 'yurii.alex.salimov', NULL, 'yurii.salimov',
                                       'Украина, г. Киев, ул. Михаила Ломоносова, 55 (5 мин ходьбы от метро Выставочный центр)',
                                       'Написание сайтов, Java, HTML, CSS',
   'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254',
   '09:00', '17:00', 42, NULL),

  (3, 'PARTNER', 'Logo', 'logo.com', 'logo_com',
      'Лучшее агенство по написанию сайтов',
      'description description description description description description description description description
      description description description description description description description description description',
      'information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information',
      NULL, '+38(063)160-01-18', NULL, NULL, 'info@alex.com', NULL, NULL,
                                       'yurii.alex.salimov', 'yurii.alex.salimov', NULL, 'yurii.salimov',
                                       'Украина, г. Киев, ул. Михаила Ломоносова, 55 (5 мин ходьбы от метро Выставочный центр)',
                                       'Термо Друк, маркировочное обордование, комплектующие расходные материалы',
   'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254',
   '09:00', '17:00', 43, NULL),

  (4, 'PARTNER', 'Apple', 'apple.com', 'apple_com',
      'Лучшее агенство по написанию сайтов',
      'description description description description description description description description description
      description description description description description description description description description',
      'information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information',
      NULL, '+38(063)160-01-18', NULL, NULL, 'info@alex.com', NULL, NULL,
                                       'yurii.alex.salimov', 'yurii.alex.salimov', NULL, 'yurii.salimov',
                                       'Украина, г. Киев, ул. Михаила Ломоносова, 55 (5 мин ходьбы от метро Выставочный центр)',
                                       'Термо Друк, маркировочное обордование, комплектующие расходные материалы',
   'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254',
   '09:00', '17:00', 44, NULL),

  (5, 'PARTNER', 'Batman', 'batman.com', 'batman_com',
      'Лучшее агенство по написанию сайтов',
      'description description description description description description description description description
      description description description description description description description description description',
      'information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information',
      NULL, '+38(063)160-01-18', NULL, NULL, 'info@alex.com', NULL, NULL,
                                       'yurii.alex.salimov', 'yurii.alex.salimov', NULL, 'yurii.salimov',
                                       'Украина, г. Киев, ул. Михаила Ломоносова, 55 (5 мин ходьбы от метро Выставочный центр)',
                                       'Термо Друк, маркировочное обордование, комплектующие расходные материалы',
   'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254',
   '09:00', '17:00', 45, NULL),

  (6, 'PARTNER', 'Beats', 'beats.com', 'beats_com',
      'Лучшее агенство по написанию сайтов',
      'description description description description description description description description description
      description description description description description description description description description',
      'information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information
      information information information information information information information information information',
      NULL, '+38(063)160-01-18', NULL, NULL, 'info@alex.com', NULL, NULL,
                                       'yurii.alex.salimov', 'yurii.alex.salimov', NULL, 'yurii.salimov',
                                       'Украина, г. Киев, ул. Михаила Ломоносова, 55 (5 мин ходьбы от метро Выставочный центр)',
                                       'Термо Друк, маркировочное обордование, комплектующие расходные материалы',
   'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254',
   '09:00', '17:00', 46, NULL),

  (47, 'PARTNER', 'Fanta', 'C.com', 'V_com',
       'Лучшее агенство по написанию сайтов',
       'description description description description description description description description description
       description description description description description description description description description',
       'information information information information information information information information information
       information information information information information information information information information
       information information information information information information information information information
       information information information information information information information information information
       information information information information information information information information information',
       NULL, '+38(063)160-01-18', NULL, NULL, 'info@alex.com', NULL, NULL,
                                        'yurii.alex.salimov', 'yurii.alex.salimov', NULL, 'yurii.salimov',
                                        'Украина, г. Киев, ул. Михаила Ломоносова, 55 (5 мин ходьбы от метро Выставочный центр)',
                                        'Термо Друк, маркировочное обордование, комплектующие расходные материалы',
   'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d12101.231188451986!2d30.46496854249532!3d50.38574881210299!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4c8e75044d5f9%3A0xb47cad6bc4ff220a!2z0LLRg9C70LjRhtGPINCc0LjRhdCw0LnQu9CwINCb0L7QvNC-0L3QvtGB0L7QstCwLCA1NSwg0JrQuNGX0LI!5e0!3m2!1sru!2sua!4v1473607244254',
   '09:00', '17:00', 47, NULL);

/*--- 8) Article photo -------------------------------------------------------------------------------*/

INSERT INTO `article_photo` (article_id, photo_id) VALUES
  (1, 18), (1, 19), (1, 20),
  (2, 22), (2, 23), (2, 24),
  (3, 26), (3, 27), (3, 28),
  (4, 30), (4, 31), (4, 32),
  (5, 34), (5, 35), (5, 36);

/*--- 9) Article video -------------------------------------------------------------------------------*/

INSERT INTO `article_video` (article_id, video_id) VALUES
  (1, 1), (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 6);

/*--- 10) Company photo -------------------------------------------------------------------------------*/

INSERT INTO `company_photo` (company_id, photo_id) VALUES
  (1, 39), (1, 40), (1, 41);

/*--- 11) Responses -------------------------------------------------------------------------------*/

INSERT INTO `responses` (id, username, date, text, is_valid) VALUES
  (1, 'Станислав Васильевич', '2016-06-09 14:53:00',
   'STUDY go помогли мне найти репетитора для сына (сейчас 9 клас). Готовимся к ДПА по математике. Ему очень нравится и мы очень довольны преподавателем.',
   1),

  (2, 'Юрий Александрович', '2016-10-12 16:31:15',
   'Как на меня, то эта компания - полная дичь. Персонал не адекватный совсем. Этот ихний Вован предлагал мне взятку.',
   0),

  (3, 'Василий Станиславович', '2016-10-12 19:53:00',
   'Готовимся с сыном к ДПА по математике. Ему очень нравится преподаватель.',
   1),

  (4, 'Геннадий', '2016-08-16 15:23:15',
   'Мы с Владимиром Анатольевичем занимались около двух месяцев. Ребёнку репетитор понравился, Константин
   был в восторге, поэтому с большим удовольствием ходил к нему на уроки. Я поставила определенную задачу -
   решать задачи олимпиадного уровня. Мальчик стал с огромным энтузиазмом решать задания. Я вижу реальные
   улучшения в учёбе сына, теперь из 10 задач он решает половину, а до этого решал всего 1-2. Хочу добавить,
   что Владимир Анатольевич занимается с теми ребятами, которым это на самом деле интересно. И мне нравится,
   что он не говорит сразу решение задач, а задает наводящие вопросы в нужном направлении, учит думать.',
   0),

  (5, 'Надежда', '2016-10-12 16:31:18',
   'До занятий с Александром Витальевичем у Екатерины был высокий уровень знаний по физике. Сейчас он практически
   "звездный". У репетитора системный подход в обучении, четкая стратегия преподавания. Александр Витальевич меняет
   представление ученика о предмете, учит детей любить физику через понимание ее основ и законов.',
   1),

  (6, 'Василий Станиславович', '2016-10-12 19:53:40',
   'Александра прекрасный репетитор! До занятий не понимала ничего! После занятий начала понимать,
   что от меня требуется сделать! Вообще, физика всегда шла с трудом, Александра помогает преодолеть этот барьер.
   Все очень подробно рассказывается, при надобности повторяется еще 10 раз, без всяких истерик и ругательства,
   как это обычно бывает! Сначала ходила на занятия одна, потом стали приходить с подругой, она тоже ничего не понимала!
   Александра - чудесный человек! Ответственный, доброжелательны и самое главное понимающий специалист!',
   0),

  (7, 'Ангелина', '2016-07-10 14:53:00',
   'Хочу от всей души поблагодарить Станислава Львовича в помощи к подготовке к ЕГЭ.
   Несмотря на то, что у меня не было достаточно времени на подготовку, Станислав Львович сумел объяснить и разобрать со мной огромное количество материала.
   Я закончила школу 8 лет назад и только в 25 решилась поступать в медицинский университет.К Станиславу Львовичу я пришла за несколько месяцев до экзамена,
   и он сумел поднять мой уровень знаний по химии с нуля до достаточно конкуретноспособного.
   Признаться, я очень давно не видела учителей настолько преданных своей работе. У Станислава Львовича своя методика обучения, он стремится развить логику
    у ученика, искать обходные пути в решении задач, всегда подскажет, на что нужно обратить особенное внимание, очень заинтересовывает предметом.
   Станислав Львович очень добрый и ответственный человек. Даже после окончания обучения, он поинтересовался результатом, изучил и разобрал скан моей работы.
   Я очень рада, что обучалась у Станислава Львовича, благодаря ему моя мечта сбылась, и теперь я студентка медицинского университета.',
   1),

  (8, 'Мария', '2016-10-12 17:31:15',
   'Хотела бы выразить огромную благодарность нашему репетитору Станислав Львовичу! Благодаря его труду мы смогли поступить в медицинский университет.
   У нас в запасе был всего лишь год( трудный год). Но Станислав Львович помог нам осуществить мечту дочери. Мы очень сильно подтянули знаниями по химии.
   А еще он просто замечательный человек, который действительно переживает за своих учеников и хочет им помочь.И что было для меня очень важно , не просто
   проводил занятия , а пытался научить ученика логически мыслить, проверял вопросами насколько усвоен материал. Именно такого репетитора я хотела найти!!!
   Но тем, кто готовится к поступлению все -таки рекомендовала бы начать заниматься за два года до ЕГЭ.
   Я, как родитель, осталась очень довольна. С ним всегда можно было связаться по телефону и узнать об успехах дочери, а если что-то было не так, Станислав
   Львович звонил сам. А это очень важно для достижения результата. Спасибо Вам большое !',
   0),

  (9, 'Людмила', '2016-10-12 19:53:00',
   'Было очень приятно вместе работать. Отличный преподаватель и просто интересный человек. Способен действительно увлечь своим предметом, показать всю его
   красоту и полноту, рассказать и показать многое из практики, что так важно в химии.',
   1),

  (10, 'Юрий', '2016-08-16 15:23:15',
   'Благодаря Станиславу Львовичу я за короткий срок подготовилась к ГИА)а теперь готовимся к ЕГЭ)Очень благодарна ему за его терпение и труд) Огромное
   спасибо Станиславу Львовичу!!!',
   0);

/*------------------------------------------------------------------------------------------*/
