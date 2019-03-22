Otsikko
====

## Mitä olen tehnyt tällä viikolla?
Aloitin toteuttamaan Huffman Coding pakkausta. Käytännössä ohjelma lukee nyt tiedoston byte taulukkoon ja osaa rakentaa siitä Huffmanin puun, mutta se ei osaa vielä serialisoida puuta semmoiseen muotoon että se voitaisiin tallentaa tiedostoon, eikä siihen ole vielä toteutettu metodia, joka antaa pakatut bitit jollekkin ei pakatulle datalle. Samoin pakkauksen purkaminen on toteuttamatta.

## Miten ohjelma on edistynyt?
Olin toivonut, että saisin tähän palautukseen valmiiksi alustavan toteutuksen huffman coding pakkauksesta ja purkamisesta. Työ on edennyt melko hyvään tahtiin, mutta ongelmana on ollut projektiin käytetyn ajan vähyys. Vaikka en tähän palautukseen ehdi saada kaikkea haluamaani (ja vaadittavaa) edistystä, yritän saada sunnuntaihin 24.3.2019 mennessä toteutettua tuon Huffman Coding pakkausalgoritmin loppuun, sekä unit-testit, jotka kattavat huffmanin puun rakennuksen annetusta datasta, ja sen, ettei pakkaamisessa häviä mitään dataa.

## Mitä opin tällä viikolla / tänään?
Tällä viikolla itselleni selkeytyi se, miten huffman coding toimii, ja se miten aion sen toteuttaa. 

## Mikä jäi epäselväksi tai tuottanut vaikeuksia?
Työ on vienyt enemmän aikaa kuin kuvittelin. Eniten päänvaivaa on ollut miettiä, miten pystyisin järkevästi toteuttaa bittien lisäämistä yksi bitti kerrallaan ulosannettavaan dataan. Tähän mennessä ei ole tullut vastaan ongelmia mitä en ole pystynyt ratkaisemaan (muita kuin aikataulu ongelmia). 

## Mitä teen seuraavaksi?
Seuraavaksi viimeistelen Huffmanin koodauksen ja aloitan LZ77 algoritmin toteutusta. Lisään myös koodin tyylitys sääntöjä ja kirjoitan jo olemassa olevalle koodille testejä.

## Omia mietteitä
Yksi haaste, on miettiä miten ottaa huomioon pakattavan datan bufferin suuruus huffmanin koodauksessa. Eli siis se, kuinka iso alue ajatellaan yhden koodattavan datan olevan (byte? char? 64 byteä?). Yleensä esimerkeissä tämän pituus on yksi kirjain.  Olen toteuttanut tällä hetkelläsen ohjelmaan niin, että tarkasteltavan alueen suuruus on 1 byte kerrallaan, mutta olisi ehkä hyvä jos suuruuden voisi määritellä jotenkin argumenttina.

## Kysymyksiä:
Onko sallittua käyttää javan java.io.File ja java.io.FileInputStream luokkia tiedoston lukemiseen byte taulukoksi?
ks. [tämä](./../hydraulic-press/src/main/java/fi/zudoku/hydraulic/util/FileUtils.java)

Tällä viikolla käytin projektiin noin 12 tuntia.
