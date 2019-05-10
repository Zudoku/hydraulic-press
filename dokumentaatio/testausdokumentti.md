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
TODO
