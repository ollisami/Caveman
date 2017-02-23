Aihe: Vuoropohjainen peli jossa seikkaillaan luolastossa ja vältellään vihollisia. Pelaaja etenee rappusia pitkin seuraavalle tasolle
ja yrittää päästä mahdollisimman suurelle tasolle.

Ohjeet: Liikuta pelaajaa (keltainen olio) nuolinäppäimillä ja yritä vältellä vihollisia (punaiset oliot).

Käyttäjät: Pelaaja, vihollisten tekoäly

Kaikkien käyttäjien toiminnot:
- Liikkuvat jokaisella vuorolla yhden askeleen pysty- tai vaakasuunnassa

Pelaajan toiminnot:
- Pelaaja pystyy siirtymään luolastojen välillä

Vihollisten toiminnot:
- Kun pelaaja on lähettyvillä viholliset liikkuvat kohti pelaajaa
- Pelaaja kuolee jos vihollinen koskettaa pelaajaa

luola generaattorin toiminnot:
- Uusi luolasto luodaan aina kun pelaaja siirtyy seuraavalle tasolle.
- Edelliset luolastot ovat aina samanlaisia jos pelaaja päättää palata niihin 

Rakennekuvaus:
- Pelin ytimenä toimii GameController-luokka, joka luo alussa muut controllerit (MapController, GraphicsController ja AvatarController).

- Kun peli alkaa luodaan MapControlleriin uusi kartta (Map). Kartta toteutetaan matriisina johon tallennetaan id-arvoja jotka GraphicsController muuntaa kuviksi. Kartta asetta aluksi kaikki alueet vedeksi johon piirretään n-määrä huoneita. Kun huoneita on n > 1, yhdistetään huone n käytävällä huoneseen n-1. Tästä seuraa että jokaiselle huoneelle n on polku huoneeseen n-1, eli toisin sanoen jokaisesta huoneesta x on myös polku huoneeseen n = 1. Kun n-määrä huoneita on luotu muutetaan kaikki vettä koskettavat maa-alueet seiniksi. Maa-alueta on kahta erityyppiä, normaali maa ja puska. Pelaaja voi piiloutua puskaan vihollisita, jotka eivät osaa kulkea puskien lävitse.

- Kun kartta on luotu siirrytään luomaan hahmoja (Avatar). Erillaisia hahmoluokkia ovat pelaaja (Player), vihollinen (Enemy). Viholliset ja pelaaja arvotaan erillisiin tyhjiin huoneisiin, jotta vältyttäisiin siltä että vihollinen saavuttaa pelaajan heti pelin alussa. Pelaajaa ohjataan nuolinäppäimillä, vihollisia ohjaa tekoäly. Viholliset eivät käytä varsinaista reitinhakualgoritmia liikkumiseen, vaan laskevat oman välimatkansa pelaajaan ja jos pelaajaa on riittävän lähellä laskee peli luomallani algoritmilla viholliselle suunnan josta se pääsee parhaiten pelaajaan käsiksi. Algoritmini toimii ajassa O(4n), missä n on matka nykyisestä sijainnista haluttuun määränpäähän. Vihollisten ja pelaajan tapauksessa n <= 10, sillä viholliset eivät hae reittiä mikäli matka pelaajaan on yli 10. Eli pahimmillaan suunnan hakemiseen kuluu aikaa 4*10 = 40 operaatiota / vihollinen, kartan koosta riippumatta. Tämän ansiosta peli toimii nopeasti myös isoilla kartoilla ja vihollis määrillä, joskin sen hinnalla etteivät viholliset aina onnistu löytämään lyhyintä reittiä kohti pelaajaa.

- Lopuksi peli piirtää grafiikat. Grafiikat leikataan yhdestä kuvatiedostosta, jokainen pala vastaa tiettyä grafiikka-id:tä ja GraficsController yhdistää id:t ja grafiikat yhteen. Jotta peli pyörisi sujuvammin ei koko kenttä piirretä kerralla (mikä olisi käytännösä mahdotonta jos kentän koko on esimerkiksi > 500x500), vaan haetaan kartalta pelaajan sijainti ja tulostetaan vain sitä ympäröivät alueet. 

![ALT text](https://github.com/ollisami/Caveman/blob/master/dokumentaatio/Luokkakaavio.png)
![ALT text](https://github.com/ollisami/Caveman/blob/master/dokumentaatio/kaaviot/Drawing_a_map.png)
![ALT text](https://github.com/ollisami/Caveman/blob/master/dokumentaatio/kaaviot/Move_avatars.png)
![ALT text](https://github.com/ollisami/Caveman/blob/master/dokumentaatio/kaaviot/repaint_canvas.png)
