# sing-testiranje-2020

Projekat iz testiranja softvera.
Za dobijanje šeme baze podataka, u MySQL Workbench otvoriti priloženi model,
pa zatim ići na opciju Database -> Forward Engineer
U projektu je potrebno promeniti korisničko ime i lozinku za pristup bazi podataka,
Trenutno je ostavljeno root/root. Podaci se postavljaju u klasi DatabaseConnection,
u metodi getConnection, 23. linija.

Potrebno je pronaći sve, ili što više bug-ova u projektu i zapisati ih u izveštaje
koji su dostavljeni na stranici predmeta -> Vežbe -> Završni ispit. **Izveštaji su obavezni.**

Pri bodovanju se posmatraju sledeće stavke:
*  Broj pronađenih bug-ova
*  Kvalitet izveštaja
*  Kvalitet i preglednost testova
*  Efikasnost testova (izbegavanje ponavljanja itd.)

Ukoliko ne možete da se snađete sa bazom podataka, možete izmeniti projekat tako da radi
isključivo sa unapred definisanim listama, ali to će uticati na konačne bodove.

Po potrebi izmeniti pom.xml, ukoliko koristite drugu verzi Java kompajlera (u dokumentu je postavljeno na verziju 13).