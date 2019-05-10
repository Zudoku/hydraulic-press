Käyttöohje
====

## Miten ohjelma suoritetaan, miten eri toiminnallisuuksia käytetään
```
Usage: java -jar hydraulic-press.jar [FILENAME] [OPERATION]

[FILENAME] = relative filepath to the input file.
[OPERATION] = operation code
1. = COMPRESS_HUFFMAN_CODING
2. = DECOMPRESS_HUFFMAN_CODING
3. = COMPRESS_LZ77
4. = DECOMPRESS_LZ77
```

## Minkä muotoisia syötteitä ohjelma hyväksyy
Ohjelma hyväksyy minkä muotoisia syötteitä tahansa, kunhan ne on annettu yllä mainitussa formaatissa

## Missä hakemistossa on jar ja ajamiseen tarvittavat testitiedostot.

Jar tiedosto voidaan luoda komennolla `gradle :jar`.

Jar tiedosto löytyy kansiosta  `/build./libs./hydraulic-press.jar`.
