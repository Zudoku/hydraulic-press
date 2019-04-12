# Testausdokumentti

## Mitä on testattu, miten tämä tehtiin
Sovelluksen testauksessa keskitytään kolmeen kulmaan: 

1. Sovelluksen suorituskyvyn testaus: sovelluksen suoritusnopeus, sekä kompressioprosentti  (manuaalinen testaus)
2. Sovelluksen tietorakenteiden toimivuuden testaukseen (yksikkötestit)
3. Sovelluksen tuottaman ulostulon oikeellisuus (yksikkötestit)

Tavoitteena on saada jokaiselle tietorakenteelle projektissa yksikkötestit jotka testaavat julkisia metodeita. Testien tarkoitus on varmistaa, että tietorakenteet toimivat oikein (edge caseissa myös). Esimerkiksi, että binääripuuhun uuden noden lisääminen oikeasti toimii, ja sen voi myös sieltä löytää, tai että Huffmanin puu rakentuu oikein serialisoidusta datasta.

Yksikkötesteillä suoritetaan myös "end to end" testausta, jossa suoritetaan ohjelmaa: pakataan dataa ja puretaan se ja tarkistetaan että sama data tulee ulos, kuin mitä annettin sisälle.

Viimeinen, eli vaikein testattava asia, on suorituskyvyn testaus. Se tehdään manuaalisella testaamisella, suorittamalla ohjelmaa eri syötöillä ja mittaamalla tuloksia jotka kerätään kaavioksi / taulukoksi alle. Mitattavia suureita on tiedoston koko vs. suoritusnopeus, sekä erityylisten tiedostojen pakkaaminen eri tavoilla ja pakkaustoteutusten vertailua. Ohjelman suorituskyky-testaus automatisoidaan luomalla skripti ja testin voi toistaa tulevaisuudessa yhdellä komennolla. Tulosten siirtäminen graafiseen muotoon on tehty käsin.

## Minkälaisilla syötteillä testaus tehtiin (vertailupainotteisissa töissä tärkeää

Manuaalisen testaamisen syötteet löytyvät paikasta ___.  Yksikkötesteissä syötteet on määritelty testeissä.

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

### Suorituskyky-testaus

Suorituskyky-testaus automatisoidaan luomalla skripti / java luokka joka suorittaa ohjelmaa ja tulostaa tulokset terminaaliin .Testin Tämän testausmuodon ohjeet tulevat tähän pian.
## Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.
TODO
