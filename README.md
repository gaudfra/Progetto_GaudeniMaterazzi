# Progetto_GaudeniMaterazzi

Questo file serve ad agevolare la comprensione del progetto svolto dagli studenti Gaudeni Francesco e Materazzi Sandro

#### Come eseguire

Tramite command line ci si posizioni nella cartella /programmazioneoggetti e si digiti:

  mvn spring-boot:run
  
Si visiti poi il link http://localhost:8080/, attraverso cui vengono ritornati gli oggetti di tipo Misurazioni, dopo che in automatico è stato fatto il parsing del file JSON e il download del file .csv .

Attraverso rotte distinte si possono eseguire diverse operazioni sugli oggetti Misurazioni:

- /meta
- /stats
- /date_hour
- /date_hour_stats
- /date_filter
- /date_filter_stats
- /values_filter

#### Test per la verifica delle funzionalità

Per avere la lista dei metadati usare /meta.

Per avere la lista delle statistiche di tutte le misurazioni usare /stats.

Per avere la lista delle misurazioni con una certa data e ora usare /date_hour.

Per avere la lista delle statistiche sulle misurazioni con una certa data e ora usare /date_hour_stats.

Per avere la lista delle misurazioni comprese fra due date usare /date_filter.

Per avere la lista delle statistiche sulle misurazioni comprese fra due date usare /date_filter_stats.

Per avere la lista delle misurazioni comprese fra due valori di CPC e DMPS usare /values_filter.

<strong>Per i vari filtri se si omettono parametri o si inseriscono errati si avranno delle ricerche alternative coerenti.</strong>

#### Esempi di esecuzione

- tramite <strong>/meta</strong> vengono ritornati i metadati
<img width="904" alt="meta" src="https://user-images.githubusercontent.com/52038801/61414918-927fa600-a8ef-11e9-9829-aacb60762572.png">




- tramite <strong>/stats</strong> vengono restituite le statistiche su tutti i dati
<img width="904" alt="stats" src="https://user-images.githubusercontent.com/52038801/61414996-c955bc00-a8ef-11e9-9a07-3b276309a331.png">





- tramite <strong>/date_hour</strong> vengono restituiti tutti i dati con quella specifica data, gestendo eventuali eccezioni
<img width="904" alt="datehour_ok" src="https://user-images.githubusercontent.com/52038801/61415012-d96d9b80-a8ef-11e9-8fa4-28ee9ef0fb51.png">

  Esempio con eccezione: inserendo solo l'ora vengono ritornate tutte le misurazioni fatte in quella specifica ora 
 <img width="904" alt="datehour_soloora" src="https://user-images.githubusercontent.com/52038801/61415041-ebe7d500-a8ef-11e9-90b9-1bb0d5f3dbc4.png">





- tramite <strong>/date_hour_stats</strong> vengono restituite le statistiche sui dati con quella specifica data, gestendo eventuali eccezioni
<img width="888" alt="datehourstats" src="https://user-images.githubusercontent.com/52038801/61415091-189bec80-a8f0-11e9-9a68-1825b8a1cfa9.png">

  Esempio con eccezione: inserendo solo giorno e mese vengono calcolate le statistiche su quel giorno di quel mese
  <img width="904" alt="datehourstats_conerrore" src="https://user-images.githubusercontent.com/52038801/61415109-29e4f900-a8f0-11e9-9081-b45759f84299.png">





- tramite <strong>/date_filter</strong> vengono restituiti i dati compresi tra due specifiche date, gestendo eventuali eccezioni
<img width="904" alt="datefilter" src="https://user-images.githubusercontent.com/52038801/61415128-36695180-a8f0-11e9-96c1-ecd3cdd39e31.png">

  Esempio con eccezione: tralasciando solo il giorno minimo, vengono ritornate le misurazioni a partire dal primo giorno di quel mese
  <img width="904" alt="datefilter_conerrore" src="https://user-images.githubusercontent.com/52038801/61415155-44b76d80-a8f0-11e9-8c49-3e70399cfaf0.png">





- tramite <strong>/date_filter_stats</strong> vengono restituite le statistiche sui dati compresi tra due specifiche date, gestendo eventuali 
eccezioni
<img width="904" alt="datefilterstats" src="https://user-images.githubusercontent.com/52038801/61415192-56991080-a8f0-11e9-803f-d4f9e789bf40.png">

  Esempio con eccezione: tralasciando il giorno massimo, vengono restituite le statistiche sui dati compresi tra la data iniziale e tutto il mese finale
<img width="904" alt="datefilterstats_conerrore" src="https://user-images.githubusercontent.com/52038801/61415219-63b5ff80-a8f0-11e9-8b7a-8523619bda1b.png">





- tramite <strong>/values_filter</strong> vengono restituiti i dati con misurazioni comprese tra due valori limite di cpc e dmps
<img width="904" alt="valuesfilter" src="https://user-images.githubusercontent.com/52038801/61415248-72041b80-a8f0-11e9-9164-18f4d683a319.png">


  Esempio con eccezione: tralasciando il cpc massimo vengono restituite le misurazioni a partire da quelle con cpc minimo richiesto
  <img width="904" alt="valuesfilter_conerrore" src="https://user-images.githubusercontent.com/52038801/61415269-81836480-a8f0-11e9-8fe7-698c7dc353de.png">

##### NB: Le eccezioni presentate sono solo una parte di quelle implementate


## Diagrammi UML

### Diagramma Use Case
<img width="628" alt="usecase" src="https://user-images.githubusercontent.com/52038801/61415306-906a1700-a8f0-11e9-95b3-8e28745b09c9.png">


### Diagramma delle classi
<img width="888" alt="diagrammaclassifinale" src="https://user-images.githubusercontent.com/52038801/61491750-30d34080-a9b0-11e9-8e7b-efe1d182f0ab.png">


### Diagramma degli stati
![diagrammastati](https://user-images.githubusercontent.com/52038801/61415345-aaa3f500-a8f0-11e9-8990-bfe044851257.png)
