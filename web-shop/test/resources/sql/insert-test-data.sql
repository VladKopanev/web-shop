INSERT INTO role VALUES
(1,	'ADMIN'),
(4,	'BLOCKED'),
(2,	'EMPLOYEE'),
(3,	'CUSTOMER');

INSERT INTO status VALUES
  (4,	'closed'),
  (1,	'created'),
  (3,	'sended'),
  (2,	'submited');

INSERT INTO user VALUES (1, 'kopaniev_dyploma@ukr.net',	'dyploma777',	'Vladyslav Kopaniev',
                           '1995-07-05',	777,	'Kharkov',	1);

INSERT INTO item VALUES (1,	'978-5-17-065495-6',	'It',	'Stephen King',	'АСТ',	'2015-01-01',
                           1248,	1,	44);
INSERT INTO mail_template (email_type, email_subject, email_text) VALUES ('customer', 'Your order', '<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
	<h3>Ваш заказ находится в обработке, вскоре наш менеджер с вами свяжется.</h3>
	<h3>Сумма вашего заказа составит:</h3>
	<h3 th:text="${emailDataObject.orderedSum}"></h3>
</body>
</html>');
INSERT INTO mail_template (email_type, email_subject, email_text) VALUES ('manager', 'New order', '<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
</head>
<body>
	<div>
	User <h2 th:text="${emailDataObject.customerName}"></h2> created new order;
	</div>

	<h3>Сумма заказа составит:</h3>
	<h3 th:text="${emailDataObject.orderedSum}"></h3>
</body>
</html>');

