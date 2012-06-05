public class RS {

	public SymbolTable symbol_table;
	public int current_level;
	public int signal;
	public int n_par;
	public boolean class_set;
	public int pos;
	public int c_id;
	public int p_id;

	public RS() {

		symbol_table = new SymbolTable();

		current_level = 0;
		n_par = 0;
	}

	/* 1) Passar um símbolo criado anteriormente
	   2) Passar um valor (0' || 0'') x sinal
	   3) Passar um tipo_c (0'|| 0'')
	   4) Passar ref	
	*/
	public static void process(int n) {	

		switch (n) {

		/* Os comentários em cima de cada case se referem os valores originais
			que estão no folha "Rotinas Semânticas". */

			// 0
			case 0: {

					if(symbol_table.declared(symbol, current_level))
						System.out.println("ID already declared");

					else
						symbol_table.insert(symbol, ref);
				}
					break;

			// 0'
			case 1: {						

					symbol_table.setAttributes(current_level, "constant", value, type_c);
									
				}
					break;

			// 0''
			case 2: {

					if(!symbol_table.search(symbol, ref) ||
							!symbol.category.equals("constant"))

						System.out.println("Constant not defined");

					else {

						//Verificar type_c e value (onde ficam)
						type_c = symbol.type_c;
						value = symbol.value;
					}
				}
					break;

			// 0'''
			case 3:{
						// Verificar type_c e value (onde ficam)
						type_c = "integer";
						value = "don't know =/";
				}
					break;

			// 1
			case 4: {

					if(symbol_table.declared(symbol, current_level))
						System.out.println("ID already declared");

					else
						symbol_table.insert(symbol, ref);
				}
					break;

			// 2
			case 5: {

								// Verificar "elementary_type", pois está vindo do RS(9)
					symbol_table.setAttributes(current_level, "type", n_bytes, elementary_type)		
				}
					break;

			// 3
			case 6: {

					if(symbol_table.declared(symbol, current_level))
						System.out.println("ID already declared");

					else
						symbol_table.insert(symbol, ref);
				}
					break;

			// 4
			case 7: {

									// Verificar "type_v", pois está vindo de RS(9) 
					symbol_table.setAttributes(current_level, "variable", type_v);
				}
					break;

			// 5
			case 8: {

					if(symbol_table.declared(symbol, current_level))
						System.out.println("ID already declared");

					else {

						symbol_table.insert(symbol, ref);
						symbol_table.setAttributes(current_level, "procedure");

						n_par = 0;
						current_level++;
				}
					break;

			// 6
			case 9: {

					if(symbol_table.declared(symbol, current_level))
						System.out.println("ID already declared");

					else {

						symbol_table.insert(symbol, current_level);
						symbol_tabel.setAttributes(current_level, "function");

						n_par = 0;
						current_level++;
					}
				}
					break;

			// 7
			case 10: {

					if(!symbol_table.search(symbol, ref) || 
							!symbol.category.equals("type"))
						
						System.out.println("Type not defined");

					else
						// type_f ???????
						symbol.type_f = type_f;
				}
					break;
			// 8
			case 11: {

					// Ler a folha "Rotinas Semânticas" para saber o que eliminar (não é tudo)
					symbol_table.destroy(current_level);
					current_level--;
				}
					break;

			// 13
			case 12: {

					pos = ref;
				}
					break;

			// 14
			case 13: {

					// Passagem por VALOR
					class_set = true;
				}
					break;

			// 15
			case 14: {

					// Passagem por REFERENCIA
					class_set = false;
				}
					break;

			// 18
			case 15: {

					if(symbol_table.declared(symbol, current_level))
						System.out.println("ID already declared");

					else {

						symbol_table.insert(symbol, ref);
						symbol_table.setAttributes(current_level, "parameter", class_set);

						npar++;
					}
				}
					break;

			// 20
			case 16: {

					// Não entendi =//
					npar1 = pos;
						or
					npar2 = pos;

					npar = 0;
				}
					break;

			// 21
			case 17: {

					if(!symbol_table.search(symbol, ref))
						System.out.println("ID not declared");

					else {

						c_id = symbol.category;
						p_id = symbol.ref;		
					}
				}
					break;

			// 22
			case 18: {

					if(!c_id.equals("function"))
						System.out.println("Function not defined");
					// COM ABRE PARENTESES????
				}
					break;

			// 22'
			case 19: {

					if(!c_id.equals("function"))
						System.out.println("Function not defined");

					else if(!c_id.equals("parameter"))
						System.out.println("Parameter not defined");

					else if(!c_id.equals("variable"))
						System.out.println("Variable not defined");

					else if(!c_id.equals("constant"))
						System.out.println("Constant not defined");

					// SEM ABRE PARENTESES??????
				}

			// 23
			case 20: {

					npar++;
				}
					break;

			// 24
			case 21: {

			// Verificar melhor esse caso!!!!!!

						//  ???????????????
					if(symbol.category apontado por p_id.equals("procedure")){

						if(npar1 != npar)
							System.out.println("Number of parameters incompatible");

					}

					else {

						if(npar2 != npar)
							Sytem.out.println("Number of parameters incompatible");
					}

					npar = 0;
				}
					break;

			// 25
			case 22: {

					if(!c_id.equals("procedure"))
						System.out.println("Procedure not defined");

					// COM ABRE PARENTESES?????????
				}
					break;

			// 25'
			case 23: {

					if(!c_id.equals("function"))
						System.out.println("Function not defined");

					else if(!c_id.equals("parameter"))
						System.out.println("Parameter not defined");

					else if(!c_id.equals("variable"))
						System.out.println("Variable not defined");

					else if(!c_id.equals("constant"))
						System.out.println("Constant not defined");

					// SEM ABRE PARENTESES??????
				}
					break;

			// 26
			case 24: {

				// Verificar melhor esse caso!!!!!!!
					tranformar a cadeia de dígitos em tipo numérico 
							(inteiro, quando for o caso)
				}
					break;
		}
	}
}
