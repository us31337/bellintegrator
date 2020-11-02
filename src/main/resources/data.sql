INSERT INTO doc_type (code, name, version) VALUES
('03', 'Свидетельство о рождении', 1),
('07', 'Военный билет', 1),
('08', 'Временное удостоверение, выданное взамен военного билета', 1),
('10', 'Паспорт иностранного гражданина', 1),
('11', 'Свидетельство о рассмотрении ходатайства о признании лица беженцем на территории Российской Федерации по существу', 1),
('12', 'Вид на жительство в Российской Федерации', 1),
('13', 'Удостоверение беженца', 1),
('15', 'Разрешение на временное проживание в Российской Федерации', 1),
('18', 'Свидетельство о предоставлении временного убежища на территории Российской Федерации', 1),
('21', 'Паспорт гражданина Российской Федерации', 1),
('23', 'Свидетельство о рождении, выданное уполномоченным органом иностранного государства', 1),
('24', 'Удостоверение личности военнослужащего Российской Федерации', 1),
('91', 'Иные документы', 1);

INSERT  INTO country (name, code, version) VALUES
('Россия', 643, 1),
('Китай', 156, 1),
('Германия', 276, 1),
('Израиль', 376, 1),
('Казахстан', 398, 1),
('Киргизия', 417, 1),
('Латвия',  428, 1),
('Таджикистан', 762, 1),
('Украина', 804, 1),
('США', 840, 1),
('Узбекистан', 860, 1);

INSERT INTO organisation (name, full_name, inn, kpp, address, phone, version) VALUES
('ООО "АНАЛИТ-НЕВА"', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "АНАЛИТ-НЕВА"', '7842499778',
 '784201001', '191144, г Санкт-Петербург, ул Мытнинская, д 12/44 литер а', '+78124493110', 1),
('ООО "ИБИСЛАБ"', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "ИБИСЛАБ"', '7842163376',
 '781601001', '192236, ГОРОД САНКТ-ПЕТЕРБУРГ, УЛИЦА СОФИЙСКАЯ, ДОМ 8', '+78124493120', 1),
('ООО "НПО "АНАЛИТЛАБ"', 'ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ "НАУЧНО-ПРОИЗВОДСТВЕННОЕ ОБЪЕДИНЕНИЕ "АНАЛИТЛАБ"', '5260437088',
 '526201001', '603057, ОБЛАСТЬ НИЖЕГОРОДСКАЯ, ГОРОД НИЖНИЙ НОВГОРОД, УЛИЦА БЕКЕТОВА, ДОМ 13', '+78124503120', 1);

INSERT INTO document (doc_type, doc_date, doc_number, version) VALUES
('21', {ts '1999-12-31'}, '3141592654', 1),
('21', {ts '1998-12-31'}, '2718254466', 1),
('10', {ts '1200-12-31'}, '314sdfsea4', 1);

INSERT INTO office (org_id, name, address, phone, version) VALUES
(1, 'Отдел продаж', '4 этаж', '+78124493110', 1),
(2, 'Отдел закупок', '3 этаж', '+78121562110', 1),
(3, 'Отдел рекламаций', '2 этаж', '+78117529110', 1);

INSERT INTO user (office_id, first_name, second_name, position, phone, doc_id, citizenship_code, version) VALUES
(1, 'Екатерина', 'Иванова', 'Менеджер', '+78125478530', 1, 643, 1),
(2, 'Сергей', 'Менников', 'директор', '+73141592654', 2, 840, 1),
(3, 'Михаил', 'Павликов', 'охранник', '+78156372569', 3, 376, 1);