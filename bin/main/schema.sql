CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,
  code VARCHAR(20) NOT NULL UNIQUE,
  name VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  admin_flag BOOLEAN NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  delete_flag BOOLEAN NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE reports (
  id INT NOT NULL AUTO_INCREMENT,
  employee_id VARCHAR(20) NOT NULL,
  report_date DATETIME NOT NULL,
  title VARCHAR(50) NOT NULL,
  content VARCHAR(100) NOT NULL,
  created_at DATETIME NOT NULL,
  updated_at DATETIME NOT NULL,
  PRIMARY KEY(id)
);
