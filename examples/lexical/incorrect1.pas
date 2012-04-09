program Teste2;
procedure fatorial(i : integer, var res : integer);
var
	aux :integer;
begin
	aux := 1;
	while i > 0 do
	begin
		aux := aux * i; 
		i := i-1;
	end;
	res := aux;
end;
%
begin
	res: integer;

	fatorial(5, res);
end.
