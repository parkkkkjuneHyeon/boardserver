-- 블로그 데이터베이스 스키마 생성 SQL

-- 1. 사용자 테이블
CREATE TABLE user (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      userId VARCHAR(50) NOT NULL UNIQUE,
                      password VARCHAR(50) NOT NULL,
                      isAdmin TINYINT DEFAULT 0,
                      createTime DATETIME DEFAULT CURRENT_TIMESTAMP,
                      isWithDraw TINYINT DEFAULT 0
);

-- 2. 카테고리 테이블
CREATE TABLE category (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL
);

-- 3. 게시글 테이블
CREATE TABLE post (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      name VARCHAR(50) NOT NULL,
                      isAdmin TINYINT DEFAULT 0,
                      contents VARCHAR(500) NOT NULL,
                      createTime DATETIME DEFAULT CURRENT_TIMESTAMP,
                      views INT DEFAULT 0,
                      categoryId INT,
                      userId INT,
                      FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE SET NULL,
                      FOREIGN KEY (userId) REFERENCES user(id) ON DELETE CASCADE
);

-- 4. 태그 테이블
CREATE TABLE tag (
                     id INT PRIMARY KEY AUTO_INCREMENT,
                     name VARCHAR(50) NOT NULL
);

-- 5. 게시글-태그 연결 테이블 (다대다 관계)
CREATE TABLE postTag (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         postId INT,
                         tagId INT,
                         FOREIGN KEY (postId) REFERENCES post(id) ON DELETE CASCADE,
                         FOREIGN KEY (tagId) REFERENCES tag(id) ON DELETE CASCADE,
                         UNIQUE KEY unique_post_tag (postId, tagId)
);

-- 6. 댓글 테이블
CREATE TABLE comment (
                         id INT PRIMARY KEY AUTO_INCREMENT,
                         comment VARCHAR(500) NOT NULL,
                         postId INT,
                         subCommentId INT,
                         FOREIGN KEY (postId) REFERENCES post(id) ON DELETE CASCADE,
                         FOREIGN KEY (subCommentId) REFERENCES comment(id) ON DELETE CASCADE
);

-- 7. 파일 테이블
CREATE TABLE file (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      path VARCHAR(50) NOT NULL,
                      name VARCHAR(50) NOT NULL,
                      extension VARCHAR(50) NOT NULL,
                      postId INT,
                      FOREIGN KEY (postId) REFERENCES post(id) ON DELETE CASCADE
);

-- 인덱스 생성 (성능 최적화)
CREATE INDEX idx_post_category ON post(categoryId);
CREATE INDEX idx_post_user ON post(userId);
CREATE INDEX idx_post_createtime ON post(createTime);
CREATE INDEX idx_comment_post ON comment(postId);
CREATE INDEX idx_file_post ON file(postId);
CREATE INDEX idx_posttag_post ON postTag(postId);
CREATE INDEX idx_posttag_tag ON postTag(tagId);

-- 샘플 데이터 삽입
INSERT INTO user (userId, password, isAdmin) VALUES
                                                 ('admin', 'admin123', 1),
                                                 ('user1', 'password1', 0),
                                                 ('user2', 'password2', 0);

INSERT INTO category (name) VALUES
                                ('기술'),
                                ('일상'),
                                ('여행'),
                                ('음식'),
                                ('취미');

INSERT INTO tag (name) VALUES
                           ('JavaScript'),
                           ('Python'),
                           ('Web개발'),
                           ('데이터베이스'),
                           ('알고리즘');

INSERT INTO post (name, contents, categoryId, userId, views) VALUES
                                                                 ('첫 번째 게시글', '안녕하세요! 첫 번째 게시글입니다.', 1, 1, 10),
                                                                 ('JavaScript 기초', 'JavaScript의 기본 문법에 대해 알아보겠습니다.', 1, 2, 25),
                                                                 ('맛집 탐방기', '오늘 다녀온 맛집을 소개합니다.', 4, 3, 15);

INSERT INTO postTag (postId, tagId) VALUES
                                        (2, 1),
                                        (2, 3),
                                        (1, 4);

INSERT INTO comment (comment, postId) VALUES
                                          ('좋은 글이네요!', 1),
                                          ('도움이 되었습니다.', 2),
                                          ('더 자세한 내용이 궁금해요.', 2);