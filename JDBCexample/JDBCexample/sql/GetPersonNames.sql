/*
Create  PROCEDURE GetPersonNames
(
@personID INT,                       --Input parameter ,  Studentid of the student
@personName VARCHAR(200)  OUT        -- Out parameter declared with the help of OUT keyword
)
AS
BEGIN
SELECT @personName = FirstName+' '+LastName FROM Persons WHERE PersonID=@personID
END
*/
Alter PROCEDURE GetPersonNames
(
@personID INT,                       --Input parameter ,  Studentid of the student
@personName VARCHAR(200)  OUT,        -- Out parameter declared with the help of OUT keyword
@Address VARCHAR(200) OUT
)
AS
BEGIN
SELECT @personName = FirstName+' '+LastName, @Address = Address FROM Persons WHERE PersonID=@personID
END
