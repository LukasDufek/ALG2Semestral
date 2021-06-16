# Semestrální práce ALG2 #
- - - -
## Zadání ##
Hlavní funkcí programu ALDtest je zadání testu s 10ti otázkami a následně jeho vyhodnocení. Uživatel zadá své ID, jméno, přijmení a rok studia a následně se mu přidá známka z testu a den kdy test psal. Pak je student přidán k dalším studentům, kteří již test psali. Program čte data ze souborů OtazkyALG2Semestral.txt a StudentiALGSemestral.csv, do tohož i rovnou zapíše dalšího studenta. Dále program umožňuje seřadit a zobrazit studenty podle hodnocení, přijmení nebo ročníku studia. Dále je tu možnost zobrazit všechny možné otázky na obrazovku nebo v pdf souboru. V poslední řadě uživatel může hledat v souboru otázka zadáný výraz a kolikrát se tam vyskytuje. 

- - - -
## Návrh řešení ##
### Funkční specifikace ###
1. Psát test 
    * Přidat nového studenta
    * Zadat mu test
    * Vyhodnotit test pro daného studenta
    * Přidat studenta k ostatním
2. Zobrazit všechny studenty
    * podle hodnocení
    * podle přijmení
    * podle ročníku
3. Zobrazit všechny otázky
4. Exportovat otázky do pdf souboru
5. Hledat zadaný výraz a jeho počet výskytů v otázkách
- - - -
### Popis struktury souborů ###
   * Soubor s otázkami (OtazkyALG2Semestral.txt):  
      Na prvním řádku souboru je uložená otázka v podobě textového řetězce. Na dalším řádku je označení správné odpovědi v podobě jednoho písmene. Na dalších třech řádcích jsou       možnosti odpovědi například v podobě  "a) FIFO" atd.
   
   * Soubor se studenty (StudentiALGSemestral.csv): <br/>
      Data jsou v soboru uložena do několika řádků, přičemž v jednom řádku jsou údaje o jednom studentovi. Na první pozici jeho 5ti místné ID, jméno, přijmení, ročník, hodnocení       a datum ve tvaru "yyyy-m-dd", které ukládá údaje o tom, kdy tento student test psal. Všechna data v řádku jsou oddělena mezerou. 

   * Binární soubor se studenty(Studenti2021.dat): <br/>
     Data jsou do binárního souboru zapisována pomocí DataOutputStream. Porgram zapisuje pro jednoho studenta zapisuje pouze jeho ID a hodnoceni jako Integery. 
   - - - -
## Popis fungovaní externí knihovny ##  
V své semestrální práce jsem přidal soubor itext.jar do Libraries, pomocí něj lze převádět text do formátu pdf. V mém případě v menu aplikace uvádím možnost exportovat všechny otázky, které se v testu mohou vyskytovat do souboru ve formátu pdf.




