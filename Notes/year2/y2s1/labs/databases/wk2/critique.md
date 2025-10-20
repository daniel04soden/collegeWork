## Design 1 Shipments Database

### Rules:

1.  Rule 1: a uniform number of columns i.e. each row has a fixed number
    of columns.

2.  Rule 2: intersection of a row - column is one data value (i.e. no
    repeating groups)

3.  Rule 3: each row in a table must be unique. In other words, no two
    rows can be the same.

    Q1. Critique of this table:

    - This database structure isn\'t normalized at all given everything
      is present on the one table
      - There is no primary keys present
      - Some of the column names such as Pno and Sn aren\'t explicitly
        clear on what they\'re describing
      - Many repeating values
      - No order number
      - No first name either, similar names, need customer number
      - Weight for item or entire order??
