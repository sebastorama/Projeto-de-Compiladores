program Teste3;
var
	a : integer;

{ Mantem uma lista com os 10 primeiros inteiros primos. Caso
  um valor invalido seja pedido (nao seja entre 1 e 10) 
  o programa retorna -1 }
procedure primeiros_primos(i : integer; var res : integer);
begin
	case i of
		1: case res of
			3, 4: res := i; 
			5, 6: res := 2;
			7, 8: res := 4
		end;
		2: res := 2;
		3: res := 3;
		4: res := 5;
		5: res := 7;
		6: res := 11;
		7: res := 13;
		8: res := 17;
		9: res := 19;
		10: res := 23
	else
		res := -1
	end
end;


begin
	primeiros_primos(5, a);
	write(a)
end.
