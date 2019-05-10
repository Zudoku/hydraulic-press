# Testausdokumentti

## Mitä on testattu, miten tämä tehtiin
Sovelluksen testauksessa keskitytään kolmeen kulmaan: 

1. Sovelluksen suorituskyvyn testaus: sovelluksen suoritusnopeus, sekä kompressioprosentti  (automaattinen ohjelman suoritus batch skriptin avulla)
2. Sovelluksen tietorakenteiden toimivuuden testaukseen (yksikkötestit)
3. Sovelluksen tuottaman ulostulon oikeellisuus (yksikkötestit)

Tavoitteena on saada jokaiselle tietorakenteelle projektissa yksikkötestit jotka testaavat julkisia metodeita. Testien tarkoitus on varmistaa, että tietorakenteet toimivat oikein (edge caseissa myös). Esimerkiksi, että binääripuuhun uuden noden lisääminen oikeasti toimii, ja sen voi myös sieltä löytää, tai että Huffmanin puu rakentuu oikein serialisoidusta datasta.

Yksikkötesteillä suoritetaan myös "end to end" testausta, jossa suoritetaan ohjelmaa: pakataan dataa ja puretaan se ja tarkistetaan että sama data tulee ulos, kuin mitä annettin sisälle.

Viimeinen, eli vaikein testattava asia, on suorituskyvyn testaus. Se tehdään suorittamalla ohjelmaa eri syötöillä ja mittaamalla tuloksia jotka kerätään kaavioksi / taulukoksi alle. Mitattavia suureita on tiedoston koko vs. suoritusnopeus, sekä erityylisten tiedostojen pakkaaminen eri tavoilla ja pakkaustoteutusten vertailua. Ohjelman suorituskyky-testaus automatisoidaan luomalla skripti ja testin voi toistaa yhdellä komennolla. Tulosten siirtäminen graafiseen muotoon on tehty käsin.

## Minkälaisilla syötteillä testaus tehtiin

Manuaalisen testaamisen syötteet löytyvät paikasta projektin [juurikansiosta](https://github.com/Zudoku/hydraulic-press/tree/master/hydraulic-press). Testisyötteillä on pääte **.txt**  Yksikkötesteissä syötteet on määritelty testeissä.

## Miten testit voidaan toistaa
### Yksikkötestit:
Yksikkötestit sijaitsevat kansiossa `src/test/java`.

Yksikkötestestit voi toistaa komennolla `gradle :cleanTest :test`.

Projektiin on lisätty gradle wrapper, eli testaus komennon kutsuminen luonnistuu seuraavasti

Linux: 
```./gradlew :cleanTest :test```

Windows:
```./gradlew.bat :cleanTest :test```


Yksikkötestien tulokset näet kansiosta `./build/test-results/test/`

Jar tiedosto voidaan luoda komennolla `gradle :jar`.

Jar tiedosto löytyy kansiosta  `/build./libs./hydraulic-press.jar`.

### Suorituskyky-testaus

Suorituskyky-testaus automatisoidaan luomalla skripti joka suorittaa ohjelmaa ja tulostaa tulokset terminaaliin.
Testin voi suorittaa seuraavilla komennoilla (.bat skripti)

```./lz77Test.bat```
```./huffmanCodingTest.bat```

## Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.


### Tulokset taulukossa:

#### HUFFMAN CODING

| FILE | ORIGINAL (B) | COMPRESSED (B) | TIME-COMPRESSION (MS) | TIME-UNCOMPRESSION (MS) |
|------|----------|------------|------------------|--------------------|
|   testdata1.txt  |   9671   |    5354    |        29        |         26         |
|   testdata2.txt  |   24731  |    17966   |        57        |         69         |
|   testdata3txt  |  465995  |   260646   |       15454      |        15454       |
|   testdata4.txt  |    12    |     23     |         4        |          4         |
|   testdata5.txt  |   7814   |    5508    |        26        |         22         |
|   testdata6.txt  |   25347  |    16067   |        52        |         86         |

![kompressio](/dokumentaatio/huffman.png "Kompressio huffman coding")

#### LZ 77

| FILE | ORIGINAL (B) | COMPRESSED (B) | TIME-COMPRESSION (MS) | TIME-UNCOMPRESSION (MS) |
|------|----------|------------|------------------|--------------------|
|   testdata1.txt  |   9671   |    8030    |        94        |         87         |
|   testdata2.txt  |   24731  |    19552   |        254       |         322        |
|   testdata3.txt  |  465995  |   328129   |       37104      |        40749       |
|   testdata4.txt  |    12    |     15     |         4        |          4         |
|   testdata5.txt  |   7814   |    5499    |        51        |         44         |
|   testdata6.txt  |   25347  |    22771   |        313       |         401        |

![kompressio](/dokumentaatio/lz77.png "Kompressio lz77")

