# Semestrální práce ALG2 #
- - - -
## Zadání ##
Hlavní funkcí programu ALDtest je zadání testu s 10ti otázkami a následně jeho vyhodnocení. Uživatel zadá své ID, jméno, přijmení a rok studia a následně se mu přidá známka z testu a den kdy test psal. Pak je student přidán k dalším studentům, kteří již test psali. Program čte data ze souborů otazky.txt a studenti.txt, do tohož i rovnou zapíše dalšího studenta. Dále program umožňuje seřadit a zobrazit studenty podle hodnocení, přijmení nebo ročníku studia. Dále je tu možnost zobrazit všechny možné otázky na obrazovku nebo v pdf souboru. V poslední řadě uživatel může hledat v souboru otázka zadáný výraz a kolikrát se tam vyskytuje. 

- - - -
## Návrh řešení ##
### Funkční specifikace ###
1. Načíst test
2. Načíst studenty
3. Psát test 
    * Přidat nového studenta
    * Zadat mu test
    * Vyhodnotit test pro daného studenta
    * Přidat studenta k ostatním
4.    Zobrazit všechny studenty
5.    Seřadit studenty podle hodnocení
6.    Seřadit studenty podle přijmení
7.    Seřadit studenty podle ročníku
8.    Zobrazit všechny otázky
9.    Exportovat otázky do pdf souboru
10.	Hledat zadaný výraz a jeho počet výskytů v otázkách
- - - -
### Popis struktury souborů ###
   * Soubor s otázkami (OtazkyALG2Semestral.txt):  
   Proměnné: question, correctAnswer, answerA, answerB a answerC, jsou datového typu string a            následně uložené do Dynamického pole
   Čtení dat probíhá podle řádků nikoli pomocí oddělovače
   * Soubor se studenty (StudentiALGSemestral.csv): (<-- two spaces)
      Proměnné: ID (5ti místné), rocnik (1 místné) a hodnoceni(1 místné), jsou datového typu int 
      Proměnné: jmeno a prijmeni, jsou datového typu string 
      Proměnná denTestu je datového typu LocalDate
      Všechny proměnné jsou následně uloženy do Dynamického pole
      Čtení dat probíhá pomocí oddělovače “,”
      Do steného souboru probíhá I zápis pomocí PrintWriter
   * Binární soubor se studenty:
   Data jsou do binárního souboru zapisována pomocí DataOutputStream. Porgram zapisuje proměnné ID a    hodnoceni. 
   - - - -
## Popis fungovaní externí knihovny ##  
V své semestrální práce jsem přidal soubor itext.jar do Libraries, pomocí něj lze převádět text do formátu pdf. V mém případě v menu aplikace uvádím možnost exportovat všechny otázky, které se v testu mohou vyskytovat do souboru ve formátu pdf.




