--item types: letter, parcel, package, postcard
insert into post_item_type values(1, 'letter');
insert into post_item_type values(2, 'parcel');
insert into post_item_type values(3, 'package');
insert into post_item_type values(4, 'postcard');

-- event types: registered, arrived, departed , delivered
insert into postal_item_event_type values(1, 'registered');
insert into postal_item_event_type values(2, 'arrived');
insert into postal_item_event_type values(3, 'departed');
insert into postal_item_event_type values(4, 'delivered');

-- post offices:
insert into post_office values(1,100001 ,'central post office','Moscow');
insert into post_office values(2,200001 ,'local post office','Sochi');


