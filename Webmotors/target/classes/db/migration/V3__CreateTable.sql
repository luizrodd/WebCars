-- Step 1: Drop the foreign key constraint from the 'Model' table
ALTER TABLE dbo.Model DROP CONSTRAINT FK__Model__brand_id__5070F446

-- Step 2: Drop the foreign key constraint from the 'Car' table
ALTER TABLE dbo.Car DROP CONSTRAINT FK__Car__brand_id__5629CD9C

-- Step 3: Drop the primary key constraint from the 'Brand' table
ALTER TABLE dbo.Brand DROP CONSTRAINT PK__Brand__3213E83FD4B59B03

-- Step 4: Add a new column with IDENTITY to the 'Brand' table
ALTER TABLE dbo.Brand ADD id_new INT IDENTITY(1,1)

-- Step 5: Drop the old 'id' column from the 'Brand' table
ALTER TABLE dbo.Brand DROP COLUMN id

-- Step 6: Rename the new column to old column's name
EXEC sp_rename 'dbo.Brand.id_new', 'id', 'COLUMN'

-- Step 7: Add the primary key constraint back to the new 'id' column in the 'Brand' table
ALTER TABLE dbo.Brand ADD CONSTRAINT PK__Brand__3213E83FD4B59B03 PRIMARY KEY (id)

-- Step 8: Add the foreign key constraint back to the 'Car' table
ALTER TABLE dbo.Car ADD CONSTRAINT FK__Car__brand_id__5629CD9C FOREIGN KEY (brand_id) REFERENCES dbo.Brand(id)

-- Step 9: Add the foreign key constraint back to the 'Model' table
ALTER TABLE dbo.Model ADD CONSTRAINT FK__Model__brand_id__5070F446 FOREIGN KEY (brand_id) REFERENCES dbo.Brand(id)