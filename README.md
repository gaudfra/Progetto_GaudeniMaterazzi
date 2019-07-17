# Progetto_GaudeniMaterazzi

Questo file serve ad agevolare la comprensione del progetto svolto dagli studenti Gaudeni Francesco e Materazzi Sandro

#### Come eseguire

Tramite command line ci si posizioni nella cartella /programmazioneoggetti e si digiti:

  mvn spring-boot:run
  
Si visiti poi il link http://localhost:8080/, attraverso cui vengono ritornati gli oggetti di tipo Misurazioni, dopo che in automatico è stato fatto il parsing del file JSON e il download del file .csv .

Attraverso rotte distinte si possono eseguire diverse operazioni sugli oggetti Misurazioni:

- tramite /meta vengono ritornati i metadati
![meta](/IdeaProjects/Progetto_GaudeniMaterazzi/meta.png)


- tramite /stats vengono restituite le statistiche su tutti i dati
![stats](/IdeaProjects/Progetto_GaudeniMaterazzi/stats.png)



- tramite /date_hour vengono restituiti tutti i dati con quella specifica data, gestendo eventuali eccezioni
![datehour_ok](/IdeaProjects/Progetto_GaudeniMaterazzi/datehour_ok.png)

Esempio con eccezione: 
![datehour_soloora](/IdeaProjects/Progetto_GaudeniMaterazzi/datehour_soloora.png)


- tramite /date_hour_stats vengono restituite le statistiche sui dati con quella specifica data, gestendo eventuali eccezioni
![datehourstats](/IdeaProjects/Progetto_GaudeniMaterazzi/datehourstats.png)

Esempio con eccezione:
![datehourstas_conerrore](/IdeaProjects/Progetto_GaudeniMaterazzi/datehourstats_conerrore.png)


- tramite /date_filter vengono restituiti i dati compresi tra due specifiche date, gestendo eventuali eccezioni
![datefilter](/IdeaProjects/Progetto_GaudeniMaterazzi/datefilter.png)

Esempio con eccezione:
![datefilter_conerrore](/IdeaProjects/Progetto_GaudeniMaterazzi/datefilter_conerrore.png)


- tramite /date_filter_stats vengono restituite le statistiche sui dati compresi tra due specifiche date, gestendo eventuali 
eccezioni
![datefilterstats](/IdeaProjects/Progetto_GaudeniMaterazzi/datefilterstats.png)

Esempio con eccezione:
![datefilterstats_conerrore](/IdeaProjects/Progetto_GaudeniMaterazzi/datefilterstats_conerrore.png)


- tramite /values_filter vengono restituiti i dati con misurazioni comprese tra due valori limite di cpc e dmps
 ![valuesfilter](/IdeaProjects/Progetto_GaudeniMaterazzi/valuesfilter.png) 

Esempio con eccezione:
![valuesfilter_conerrore](/IdeaProjects/Progetto_GaudeniMaterazzi/valuesfilter_conerrore.png)

##### NB: Le eccezioni presentate solo sono una parte di quelle implementate

## Diagrammi UML

### Diagramma Use Case
![](usecase.png)


### Diagramma delle classi
![classdiagram](/Progetto_GaudeniMaterazzi/classdiagram.png)

### Diagramma degli stati
![diagrammastati](/Progetto_GaudeniMaterazzi/diagrammastati.png)
