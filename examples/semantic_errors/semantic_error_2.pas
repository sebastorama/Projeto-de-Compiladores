program Teste4;
var a: boolean;

{ checa se um numero passado como parametro eh primo. Resultados vao
  pela variavel "res" passada como referencia }
procedure checa_primo(i : integer; var res : boolean);
begin
	case i of
		1,2,3,5,7,11,13,17,23,29,31,37,41,43,47: res := true
	else
		res := false
	end
end

begin
	a := b; { 3 Identificador b nao declarado }
	checa_primo(17, a); 
	{ 4 - inconpatibilidade no numero de parametros }
	checa_primo(17, a, 2);

	checa_primo(28, a)
end.
