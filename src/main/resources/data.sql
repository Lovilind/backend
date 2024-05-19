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