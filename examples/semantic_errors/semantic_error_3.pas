program Teste3;
var
	a : integer;

{ Mantem uma lista com os 10 primeiros inteiros primos. Caso
  um valor invalido seja pedido (nao seja entre 1 e 10) 
  o programa retorna -1 }
procedure primeiros_primos(i : integer; var res : integer);
begin
	case i of
		1: res := 1;
		1: res := 1; { 5 - constante repetida } 
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

{ checa se um numero passado como parametro eh primo. Resultados vao
  pela variavel "res" passada como referencia }
procedure checa_primo(i : integer; var res : boolean);
begin
	case i of
		{ 6 - constante repetida }
		1,1,2,3,5,7,11,13,17,23,29,31,37,41,43,47: res := true
	else
		res := false
	end
end;

begin
	primeiros_primos(5, a);
	write(a)
end.
