##Testausdokumentaatio
Ohjelma ei erikseen testaa että jokaisesta huoneesta on kulkuyhteys kaikkiin muihin huoneisiin,
sillä sen testaaminen olisi melko haastavaa ja raskasta, varsinkin suurilla kartoilla.
Tämän testaaminen olisi myös teoriassa turhaa, sillä huoneet yhdistetään käytävillä, jotka luodaan niin että
jokaisesta huoneesta n on käytävä huoneeseen n-1 kun n > 1. Tästä seuraa että kaikkien huoneiden välille syntyy kulkuyhteys, eikä sitä
mielestäni tarvitse erikseen testata. Tilanne muuttuisi jos kentälle luotaisiin esimerkiksi maa-alueita joiden päälle pelaaja ei voi kulkea
tai muuta vastaavaa, tällöin kulkuyhteys olisi tarkastettava esimerkiksi jollain reitinhakualgoritmillä.

Lisäksi ohjelman olisi hyvä tarkastaa pelin alussa että vihollisten etäisyys pelaajaan on riittävän suuri.
Tämä testi jäi nyt pois sillä tällä hetkellä hahmoja voidaan luoda vain huoneiden keskelle, mutta olisi hauskempaa jos 
vihollisiin voisi törmätä myös vaikka käytävillä. Nyt vihollisia voi siis olla enintään huoneiden lukumäärä-1 kappaletta, kun taas satunnaisella
spawnaamisella määrä voisi olla huomattavasti suurempi.

Tällä hetkellä pelin oikeastaan ainut bugi on että viholliset eivät aina onnistu löytämään lyhyintä reittiä pelaajan luokse ja saattavat
joskus jäädä nykimään edestakaisin kahden ruudun välille. Ongelma olisi ollut helppo korjata käyttämällä tälläisessä tilanteessa 
jotain "oikeaa" reitinhakualgoritmia, mutta en ehtinyt tehdä sitä.
