INSERT INTO employee(code, name, password, admin_flag, created_at, updated_at, delete_flag)
VALUES ('220001', '����i�P', 'rootroot', true, '2022-05-10 15:00:00', '2022-05-10 15:00:00', false);
INSERT INTO employee(code, name, password, admin_flag, created_at, updated_at, delete_flag)
VALUES ('220002', 'ei', 'root', false, '2022-05-10 15:00:00', '2022-05-10 15:00:00', false);

INSERT INTO reports(employee_id,report_date,title,content,created_at,updated_at) 
VALUES ('220001', '2022-05-20 15:00:00', '�O�����C', '�r�W�l�X�E���C�e�B���O', '2022-05-10 15:00:00', '2022-05-10 15:00:00');
INSERT INTO reports(employee_id,report_date,title,content,created_at,updated_at) 
VALUES ('220001', '2022-05-27 15:00:00', 'Lightning Talk', '���\', '2022-05-10 15:00:00', '2022-05-10 15:00:00');
