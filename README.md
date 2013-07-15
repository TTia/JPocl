#JPocl - J(Piece of cake language)

###Introduzione

JPocl � un compilatore sviluppato a scopo didattico come progetto finale del corso di
[IPL](http://www.disi.unige.it/person/AnconaD/IPL/index.html), tenuto per l'anno accademico 2013/2014 dai 
professori [Davide Ancona]() e [Giovanni Lagorio]().
Obiettivo del progetto � lo sviluppo di un compilatore, che possa generare codice eseguibile sulla [Java Virtual Machine]().

###Sviluppo
Per lo sviluppo di JPocl sono stati utilizzati differenti strumenti:
- [ANTLR v3]()
- [ANTLRWorks v1.4]()
- l'IDE [Eclipse](), dotato del plugin [ASM Bytecode]()
- [JasminXT]()

Durante lo sviluppo del progetto � stata sfruttata ampiamente la tecnica del TDD; oltre al codice strettamente
necessario al compilatore � presente un package *test*, utile sia per la verifica delle funzionalit� che come
documentazione dell'intero sistema.

##Il linguaggio
###Tipi di dato
In JPocl sono presenti due tipi di dato predefiniti, *int*, *bool* e *void*. E' inoltre possibile definire i propri tipi
strutturati.

	struct data{int giorno; int mese; int anno;}
	struct progetto{int giorniSpesi; bool agile; struct data inizio;}
	p = struct progetto{10, true, struct data{1,7,2013}}

E' possibile codificare le proprie espressioni attraverso i principali operatori aritmetici e logici, sia 
fruttare operatori pi� complessi come il fattoriale e l'elevamento a potenza.
JPocl non prevede l'esplicita dichiarazione delle variabili, ma vi attribuisce staticamente il tipo 
al primo assegnamento, vincolandone gli usi seguenti.

	a = 1+2
	a = a+3
	a = (a == 6) -> int and bool are incompatible @[3,2]
	
Data un'instanza di tipo strutturato, � possibile accedere ai campi utilizzando l'operatore *"."*.

	b = struct data{12,07,2013}.giorno
	c = struct progetto{10, true, struct data{1,7,2013}}
	c.inizio = b

###Funzioni
In JPocl fornisce all'utente una piccola libreria, composta dalle funzioni:
- int min(int a, int b)
- int max(int a, int b)
- void runScript() (La funzione *runScript* � il punto di accesso di ogni script JPocl, ed � definita dalle istruzioni contenute in essi)

La libreria previene l'utente dall'eventuale ridefinizione delle funzioni elencate, ed � definita separatamente dalla
grammatica di JPocl.
*Nota: potenzialmente l'utente pu� sfruttare in maniera ricorsiva la funzione runScript all'interno del proprio codice. (A proprio rischio e pericolo! :))*

	struct data initData(int giorno, int mese, int anno){
		d = struct data(giorno, mese, anno)
		return d
	}
	echo initData(12,07,2013).mese -> 7

	struct data meseProssimo(struct data oggi){
		meseProssimo = struct data{oggi.giorno, oggi.mese, oggi.anno}
		meseProssimo.mese = (meseProssimo.mese + 1) % 12
		return meseProssimo
	}
	
	oggi = struct data{31,12,2013}
	echo meseProssimo(oggi) -> data[31,1,2013]
	echo meseProssimo(meseProssimo(oggi)) -> data[31,2,2013]

Non � possibile effettuare overloading delle funzioni definite, n� ricorrere alla ricorsione mutua, mentre � possibile
utilizzare la ricorsione semplice per definire i propri algoritimi.

	int mcd(int a, int b){
		r = a % b
		if(r == 0) return b
		return mcd(b,r)
	}

	echo mcd(256,512)

Vengono riconosciuti come errori:
- la mancanza di espressioni *return* all'interno di una funzione con tipo di ritorno diverso da *void*;
- l'uso di pi� espressioni *return* non condizionate;

###Espressioni

	bool isPrime(int n){
		i = 2
		while(i<n){
			if (n%i==0) return false
			i = i + 1
		}
		return true
	}
	echo isPrime(37) -> true

	d = struct data{31,12,2013}
	p = struct progetto{10,true, d}
	echo p -> progetto[10,true,data[31,12,2013]]

Oltre agli operatori e ai costrutti gi� mostrati, JPocl espone altre funzionalit�:
- le espressioni condizionali *if* e *while* (il simbolo *{*, se utilizzato, deve appartenere alla stessa riga del costrutto);
- il comando *echo* per la stampa su terminale, � utilizzabile sia per i tipi di dato primitivi che per le istanze di tipi strutturati;
- l'operatore *==* effettua il confronto fra due espressioni delle stesso tipo, anche fra istanze di tipi strutturati, confrontando i rispettivi campi.
	
###Type-Checking
I messaggi di errore prodotti durante la fase di verifica statica del codice sorgente si presentano nella forma *Messaggio @[linea, colonna]*.
I casi di errore gestiti sono i seguenti:
- dichiarazione di funzioni con parametri duplicati;
- dichiarazione di tipi strutturati con campi duplicati;
- uso invalido di espressione di tipi *void*;
- definizione di espressioni contenenti tipi incompatibili;
- incompatibilit� fra l'espressione *return* e il tipo di ritorno dichiarato nella funzione;
- funzione non applicabile agli argomenti dati;
- nuova dichiarazione di una funzione gi� dichiarata nel codice;
- nuova dichiarazione di un tipo strutturato gi� dichiarato nel codice;
- codice irraggiungibile;
- assenza di espressioni *return*;
- uso di tipo non dichiarato, o non visibile;
- uso di funzione non dichiarata, o non visibile;
- identificatore non dichiarato, o non visibile;

Per semplificare la definizione del type-checker, � stato scelto di anticipare alcuni controlli alla fase di parsing.
In particolare, l'uso di espressioni *return* � limitato al corpo delle funzioni (sfruttando gli operatori semantici di ANTLR) e 
l'operatore *"."* � applicabile solo ad istanze di tipi strutturati o ad identificatori.

###Scoping
Di seguito sono riportati alcuni esempi di scoping in JPocl.

	a = 0
	void f(bool a){
		echo a && false
	}
	echo a + 1

La variabile intera a, anche se dichiarata nello scope top-level, non � visibile all'interno di f(Z). In maniera simile,
il parametro a non � visibile all'esterno.

	struct data{int giorno; int mese; int anno;}
	void f(){
		echo struct data{1,2,2013}
	}
	
Le dichiarazioni di tipo, come per le funzioni, se dichiarate nello scope top-level, sono visibili in qualsiasi blocco.

	{
		struct X{int x;}
	}
	{
		struct X{int x;}
	}
	
Nonostante appartengano a due blocchi separati, non � possibile effettuare una seconda dichiarazione del tipo X.
Anche per le dichiarazioni di funzioni si applica lo stesso criterio.

###Compilazione e generazione
Per definire il lexer ed il parser, � stato sfruttato il codice prodotto durante i laboratori del corso.
Sono state necessarie solo alcune correzioni, per semplificare maggiormente il codice. Anche il sotto-sistema
relativo al controllo statico non ha subito variazioni importanti.
I file relativi alla grammatica e al controllo statico, sono rispettivamente *"JPoclAST.g"* e *"JPoclTypedAST.g"*.

Il processo di compilazione si compie nelle seguenti fasi:
- il lexer *JPoclASTLexer* analizza l'input e produce la sequenza di token;
- il parser *JPoclASTParser* ottiene la sequenza di token e produce un AST di tipo *TypeTree*;
- il type-checker *JPoclTypedAST* inferisce i tipi dei nodi ed effettua i controlli statici;
- il generatore *JPoclGenerator* genera codice *Jasmin*, sfruttando lo String Template Group *JPoclGenerator.stg*;
- l'output cos� prodotto viene suddiviso in diversi file ".j" e assemblato sfruttando la libreria *"jasmin.jar"*;
- i file ".class" prodotti da Jasmin vengono eseguiti.

Di seguito sono esposti i pattern di generazione di JPocl:
- le classi prodotte dal generatore apparterranno ad il package *"jpocl"*;
- le dichiarazioni di tipi strutturati generano una classe *jpocl.ID*, con visibilit� "public" se top-level, "default" altrimenti;
- per ogni campo di struct � generato un attributo nella classe di appartenza;
- per ogni classe sono definiti un costruttore, con i relativi parametri, ed i metodi *"equals(Object)"* e *"toString"*;
- viene creata una classe "*jpocl.Api*" contenente i metodi *"main"*, *"runScript"* e le altre funzioni incluse nel linguaggio;
- le espressioni presenti nel codice sorgente, ad eccezione delle dichiarazioni di tipi o funzioni, sono adeguatamente tradotte all'interno del metodo *"jpocl/Api.runScript"*.

Piccolo frammento di codice JPocl:

	struct TheAnswer{int answer;}
	echo struct TheAnswer{42}

Codice Jasmin generato:

	.class public jpocl/Api
	.super java/lang/Object
	
	.method public static main([Ljava/lang/String;)V
	  .limit stack 0
	  .limit locals 1
	  
	  invokestatic jpocl/Api.runScript()V  
	 	return
	
	.end method
	
	.method public static runScript()V
	  .limit stack 4
	  .limit locals 0
	
		getstatic java/lang/System/out Ljava/io/PrintStream;
		new jpocl/TheAnswer
		dup
		ldc 42
		invokespecial jpocl/TheAnswer.<init>(I)V
		invokevirtual jpocl/TheAnswer.toString()Ljava/lang/String;
		invokevirtual java/io/PrintStream.println(Ljava/lang/Object;)V
		
		return
	.end method
	
	.method public static min(II)I
	  .limit stack 2
	  .limit locals 2
	  
	  iload 0
	  iload 1
	  invokestatic java/lang/Math.min(II)I
	  ireturn
	.end method
	
	.method public static max(II)I
	  .limit stack 2
	  .limit locals 2
	  
	(...)
	.end method
	
	.method public static fact(I)I
	  .limit stack 4
	  .limit locals 2
	    
	  L0:
	   iload 0
	   iconst_1
	   if_icmpgt L1
	  L2:
	   iconst_1
	   ireturn
	  L1:
	   iload 0
	   iload 0
	   iconst_1
	   isub
	   invokestatic jpocl/Api.fact(I)I
	   imul
	   ireturn
	  L3:
	.end method
	
	##TheAnswer##
	.class  public jpocl/TheAnswer
		.super  java/lang/Object
		
		.field public answer I
		
		.method public <init>(I)V
		  .limit stack 2
		  .limit locals 2
		aload 0
		invokespecial java/lang/Object.<init>()V
		aload 0
		iload 1
		putfield jpocl/TheAnswer.answer I
		return
		.end method
	
		
		.method public toString()Ljava/lang/String;
		  .limit stack 3
		  .limit locals 1
		(...)
		.end method
	
		
		.method public equals(Ljava/lang/Object;)Z
		  .limit stack 2
		  .limit locals 3
		(...)
		.end method

Come si pu� osservare, Jasmin mette a disposizione un linguaggio leggermente pi� astratto rispetto al bytecode puro di Java,
non � presente ad esempio la gestione della tabella delle costanti, mantenendo per� le medesime istruzioni mnemoniche della JVM.
L'elemento "##TheAnswer##" non appartiene al sintassi di JasminXT, 
bens� separa le classi generate permettendo la redirezione dell'output su diversi file ".j".

Oltre alla generazione tramite String Template, � stato necessario individuare due algoritmi per la gestione dei "locals"
e dello "stack" dei vari control-frame. Il calcolo delle due soglie � stato effettuato all'interno della tree-grammar
*"JPoclGenerator.g"*.

	scope BlockLocals{
		LinkedList<String> locals;
		int localsLimit;
		boolean isFunctionBlock;
	}
	scope FunctionStack{
		int stackLimit;
	}

"BlockLocals" supporta il calcolo del limite di variabili per ciascuna funzione e mantiene una lista, utile per 
mantenere l'associazione (variabile, #num).
"FunctionStack" � definito unicamente da il campo stackLimit, utilizzato come contatore.
La definizione di due scope, unita alla possibilit� di effettuare accessi dinamici da qualsiasi produzione della
tree-grammar, ha semplificato l'applicazione degli algoritmi.

Per il calcolo del valore massimo di stack, � stato individuato un algoritmo che bilanciasse precisione e semplicit�
di applicazione, ottenendo valori leggermente abbondanti per alcuni casi particolari.

###Esecuzione
JPocl � eseguibile sia in Windows che in Linux; data la necessit� di eseguire il comando "java" viene richiesto
di specificare il proprio ambiente di esecuzione.

	JPocl usage: jpocl.jar -i InputFile [-o OutputDirectory] [-os {W,L}]
	Default output directory is ./output
	Default operative system is Linux(L)
	
L'eseguibile � all'interno della cartella Resources.
