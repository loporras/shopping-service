
INSERT INTO tbl_invoices ( number_invoice, description, customer_id,state) VALUES
('001','Invoice store',1,'CREATED'),
('002','Inoives Store2',2, 'CREATED');

INSERT INTO tbl_invoice_items (quantity,price,product_id,invoice_id) VALUES
( 2.0, 50.0,1,1),(3.0, 20.0,2,1),(60.0,100.0,3,1);

