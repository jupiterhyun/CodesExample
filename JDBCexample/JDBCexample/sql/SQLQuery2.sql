Declare @PersoonID as int
Declare @Personname as nvarchar(200) 
Declare @Address as nvarchar(200)  
Exec GetPersonNames 100, @Personname output, @Address output
select @Personname,@Address