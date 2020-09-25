INSERT INTO school (id, avatar, description, name) VALUES (1, 'pku.png', '一所北京的市级大学', '北京大学');
INSERT INTO school (id, avatar, description, name) VALUES (2, 'thu.png', '不知名的理工院校', '清华大学');
INSERT INTO school (id, avatar, description, name) VALUES (3, 'qlut.png', '省科学院合作的高级综合院校', '齐鲁工业大学');
INSERT INTO school (id, avatar, description, name) VALUES (4, 'sdu.png', '山东带学', '山东大学');
INSERT INTO school (id, avatar, description, name) VALUES (5, 'qdu.png', '山东济南，中国青岛', '青岛大学');

INSERT INTO clazz (id, description, name, school_id) VALUES (1, '我们计科真的太厉害了', '计科18-1', 1);
INSERT INTO clazz (id, description, name, school_id) VALUES (2, '我们学校的软工行，其他学校的软工专业不行', '软工17-1', 1);
INSERT INTO clazz (id, description, name, school_id) VALUES (3, '我真的是太爱物联了', '物联19-1', 2);
INSERT INTO clazz (id, description, name, school_id) VALUES (4, '今天依旧奢侈一把整点阿里云服务器', '云计20-1', 2);
INSERT INTO clazz (id, description, name, school_id) VALUES (5, '我们学校的软工行，其他学校的软工专业不行', '软工19-1', 3);
INSERT INTO clazz (id, description, name, school_id) VALUES (6, '我们计科真的太厉害了', '计科20-2', 3);
INSERT INTO clazz (id, description, name, school_id) VALUES (7, '我真的是太爱物联了', '物联17-1', 4);
INSERT INTO clazz (id, description, name, school_id) VALUES (8, '今天依旧奢侈一把整点阿里云服务器', '云计19-3', 4);
INSERT INTO clazz (id, description, name, school_id) VALUES (9, '我们学校的软工行，其他学校的软工专业不行', '软工16-2', 5);
INSERT INTO clazz (id, description, name, school_id) VALUES (10, '我们计科真的太厉害了', '计科18-2', 5);

INSERT INTO contest (id, description, enabled, end, error, finish, latch, penalty, start, title, type) VALUES (1, '## 比赛1描述  Markdown格式的比赛描述测试', 1, '2020-09-07 08:20:32', null, '2020-09-07 08:20:32', '2020-09-07 08:20:32', 20, '2020-07-20 10:30:00', '2020年“美团杯”程序设计挑战赛', 1);
INSERT INTO contest (id, description, enabled, end, error, finish, latch, penalty, start, title, type) VALUES (2, '## 比赛2描述  Markdown格式的比赛描述测试', 1, '2020-08-11 16:28:16', null, '2020-08-11 16:28:16', '2020-08-11 16:28:16', 10, '2020-07-20 10:30:00', '集训队互测 2020 Round #2', 2);
INSERT INTO contest (id, description, enabled, end, error, finish, latch, penalty, start, title, type) VALUES (3, '## 比赛3描述  Markdown格式的比赛描述测试', 1, '2020-06-21 18:43:16', null, '2020-06-21 18:43:16', '2020-06-21 18:43:16', 15, '2020-07-20 10:30:00', '集训队互测 2020 Round #1', 3);
INSERT INTO contest (id, description, enabled, end, error, finish, latch, penalty, start, title, type) VALUES (4, '## 比赛4描述  Markdown格式的比赛描述测试', 1, '2020-12-11 12:28:16', null, '2020-12-11 12:28:16', '2020-12-11 12:28:16', 0, '2020-07-20 10:30:00', 'Round #0', 4);

INSERT INTO team (id, description, name, register, school_id) VALUES (1, '这个队伍很懒没有描述', '蒙巴萨黑豹', '2020-08-07 10:21:22', 1);
INSERT INTO team (id, description, name, register, school_id) VALUES (2, '这个队伍很懒没有描述', '埃德蒙顿利刃', '2020-08-07 10:21:22', 1);
INSERT INTO team (id, description, name, register, school_id) VALUES (3, '这个队伍很懒没有描述', '美因茨俱乐部', '2020-08-07 10:21:22', 2);
INSERT INTO team (id, description, name, register, school_id) VALUES (4, '这个队伍很懒没有描述', '巴库国王队', '2020-08-07 10:21:22', 2);
INSERT INTO team (id, description, name, register, school_id) VALUES (5, '这个队伍很懒没有描述', '朗斯俱乐部', '2020-08-07 10:21:22', 3);
INSERT INTO team (id, description, name, register, school_id) VALUES (6, '这个队伍很懒没有描述', '切沃之星', '2020-08-07 10:21:22', 3);
INSERT INTO team (id, description, name, register, school_id) VALUES (7, '这个队伍很懒没有描述', '梵蒂冈王道', '2020-08-07 10:21:22', 4);
INSERT INTO team (id, description, name, register, school_id) VALUES (8, '这个队伍很懒没有描述', '曼谷国王队', '2020-08-07 10:21:22', 5);
INSERT INTO team (id, description, name, register, school_id) VALUES (9, '这个队伍很懒没有描述', '阿姆斯特丹之光', '2020-08-07 10:21:22', 5);

INSERT INTO news (id, content, title) VALUES (1, '## 齐工大牛逼1', '新闻1');
INSERT INTO news (id, content, title) VALUES (2, '### 齐工大牛逼2', '新闻2');
INSERT INTO news (id, content, title) VALUES (3, '#### 齐工大牛逼3', '新闻3');
INSERT INTO news (id, content, title) VALUES (4, '#### 齐工大牛逼4', '新闻4');
INSERT INTO news (id, content, title) VALUES (5, '### 齐工大牛逼5', '新闻5');

INSERT INTO tag (id, category, content, color, icon) VALUES(1, 'problem', '二叉树', 'success', 'el-icon-medal');
INSERT INTO tag (id, category, content, color, icon) VALUES(2, 'problem', '分治', 'info', 'el-icon-medal');
INSERT INTO tag (id, category, content, color, icon) VALUES(3, 'problem', '动态规划', 'warning', 'el-icon-medal');
INSERT INTO tag (id, category, content, color, icon) VALUES(4, 'problem', '字符串', 'danger', 'el-icon-medal');

INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (1, 0, 5, 5, '# 萌新一加一题目  ## 题目描述  输出1+1的结果', 1.00, '简单一加一题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (2, 1, 10, 5, '# 新手题目  ## 题目描述  输出2+2的结果', 1.00, '新手题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (3, 2, 5, 10, '# 简单题目  ## 题目描述  输出3+3的结果', 1.00, '简单题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (4, 3, 20, 5, '# 普通题目  ## 题目描述  输出4+4的结果', 1.00, '普通题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (5, 4, 5, 5, '# 较难一加一题目  ## 题目描述  输出5+5的结果', 1.00, '较难一加一题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (6, 0, 5, 5, '# 萌新一加一题目  ## 题目描述  输出1+1的结果', 1.00, '简单一加一题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (7, 1, 10, 5, '# 新手题目  ## 题目描述  输出2+2的结果', 1.00, '新手题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (8, 2, 5, 10, '# 简单题目  ## 题目描述  输出3+3的结果', 1.00, '简单题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (9, 3, 20, 5, '# 普通题目  ## 题目描述  输出4+4的结果', 1.00, '普通题目', 1, null, null);
INSERT INTO problem (id, difficulty, memory_limit, output_limit, text, time_limit, title, type, compare_script_id, run_script_id) VALUES (10, 4, 5, 5, '# 较难一加一题目  ## 题目描述  输出5+5的结果', 1.00, '较难一加一题目', 1, null, null);

INSERT INTO language (id, code, enabled, memory_factor, name, time_factor) VALUES (1, 100, 1, 1, 'C', 1);
INSERT INTO language (id, code, enabled, memory_factor, name, time_factor) VALUES (2, 101, 1, 1, 'C++', 1);
INSERT INTO language (id, code, enabled, memory_factor, name, time_factor) VALUES (3, 102, 1, 1, 'C#', 1);
INSERT INTO language (id, code, enabled, memory_factor, name, time_factor) VALUES (4, 103, 1, 5, 'Java', 2);
INSERT INTO language (id, code, enabled, memory_factor, name, time_factor) VALUES (5, 104, 1, 2, 'Python', 5);
INSERT INTO language (id, code, enabled, memory_factor, name, time_factor) VALUES (6, 105, 1, 2, 'JavaScript', 3);

INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (1, '001-barista.png', 'liam@outlook.com', 1, null, 0, 'Liam', null, '13808945410', '2020-08-07 09:50:42', null, 0, '201701010001 ', 'liam', 2, 1, 1);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (2, '002-bussiness man.png', 'leon@outlook.com', 1, null, 0, 'Leon', null, '13808945411', '2020-08-07 09:50:42', null, 0, '201701010002 ', 'leon', 1, 1, 1);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (3, '003-business man.png', 'khalil@outlook.com', 1, null, 0, 'Khalil', null, '13808945412', '2020-08-07 09:50:42', null, 0, '201701010003 ', 'khalil', 1, 1, 1);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (4, '004-butcher.png', 'johnson@outlook.com', 1, null, 0, 'Johnson', null, '13808945413', '2020-08-07 09:50:42', null, 0, '201701010004 ', 'johnson', 1, 1, 2);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (5, '005-chinese.png', 'martin@outlook.com', 1, null, 0, 'Martin', null, '13808945414', '2020-08-07 09:50:42', null, 0, '201701010005 ', 'martin', 2, 1, 2);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (6, '006-coach.png', 'berry@outlook.com', 1, null, 0, 'Berry', null, '13808945415', '2020-08-07 09:50:42', null, 0, '201701010006 ', 'berry', 3, 2, 3);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (7, '007-dad.png', 'kim@outlook.com', 1, null, 0, 'Kim', null, '13808945416', '2020-08-07 09:50:42', null, 0, '201701010007 ', 'kim', 3, 2, 3);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (8, '008-dancer.png', 'rose@outlook.com', 1, null, 0, 'Rose', null, '13808945417', '2020-08-07 09:50:42', null, 0, '201701010008 ', 'rose', 4, 2, 4);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (9, '009-designer.png', 'powell@outlook.com', 1, null, 0, 'Powell', null, '13808945418', '2020-08-07 09:50:42', null, 0, '201701010009 ', 'powell', 4, 2, 4);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (10, '010-designer.png', 'miller@outlook.com', 1, null, 0, 'Miller', null, '13808945419', '2020-08-07 09:50:42', null, 0, '201701010010 ', 'miller', 5, 3, 5);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (11, '011-man.png', 'harrison@outlook.com', 1, null, 0, 'Harrison', null, '13808945420', '2020-08-07 09:50:42', null, 0, '201701010011 ', 'harrison', 5, 3, 5);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (12, '012-doctor.png', 'murray@outlook.com', 1, null, 0, 'Murray', null, '13808945421', '2020-08-07 09:50:42', null, 0, '201701010012 ', 'murray', 6, 3, 6);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (13, '013-editor.png', 'hunt@outlook.com', 1, null, 0, 'Hunt', null, '13808945422', '2020-08-07 09:50:42', null, 0, '201701010013 ', 'hunt', 6, 3, 6);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (14, '014-graphic designer.png', 'adams@outlook.com', 1, null, 0, 'Adams', null, '13808945423', '2020-08-07 09:50:42', null, 0, '201701010014 ', 'adams', 7, 4, 7);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (15, '015-homeless.png', 'ford@outlook.com', 1, null, 0, 'Ford', null, '13808945424', '2020-08-07 09:50:42', null, 0, '201701010015 ', 'ford', 8, 4, 7);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (16, '016-woman.png', 'ryan@outlook.com', 1, null, 0, 'Ryan', null, '13808945425', '2020-08-07 09:50:42', null, 0, '201701010016 ', 'ryan', 7, 4, 7);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (17, '017-muslim.png', 'allen@outlook.com', 1, null, 0, 'Allen', null, '13808945426', '2020-08-07 09:50:42', null, 0, '201701010017 ', 'allen', 9, 5, 8);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (18, '018-pastor.png', 'knight@outlook.com', 1, null, 0, 'Knight', null, '13808945427', '2020-08-07 09:50:42', null, 0, '201701010018 ', 'knight', 9, 5, 8);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (19, '019-teacher.png', 'romero@outlook.com', 1, null, 0, 'Romero', null, '13808945428', '2020-08-07 09:50:42', null, 0, '201701010019 ', 'romero', 10, 5, 9);
INSERT INTO user (id, avatar, email, enabled, ip, locked, nickname, password, phone, register, role, silenced, student_id, username, clazz_id, school_id, team_id) VALUES (20, '020-teenager.png', 'stevens@outlook.com', 1, null, 0, 'Stevens', null, '13808945429', '2020-08-07 09:50:42', null, 0, '201701010020 ', 'stevens', 10, 5, 9);

INSERT INTO submission (id, submit, language, code, contest_id, problem_id, user_id) VALUES (1, '2020-08-07 09:50:42', 1, '#include <iostream>', 1, 1, 1);
INSERT INTO submission (id, submit, language, code, contest_id, problem_id, user_id) VALUES (2, '2020-08-07 09:50:42', 1, '#include <iostream>', 1, 1, 1);
INSERT INTO submission (id, submit, language, code, contest_id, problem_id, user_id) VALUES (3, '2020-08-07 09:50:42', 1, '#include <iostream>', 1, 1, 1);
INSERT INTO submission (id, submit, language, code, contest_id, problem_id, user_id) VALUES (4, '2020-08-07 09:50:42', 1, '#include <iostream>', 1, 1, 1);

INSERT INTO school_contest (contests_id, schools_id) VALUES (2, 1);
INSERT INTO school_contest (contests_id, schools_id) VALUES (2, 2);
INSERT INTO school_contest (contests_id, schools_id) VALUES (2, 3);

INSERT INTO team_contest (contests_id, teams_id) VALUES (3, 1);
INSERT INTO team_contest (contests_id, teams_id) VALUES (3, 2);
INSERT INTO team_contest (contests_id, teams_id) VALUES (3, 3);
INSERT INTO team_contest (contests_id, teams_id) VALUES (3, 4);
INSERT INTO team_contest (contests_id, teams_id) VALUES (3, 5);

INSERT INTO user_contest (joined_contests_id, users_id) VALUES (4, 1);
INSERT INTO user_contest (joined_contests_id, users_id) VALUES (4, 2);
INSERT INTO user_contest (joined_contests_id, users_id) VALUES (4, 3);
INSERT INTO user_contest (joined_contests_id, users_id) VALUES (4, 4);

INSERT INTO problem_tag (tags_id, problems_id) VALUES(1, 1);
INSERT INTO problem_tag (tags_id, problems_id) VALUES(1, 2);
INSERT INTO problem_tag (tags_id, problems_id) VALUES(2, 3);
INSERT INTO problem_tag (tags_id, problems_id) VALUES(3, 3);
INSERT INTO problem_tag (tags_id, problems_id) VALUES(4, 3);
INSERT INTO problem_tag (tags_id, problems_id) VALUES(3, 4);
INSERT INTO problem_tag (tags_id, problems_id) VALUES(4, 5);

INSERT INTO problem_contest (contests_id, problems_id) VALUES (1, 1);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (1, 2);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (1, 3);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (1, 4);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (2, 1);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (2, 2);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (2, 5);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (2, 6);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 2);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 3);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 4);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 5);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 6);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 7);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 8);
INSERT INTO problem_contest (contests_id, problems_id) VALUES (3, 9);