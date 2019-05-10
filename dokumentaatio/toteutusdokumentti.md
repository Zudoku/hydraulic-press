# Toteutusdokumentti

## Ohjelman yleisrakenne
Ohjelma suoritus on jaettu seuraaviin osiin: 
1. Argumenttien parsinta logiikka ja datan lukeminen tiedostosta
2. "Operaation toteutus"
3. Operaation tuloksen tallentaminen tiedostoon.

Kohta 2. eli itse ohjelman logiikka, on toteutettu seuraavasti:

HydraulicPressInstance luokka kuvastaa yhtä kierrosta ohjelman suoritusta. Se suorittaa siis yhden kompression tai dekompression datalle ja palauttaa arvoksi tuloksen, missä data on (de)kompressoitu. Se ottaa vastaan aina datan mitä muokataan ja operaation, mitä datalle tehdään. Operaatio voi olla esimerkiksi COMPRESS_HUFFMAN_CODING tai DECOMPRESS_LZ77.

HydraulicPressInstance kutsuu operaation perusteella oikeaa luokkaa. Esim jos operaationa on COMPRESS_HUFFMAN_CODING, luo HydraulicPressInstance CompressHuffManCoding luokan ja kutsuu sitä. CompressHuffManCoding luokan tehtävä on tehdä vain kyseinen operaatio. Se pitää sisällään viittauksia tietorakenteisiin, joita kompressoinnissa käytetään, esim HuffmanTree joka kuvastaa Huffmanin puuta, tai BinaryTree joka kuvaa binääripuuta.

Apuna on käytetty BitBlob tietorakennetta, joka kuvastaa jonoa bittejä, joka on n pituinen. Näitä voi yhdistellä, jolloin bittien käsittely on kivempaa. Myös erilaisia utility metodeita on käytetty, jotka sijaitsevat ByteUtils luokassa.

## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
TODO

## Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
Suorituskykyvertailua löytyy [testausdokumentista](/dokumentaatio/testausdokumentti.md).

## Työn mahdolliset puutteet ja parannusehdotukset
- Suorituskyky (ei ole käytetty optimaalisia tietorakenteita)
- Ei voi antaa parametrina output tiedostonimeä
- Ei voi antaa parametrina LZ77 buffereiden pituutta (vaikutusta pakkausprosenttiin)
- Heikko testikattavuus
- Projektissa voisi olla parempi arkkitehtuuri ja dokumentointi
- Koodin luettavuus paikoitellen heikkoa

## Lähteet
- https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
- http://jens.jm-s.de/comp/LZ77-JensMueller.pdf
- https://en.wikipedia.org/wiki/Huffman_coding
- https://en.wikipedia.org/wiki/LZ77_and_LZ78
- https://www.baeldung.com/java-binary-tree
- https://www.geeksforgeeks.org/min-heap-in-java/

