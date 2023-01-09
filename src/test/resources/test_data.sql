insert into users (user_id, username, password, account_non_expired, account_non_locked, credentials_non_expired,
                   enabled)
values (1, 'test', '$2a$04$kPnAoWux201JVpj5RFSiruCaEgVB/IiEKQVYU1Y1oqHlBTxTnYHy2', true, true, true, true);
insert into passengers (passenger_id, first_name, last_name, user_id)
values (1, 'testName', 'testLastName', 1);

insert into users (user_id, username, password, account_non_expired, account_non_locked, credentials_non_expired,
                   enabled)
values (2, 'test2', '$2a$04$kPnAoWux201JVpj5RFSiruCaEgVB/IiEKQVYU1Y1oqHlBTxTnYHy2', true, true, true, true);
insert into passengers (passenger_id, first_name, last_name, user_id)
values (2, 'testName2', 'testLastName2', 2);

insert into cruises (cruise_id, description)
values (350, 'someTest');