program Teste;
var a: boolean;
    a: integer; { Erro semantico 1 - identificador ja declarado }
    b: bolean;  { Erro semantico 2 - tipo nao definido }

{ checa se um numero passado como parametro eh primo. Resultados vao
  pela variavel "res" passada como referencia }
procedure checa_primo(i : integer; var res : boolean);
begin
	case i of
		1,2,3,5,7,11,13,17,23,29,31,37,41,43,47: res := true
	else
		res := false
	end
end;

begin
	checa_primo(17, a);
	{ a deve ser true }
	checa_primo(28, a)
	{ a deve ser false }
end.
