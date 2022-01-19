# Laboratoire 8 Echecs - Rapport

Auteurs : Luca Coduri & Chloé Fontaine

Date : 18.01.2022

## Introduction

Dans ce laboratoire, nous avons implémenté un jeu d'échec respectant les règles suivantes :

- Les déplacements  corrects et prises des pièces.
- Les petit et grand roque
- La prise en passant
- La promotion
- L'échec

Nous avons également ajouté un contrôle sur :

- L'échec et mat
- L'égalité (pat)

Ces deux fonctionnalité ne sont malheuresement pas totalement fonctionnelles.

## Diagramme de classes



## Description des classes

Dans ce chapitre, nous allons nous contenter de donner uniquement des informations sur des classes qui nous semblent pertinentes. Vous pouvez cependant retrouver une documentation plus précise de chaque méthode dans notre code.

### Controller

La classe Controller implémente l'interface ChessController et a pour responsabilité tout affichage sur les vues. A la fin de chaque mouvement, toutes le pièces sont effacées de l'interface puis réaffichées en tenant compte de leur nouvelle position. Il aurait été possible de n'effacer et réafficher uniquement les pièces ayant bougé durant le tour. Cependant, plusieurs pièces peuvent être impliquées en fonction de la règle appliquée et nous avons donc jugé la première solution plus claire et plus sûre.

Cette classe s'occupe également de la gestion globale du jeu. Cela implique notamment les tours de jeu et l'appel aux différentes vérifications permettant de vérifier l'applicabilité du déplacement. Lors de l'appel à la méthode "move", les différentes vérification vont être effectuées dans cet ordre :

1. Echec et mat ou égalité
2. La pièce sélectionnée est de la bonne couleur.
3. Vérification général du mouvement (mouvement légal pour la pièce en question).
4. Echec (si c'est le cas, on annule le déplacement) 

### Gameboard

La classe Gameboard représente le plateau de jeu. Cette classe propose toutes les méthodes concernant l'état du plateau de jeu, telles que l'initialisation des pièces et les vérification d'échec, échec et mat ou égalité (pat).

### Vector

Nous avons choisi de représenter toutes les positions des pièces ainsi que les déplacements par des vecteurs. La classe Vector implémente donc certaines méthodes propres aux vecteurs, telles que l'addition, la soustraction, la colinéarité, la norme, etc.

### Piece

Nous avons créé une classe par catégorie de pièce. Chaque classe hérite de la classe Piece. Cette dernière possède comme attribut un tableau de règles que ses sous-classes remplissent dans leur constructeur avec les règles et mouvements applicables à leur propre type de pièce. Lorsque l'utilisateur veut effectuer le déplacement d'une pièce, la fonction `checkMove` vérifie qu'une de ses règles permet le déplacement de la pièce jusqu'à la position désirée.

### Rule

La classe abstraite Rule oblige toutes ses sous-classe à implémenter la méthode `check`. Cette méthode a pour objectif de tester si la règle en question est applicable pour le déplacement souhaité.

Chaque règle est ensuite représentée par une classe à part entière.

### MoveLinear

La classe MoveLinear implémente tout déplacement linéaire (qu'il soit horizontale, verticale ou diagonale), en prenant compte des pièces se trouvant sur le chemin; la pièce ne saute pas, contrairement au mouvement MoveL utilisée par le cavalier.

Le constructeur de MoveLinear prend en argument une vecteur représentant la "direction" du mouvement. Par exemple, le vecteur(1, 0) représente un mouvement horizontal, alors que le vecteur(1,1) représente un mouvement diagonal.

La vérification de ce mouvement consiste ainsi simplement à vérifier que le vecteur correspondant au chemin à effectuer par la pièce pour aller à la position souhaitée soit colinéaire au vecteur directionnel de la classe. La vérification ne prend compte du sens du vecteur que si l'utilisateur l'a précisé en ajoutant une option supplémentaire lors de la création du mouvement.

## Tests

### Règles générales
| Test | Résultat |
|:-|:-:|
| En appuyant sur "New game", une nouvelle partie commence et tout message est effacé. | ok |
| Le joueur blanc commence la partie. | ok |
| Les couleurs des joueurs alternent. | ok |
| Si le mouvement d'un joueur est refusé, ce même joueur doit à nouveau jouer. | ok |
| Un pièce ne peut pas se déplacer sur une case si une autre pièce de la même couleur s'y trouve déjà. | ok |
| Si une pièce se déplace sur une case où se trouve une pièce de la couleur opposée, elle la tue et prend sa place. | ok |
| Il n'est pas possible de mettre son roi en danger. | ok |
| Un message "Vous mettez votre roi en danger" est affiché si vous exposez votre roi. | ok |
| Un message "Echec et mat" est affiché si le roi est en danger sur sa case ainsi que toutes ses cases adjacentes. | ok |
| Le message "Echec et mat" n'est pas affiché le roi ou une de ses pièces alliées peut éliminer la menace. | pas ok |

### Pion
| Test | Résultat |
|:-|:-:|
| Se déplace uniquement en avant (en haut pour les blancs et en bas pour les noirs) si aucune pièce ne se trouve sur sa destination. | ok |
| Ne peut se déplacer que d'une case maximale (exception ci-dessous). | ok |
| Peut se déplacer de deux case dans le cas ou elle ne s'était pas encore séplacer (double forwards) | ok |
| Ne peut pas tuer une autre pièce avec un mouvement vertical (classique ou double forwards). | ok |
| Tue uniquement des pièces avec un mouvement diagonale d'une case. | ok |
| Une fois le pion arrivé au bout du plateau, une fenêtre est présentée à l'utilisateur pour qu'il choisisse une pièce pour remplacer son pion promu parmi les pièces : tour,, reine, fou et cavalier. | ok|
| Le joueur ne peut pas décider de ne pas appliquer la promotion à un pion. | ok |
| Un pion peut accéder à la promotion par un movement vertical ou diagonal. | ok |
| La prise en passant ne peut s'appliquer que sur des pions ayant effectué un double forwards au tour précédent. | ok |
| Un pion ne pas utiliser la prise en passant pour tuer une autre pièce qu'un pion. | ok |
| Un pion ne pas utiliser la prise en passant pour tuer un pion qui n'a pas joué au tour précédent. | ok |

### Tour
| Test | Résultat |
|:-|:-:|
| Se déplace par mouvements horizontaux et verticaux. | ok |
| N'a pas de limite de déplacement maximal. | ok |
| Ne peut pas pas faire un déplacement si une pièce se trouve entre elle et sa destination. | ok |

### Fou
| Test | Résultat |
|:-|:-:|
| Se déplace par mouvements diagonaux. | ok |
| N'a pas de limite de déplacement maximal. | ok |
| Ne peut pas pas faire un déplacement si une pièce se trouve entre elle et sa destination. | ok |

### Cavalier
| Test | Résultat |
|:-|:-:|
| Se déplace en L. | ok |
| Ne tient pas compte des pièces entre sa position et sa destination. | ok |

## Reine
| Test | Résultat |
|:-|:-:|
| Se déplace par mouvements horizontaux et verticaux. | ok |
| Se déplace par mouvements diagonaux. | ok |
