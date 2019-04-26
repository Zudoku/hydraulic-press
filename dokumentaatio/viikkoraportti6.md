Viikkoraportti: 6
====

## Mitä olen tehnyt tällä viikolla?
Lisäsin gradleen configuraation joka rakentaa projektista jar tiedoston, aloitin LZ77 kompression toteutuksen, sovellus tallentaa tuloksen nyt tiedostoon ja mittaa kuinka kauan suorituksessa meni. Samalla yritin myös optimoida huffmanin koodausta, 400 MB tiedoston kompressointinopeus väheni 14 kertaisesti (14s -> 1s). Vielä ongelmana on purkuun menevä aika.

## Miten ohjelma on edistynyt?
Saadut featuret ja mainittu yllä. Kehitys / edistystahti on ollut viikolla aika hidas.

## Mitä opin tällä viikolla / tänään?


## Mikä jäi epäselväksi tai tuottanut vaikeuksia?
Mietin pitkään, miten saisin käytettyä Suffix puuta tai Trie rakennetta LZ77 hakubufferissa, mutta päädyin toteuttamaan hakubufferin tehottomalla tavalla ilman hakupuuta, koska sen toteuttamisessa meni liian paljon aikaa enkä saanut mitään konkreettista aikaan. Pitämällä hakubufferi tarpeeksi pienenä (esim 32 bittiä), tässä ei varmaan mitään ongelmaa, mutta jos hakubufferi olisi iso (esim vaikka 16 000 bittiä) niin silloin tulee varmasti vastaan suorituskyky ongelmat. LZ77 kompressointi on osittain kesken, mutta uskon, että saan sen toteutettua ennen demoa ja viimeistä palautusta.

## Mitä teen seuraavaksi (ennen viimeistä deadlineä)?
Korvaan `PriorityQueue` java luokan omalla toteutuksella. 
Korvaan `ArrayListin` käytön omalla toteutuksella.
Viimeistelen LZ77 toteutuksen. 
Kirjoitan testejä kattavammin. 
Lisään testausdokumenttiin kuvaajan ja empiiristen testauksen tulokset.
Täydennän toteutusdokumenttia.
Jos aikaa jää, käytän aikani suorituskyky optimointiin. (esimerkiksi LZ kompressiossa ei käytetä hakupuuta tällä hetkellä, vaan tehotonta "omaa" toteutusta)



2 viikossa käytin projektiin noin 15 tuntia.
