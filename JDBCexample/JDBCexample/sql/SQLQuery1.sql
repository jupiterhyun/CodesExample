Create  PROCEDURE GetPersonNames
(
@ID INT,                       --Input parameter ,  Studentid of the student
@personName VARCHAR(200)  OUT        -- Out parameter declared with the help of OUT keyword
)
AS
BEGIN
SELECT @personName = FirstName+' '+LastName FROM Persons WHERE id=@ID
END