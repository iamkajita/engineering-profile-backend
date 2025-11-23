### MySQL
'''
# テーブル作成
CREATE TABLE engineer_profile (
id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100) NOT NULL,
department VARCHAR(100) NOT NULL,
project VARCHAR(20) DEFAULT '待機中',
skill_level VARCHAR(20) CHECK (skill_level IN ('初級', '中級', '上級', 'エキスパート')),
years_of_experience INTEGER,
note TEXT
) DEFAULT CHARSET=utf8mb4;

# 挿入
INSERT INTO engineer_profile (name, department, project, skill_level, years_of_experience, note) VALUES
('山田太郎', '開発部', 'ECサイトリニューアル', '上級', 8, 'Javaが得意。Spring Bootでの開発経験豊富。'),
('佐藤花子', '営業部', '待機中', 'エキスパート', 12, 'プロジェクトマネージャー経験あり。英語対応可能。');
'''
