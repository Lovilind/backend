insert into member (
    birth_date, is_adult, created_at, updated_at,
    address_detail, address_simple, email, gender, mbti, nickname, password, phone, user_grade, user_status, zip_code
) values
      (
          '1990-01-01', true, current_timestamp(6), current_timestamp(6),
          '123 Main St, Apt 1', '123 Main St', 'john.doe@example.com', 'MALE', 'INTJ', 'johnny', 'password123', '123-456-7890', 'NORMAL', 'ACTIVE', '12345'
      ),
      (
          '1985-05-20', true, current_timestamp(6), current_timestamp(6),
          '456 Elm St, Apt 2', '456 Elm St', 'jane.doe@example.com', 'FEMALE', 'ENFP', 'janedoe', 'password456', '098-765-4321', 'VIP', 'SLEEP', '67890'
      ),
      (
          '2000-12-15', true, current_timestamp(6), current_timestamp(6),
          '789 Oak St, Apt 3', '789 Oak St', 'alice.smith@example.com', 'FEMALE', 'ISTJ', 'alice', 'password789', '567-890-1234', 'VVIP', 'ACTIVE', '11223'
      );

insert into chatroom (max_participant,min_participant,title)
values
    (20,10,'채팅방제목1'),
    (20,10,'채팅방제목2'),
    (20,10,'채팅방제목3');

insert into participant (participant_status,member_id,chatroom_id,last_send_message)
values
    ('PARTICIPATING',1,1,0),
    ('PARTICIPATING',1,2,5),
    ('PARTICIPATING',1,3,10),
    ('PARTICIPATING',2,1,0),
    ('PARTICIPATING',2,2,5),
    ('PARTICIPATING',2,3,20),
    ('PARTICIPATING',3,1,0),
    ('PARTICIPATING',3,2,10),
    ('PARTICIPATING',3,3,5);

insert into message(message,message_type,member_id,chatroom_id)
values
    ('메시지1','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지2','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지3','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지4','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지5','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지6','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지7','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지8','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지9','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지10','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지11','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지12','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지13','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지14','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지15','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지16','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지17','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지18','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지19','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지20','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지21','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지22','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지23','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지24','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지25','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지26','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지27','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지28','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지29','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지30','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지31','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지32','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지33','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지34','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지35','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지36','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지37','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지38','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지39','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지40','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지41','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지42','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지43','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지44','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지45','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지46','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지47','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지48','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지49','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3))),
    ('메시지50','CHAT',FLOOR(1 + (RAND() * 3)),FLOOR(1 + (RAND() * 3)));