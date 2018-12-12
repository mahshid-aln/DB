DELIMITER //
create trigger count_constraint before insert on orders
for each row
begin
	declare counter int default 0;
	select count(order_id) from customer natural join orders group by customer_id
	having (new.customer_id = customer_id) into counter; 
	if(counter <= 5) then
		update customer
		set order_count = order_count + 1;
	else
		signal sqlstate '45000';# set message_text = "can't add more orders";
	end if;
end; 
//
DELIMITER ;
