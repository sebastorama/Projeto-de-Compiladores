program Teste3;
var
	a, b, c : integer;
	z : boolean;

{ Mantem uma lista com os 10 primeiros inteiros primos. Caso
  um valor invalido seja pedido (nao seja entre 1 e 10) 
  o programa retorna -1 }
procedure primeiros_primos(i: integer; var res : integer);
begin
	case i of
		1: res := 1;
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
end

begin
	case a of
		1: b := 3;
		2: b := 4
	end;

	primeiros_primos(2, 2);
	a := b+1;
	z := true+false;
	read(a, b, z);
	write(2, b, 4)
end.
