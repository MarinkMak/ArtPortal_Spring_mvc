USE artPortalDB;
         
INSERT INTO role_names VALUES 
    (1,'guest'),
    (2,'user'),
    (3,'admin'); 
    
INSERT INTO users VALUES 
     (1,'Artem','Artemov','Artem','artempsw','artem@ukr.net','artem.jpg',1,1,3),
     (2,'Олег','Олегов','Oleg','olegpsw','oleg@ukr.net','oleg.jpg',1,1,2),
     (3,'Maksim','Maksimov','Maksim','maksimpsw','maksim@ukr.net','maksim.jpg',1,1,2),
     (4,'Olesya','Maksimova','Olesya','olesyapsw','olesya@ukr.net','olesya.jpg',1,1,2),
     (5,'超级用户','使用者','Petr','petrpsw','petr@ukr.net','petr.jpg',1,1,2),
     (6,'Ivan','Ivanov','Ivan','ivanpsw','ivan@ukr.net','ivan.jpg',1,1,2),
     (7,'超级用户','使用者','Masha','mashapsw','masha@ukr.net','masha.jpg',1,1,2),
     (8,'Anton','Antonov','Anton','antonpsw','anton@ukr.net','anton.jpg',1,1,2),
	 (9,'Artur','Arturov','admin','admin','artur@ukr.net','artem.jpg',1,1,3);
     
INSERT INTO competitions VALUES
    (1,'Tourism as a Wild Life','Excellent set of marching kettles!','2015-02-01','2015-06-01','2015-06-15',null),
    (2,'Fantasy World','Two tickets to the cinema show!','2015-03-01','2015-07-01','2015-07-15',null),
    (3,'Сharismatic Horses','One week of training in the horse club!','2015-04-01','2015-08-01','2015-08-15',null); 
     
INSERT INTO art_types VALUES
    (1,'photography'),
    (2,'digital art'),
    (3,'classic art'); 

INSERT INTO art_works VALUES
    (1,'Lady&Dragon','1oleg.jpg','2015-01-18',1,2,2,2),
    (2,'White horse','2maksim.jpg','2015-01-18',1,2,3,3),
    (3,'Lady&Dragon2','3oleg.jpg','2015-01-19',1,2,2,null),
    (4,'Dragon','4olesya.jpg','2015-01-20',1,2,4,2),
    (5,'Black horse','5maksim.jpg','2015-01-20',1,2,3,3),
    (6,'Sky River','6petr.jpg','2015-01-21',1,1,5,1),
    (7,'Fireplace','7masha.jpg','2015-01-21',1,1,7,1),
    (8,'Cancers','8masha.jpg','2015-01-21',1,1,7,1),
    (9,'Tent','9anton.jpg','2015-01-22',1,1,8,1),
    (10,'Cow','10anton.jpg','2015-01-22',1,1,8,1),
    (11,'Sunset','11petr.jpg','2015-01-22',1,1,5,1),
    (12,'River','12petr.jpg','2015-01-22',1,1,5,1),
    (13,'Sky','13ivan.jpg','2015-01-23',1,1,6,1),
    (14,'Nature','14ivan.jpg','2015-01-23',1,1,6,1); 

INSERT INTO comments VALUES
    (1,'Cool :)','2015-01-23',1,4),
    (2,'I like it!','2015-01-23',2,4),
    (3,':)))))','2015-01-24',3,4),
    (4,'Спасибо!','2015-02-24',1,2),
    (5,'...it is some strange...','2015-02-25',1,3),
    (6,'interesting work','2015-02-26',3,3),
    (7,'Great! +1','2015-02-27',7,2),
    (8,'not, really... -1','2015-03-27',7,4),
    (9,'= 0!','2015-03-28',7,1);

INSERT INTO voices VALUES
    (1,10,'2015-04-23',1,4),
    (2,10,'2015-04-23',2,4),
    (3,10,'2015-04-24',5,4),
    (4,10,'2015-04-24',1,3),
    (5,10,'2015-04-25',2,3),
    (6,10,'2015-04-26',6,3),
    (7,10,'2015-04-27',4,2),
    (8,10,'2015-04-27',5,2),
    (9,10,'2015-04-28',6,2),
    (10,10,'2015-04-28',4,7),
    (11,10,'2015-04-29',5,7),
    (12,10,'2015-04-30',6,6),
    (13,10,'2015-05-01',7,6),
    (14,10,'2015-05-03',8,5);

INSERT INTO presents VALUES 
    (1,'Excellent set of marching kettles!','1.jpg','Excellent set of marching kettles!',1),
    (2,'Two tickets to the cinema show!','2.jpg','Two tickets to the cinema show!',2),
    (3,'One week of training in the horse club!','3.jpg','One week of training in the horse club!',3);

INSERT INTO roles VALUES 
    (1,'maksim','user'),
    (2,'oleg','user'),
    (3,'artem','admin'); 