Viikkoraportti: 4
====

## Mitä olen tehnyt tällä viikolla?
Tällä viikolla sain korjattua muutamia bugeja bittien käsittelyssä, huffmanin puun serialisoinnin, datan serialisoinnin huffmanin puun perusteella, huffman puun deserialisoinnin tiedostosta sekä huffmanin puun avulla kompressoidun datan deserialisoinnin huffmanin puun avulla.

## Miten ohjelma on edistynyt?
Tällä viikolla sain ohjelmaan selvää edistystä: Huffmanin koodaus on "valmis" ja se osaa kompressoida dataa ja decompressoida sen, tietoa hävittämättä ja riippumatta inputista. 

## Mitä opin tällä viikolla / tänään?
Mieleeni selkeytyi miten unsigned / signed arvot toimivat ja miten projektissa käsitellään bytejä. Ratkaisin ongelman omalla BitBlob tietorakenteella johon voi lisätä dataa yksi bitti kerrallaan.

## Mikä jäi epäselväksi tai tuottanut vaikeuksia?
Tällä viikolla jäi vajaaksi ehkä argumenttien parsinta ja jar tiedoston rakentaminen sovelluksesta. En usko että se tuottaa vaikeuksia, mutta se jäi puuttumaan viikolta.  Vaikeuksia tuotti javan signed bytejen hallinta (varsinkin negatiivisten numeroiden kohdallla) mutta luulen, että ymmärsin lopulta miten ne toimivat. Vaikeuksia on ollut toteuttaa tarpeellinen testaus projektille (kuten arvioinnissa mainittu).

## Mitä teen seuraavaksi?
Toteutan java argumenttien parsinnan ja teen sovelluksesta jar tiedoston ja toteutan lempel ziv 77 algoritmia.

## Vertaispalautteen testausohejeita
Valitettavasti en ehtinyt vielä tällä viikolla saada valmiiksi ohjelman rakentamista jar muotoon, sekä ohjelman argumenttien parsintaa. Jos haluat testata ohjelmaani, voit kloonata projektini, muokata *testdata.tst* tiedoston sisältöä, ja nähdä miten ohjelma kompressoi / decompressoi tiedoston sisällön huffmanin koodaus algoritmilla. Tulevaisuudessa ohjelmalle voi antaa argumenttina input ja output tiedostot.

Tällä viikolla käytin projektiin noin 25 tuntia.
