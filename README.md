# Projet de session - {bchainVersion1}

## Noms
Shabab Ishraq

## Brève description de l'application
Une application de technologie blockchain qui permet à un utilisateur de soumettre le type de vaccin qu'il a pris selon la compagnie approprié. La compagnie est une organisation qui offre aux voters de se faire vacciner avec seulement un type de vaccin. Celle-ci doit soumettre un formulaire dans l'application pour être valide. Le formulaire comprend le nom de la compagnie ainsi qu'un type de vaccin qu'elle offre. Le voter doit insérer une clé d'auhtentification qui lui a été fournie d'avance par le médecin afin qu'il puisse s'authentifier. Dans l'interface du voter, il peut soumettre son type de vaccin et aussi observer le nombre total de personnes vaccinées selon 4 catégories différentes, soit le nombre totals des personnes vaccinés dans le pays, le nonmbre des personnes vaccinés selon les 3 types de vaccins (Pfizer, Moderna et Johson) et un cumulatif de tous les vaccinés sur l'application. L'électoral est celui qui peut initier le blockchain. Il doit aussi insérer une clé d'authentification afin de s'authentifier. L'application offre une autre interface pour que n'importe qui puisse observer le blockchain de tous les vaccins. 

## Structure du projet

  Actors:       Comprend les classes des 3 acteurs, soit le voteur, le candidat ainsi que l'électoral.
  Adapter:      Comprend les classes Adapter pour le recyclerView.
  Model:        Comprend les classes qui servent de modèle, comme le Block, le VaccinType qui est un enum, le Person qui est une classe abstraite ainsi que le Vaccin pour les                   données de JSON
  Network:      Comprend les classes qui permettent de parser les données de l'API.
  Repository:   Comprend la classe qui est le repository pour le firebase.
  UI:           Comprend les activités et le fragments.
  Utils:        Comprend les fonctions, les constants nécessaires dont l'application utilise.
  ViewModel:    Comprend les différentes viewModel de chaque UI.

## Diagramme de classe

## CU implémentés

######CU #1 : Submit vote
Description:	    Le voter soumet le vaccin qu'il a pris.
Acteurs:	        Le voter
Préconditions:    Le voter doit avoir une clé d'authenficiation. 
Post-Conditions:	Le vote est soumis comme un string avec une fonction de hachage qui ne peut pas être retraçable. 
                  Le nombre de vaccin pour la compagnie en question est incrémenté de 1.
Scénario	
  1.	Le voter entre sa clé d'authentification pour s'authentifier.
  2.	Le voter soumet son vote pour la compagnie spécifique dont il a pris son vaccin.
  3.	Le vote est soumis dans le blockchain.
  4.	Le nombre de vaccins pris par la compagnie est incrémenté à 1. 
  5.	Le voter peut observer le nombre total de vaccins dans le pays en comparaison avec le nombre totals de vaccins pris avec l'application. 
 
Scénario Alternatifs	
1. La clé d'authentification n'est pas validée.

######CU #2 : Submit candidacy
Description:      La compagnie postule sa candidature
Acteurs:	        Le candidat
Préconditions:    Le candidat a déjà été approuvé par le gouvernment.
Post-Conditions:  Le candidat peut recevoir les votes. 

Scénario	
1.	La compagnie remplit un formulaire d'application.
2.	Les informations sont enregistrées dans l’application.
3.	Un affichage existe pour voter pour le candidat.

Scénario Alternatifs	
1.	Le candidat n’a pas rempli adéquatement le formulaire.

######CU #3 : Starts the blockchain
Description:      L'électoral démarre le blockchain.
Acteurs:	        L'électoral.
Préconditions:    L'électoral est authentifié.
Post-Conditions:  Chaque vote est enregistré dans le blockchain.

Scénario	
1.	L'électoral entre sa clé d'authenfication.
2.	L'électoral démarre le blockchain.

Scénario Alternatifs	
1.	Le blockhain est déjà initialisé.

Spécifier également des fonctionnalitéss manquantes, s'il y a lieu. 

## Problèmes/bogues connus
