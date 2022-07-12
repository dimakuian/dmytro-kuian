# Data for role:
DELETE
FROM delivery.role;
INSERT INTO delivery.`role` (id, name)
VALUES (0, 'admin'),
       (1, 'client');

# Data for invoice status:
DELETE
FROM delivery.invoice_status;
INSERT INTO delivery.`invoice_status` (id, name)
VALUES (0, 'created'),
       (1, 'paid'),
       (2, 'declined');

# Data for language:
DELETE
FROM delivery.language;
INSERT INTO delivery.language (id, short_name, full_name)
VALUES (1, 'EN', 'English'),
       (2, 'UA', 'Ukraine');

# Data for shipping status:
DELETE
FROM delivery.shipping_status;
INSERT INTO delivery.`shipping_status` (id, name)
VALUES (0, 'created'),
       (1, 'confirmed'),
       (2, 'paid'),
       (3, 'preparing to ship'),
       (4, 'in the way'),
       (5, 'delivered'),
       (6, 'canceled');









