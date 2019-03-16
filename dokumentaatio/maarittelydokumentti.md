Projektin määrittely
====

Projektin päämääränä on toteuttaa tiedostonpakkaus-ohjelma Javalla, joka käyttää DEFLATE-algoritmia. Deflate-algoritmi on häviötön algoritmi, joka tarkoittaa sitä, että data voidaan pakata ja purkaa, niin ettei tietoa katoa. Se perustuu Huffmanin koodaukseen, sekä Lempel Ziv-algoritmiin (LZ77), jotka tullaan myös toteuttamaan projektiin. 

Valitsen projektissa DEFLATE-algoritmin, koska se on hyvin laajalle levinnyt ja sitä käytetään osana muita tiedostonpakkaus algoritmeja, enkä koe sen olevan tämän projektin aikataulun ulkopuolella. Projekti keskittyy itse algoritmin toteuttamiseen, mutta toissijaisena päämäränä on saada ohjelma tallentamaan pakattu tiedosto gzip tiedostomuotoon, jotta ohjelman tuottamia tiedostoja voidaan purkaa myös muilla ohjelmilla tai vastaavasti toisinpäin. Gzip tiedostomuoto (.gz) lisää DEFLATE pakattuun dataan otsaketietoja, sekä tarkistussumman.


## Mitä tietorakenteita toteutat työssäsi?
Huffmanin koodauksessa käytetään minimi-kekoa (min heap), kun rakennetaan Huffmanin puuta. Valmis Huffmanin puu esitetään puu tietorakenteena. Lempel Ziv algoritmissa hakubufferi tullaan esittämään jonkinlaisena hakupuuna. Jos projektin aikana tarvitaan muita tietorakenteita, tullaan tätä dokumenttia päivittämään.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään
Ohjelmaa käytetään komentoriviltä. Se ottaa syötteeksi polun tiedostoon, joka puretaan tai pakataan, valittavan algoritmin, ja tiedon, ollaanko purkamassa vai pakkaamassa tiedostoa. Ohjelma suoriutuu ja tekee tiedoston jossa sisäänotettu tiedosto purettuna / pakattuna.

## Tavoitteena olevat aika- ja tilavaativuudet
Tavoitteena on parempi pakkauskoko kuin Huffmanin koodauksella ja Lempel Ziv algoritmilla yksittäin. Aika- ja tilavaativuudet tullaan lisäämään tälle sivulle pian.

## Lähteet
- https://www.ietf.org/rfc/rfc1951.txt
- https://www.ietf.org/rfc/rfc1952.txt
- https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
- http://jens.jm-s.de/comp/LZ77-JensMueller.pdf
- https://en.wikipedia.org/wiki/DEFLATE
- https://en.wikipedia.org/wiki/Huffman_coding
- https://en.wikipedia.org/wiki/LZ77_and_LZ78
