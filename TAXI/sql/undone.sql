use projectdb
DELIMITER //
create procedure count_undone(in wanted_agency int)
begin
	select * from orders natural join agency where orders.status = 'undone' and agency.id = wanted_agency;
end;
//
DELIMITER ;
