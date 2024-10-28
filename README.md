# CollabCampus

CollabCampus est une application d’interface interactive, développée pour faciliter la comparaison et la fusion de modifications textuelles dans un contexte d’écriture collaborative. Ce projet est pensé pour les étudiants de l’Université Paris-Saclay, avec une inspiration tirée de la plateforme Ecampus, d'où son nom.

### Objectif
L’objectif principal de CollabCampus est de permettre aux utilisateurs de comparer deux versions d’un même texte pour faciliter la fusion de modifications. L’application répond à des besoins tels que :

Garder une trace des modifications (ajouts, suppressions, remplacements)
Évaluer ou valider des propositions de modifications par un autre utilisateur
Converger vers une version finale commune.
Fonctionnalités
CollabCampus propose les fonctionnalités suivantes :

Chargement de fichiers TXT : Importer un fichier de texte brut (jusqu’à 10 000 caractères) comme référence en lecture seule.
Comparaison de textes : Affichage côte à côte des textes de référence et modifié, avec identification visuelle des modifications (ajouts, suppressions, remplacements).
Gestion des modifications : Accepter ou refuser des modifications, commenter chaque modification, et éditer librement le texte modifié pour proposer des contre-propositions distinctes.
Sauvegarde : Exporter le résultat des modifications sous format TXT, y compris les décisions d’acceptation/refus et les contre-propositions.

### Architecture et Technologies
**Langage** : Java avec JavaFX
**Architecture** : Modèle-Vue-Contrôleur (MVC)
**Bibliothèques** : java-diff-utils pour la détection des différences textuelles
**IDE** : IntelliJ Ultimate / Eclipse

### Conception de l’Interface
L’interface a été conçue en tenant compte des concepts étudiés en Interfaces Interactives Avancées, avec :

Création de personas pour cibler les utilisateurs
Scénarios et storyboards pour représenter l’utilisation de l’application
Utilisation de heuristiques pour l’esthétique, la visibilité et la cohérence

### Problèmes rencontrés
Lors du développement, plusieurs défis ont été relevés :

Transition de SceneBuilder à Java pour certaines zones de texte
Implémentation des fonctionnalités d’acceptation/refus des modifications
Gestion des fichiers et des erreurs liées aux entrées utilisateur.
Améliorations Futures
Bien que notre application atteigne ses objectifs de base, nous envisageons d’ajouter :

Une gestion plus avancée des erreurs
Une amélioration de l’esthétique de l’interface pour une meilleure ergonomie
Une intégration plus poussée pour répondre aux besoins futurs des utilisateurs.

### Auteurs
Chloé Makoundou
Chéïma Hamrouni
*GROUP 3*

### Licence
Ce projet est réalisé dans le cadre d’un projet de Licence à l’Université Paris-Saclay, sous la supervision de Yann Trividic et Ouriel Grynszpan.
