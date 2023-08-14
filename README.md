# hygie
Hygie, logiciel de vérification de près requis systhème et communication.
L'utilitaire se présente sous la forme d’une archive .zip pour la version windows et .tar.gz pour la version linux. Les livrables, toutes ses versions ainsi que ces notes de versions se trouvent dans le github du projet: https://github.com/singard/hygie/tree/master. À droite de la page principale, se trouve la partie release, c’est ici que se trouvent les livrables à télécharger. 

# ouvrir le projet

```
git clone https://github.com/singard/hygie.git
```

# création d'une nouvelle version

A faire à la racine du projet
```
mvn versions:set -DnewVersion=2.50.1-SNAPSHOT (pour changer de version)
```
# création du livrable

A faire à la racine du projet
```
mvn clean install -am -Pwindows
```
# utilisation

Une fois l’archive téléchargée, décompressez l’archive sur le disque virtuel ou physique qui doit être testé. Car l’utilitaire vérifiera l’espace disponible du disque sur lequel il se situe. Maintenant, si ça n’est pas déjà effectué, il faudra rédiger les fiches de test sous format yaml avec l’exemple fourni avec l’utilitaire. 

Pour la mise à jour du logiciel. L’utilitaire ne se mettra pas à jour tout seul, il faudra télécharger manuellement la bonne version ou la dernière version publiée pour avoir les dernières nouveautés. La seule manipulation à avoir est de transférer les fichiers yaml de tests de l’ancienne version à la nouvelle version. En sachant que les fichiers créés dans une version antérieure seront toujours compatibles avec une version supérieure. Cependant, la rétrocompatibilité ne sera pas assurée au fait qu’il y aura des nouvelles fonctionnalités de tests qui ne seront pas présentes sur les précédentes versions.

## Configuration minimum

- os : linux en ligne de commande ou graphique, windows 7 à 10; windows serveur 2003 à 2022
- espace disque disponible :  320Mo
- logiciel : aucun logiciel n’est nécessaire, java est déjà intégrée au projet. De plus les fonctions de décompression d’archive sont apparues sur windows au moins à partir de Windows Me 

## Présentation de l'arborescence.

![image](https://github.com/singard/hygie/assets/77006808/3166c301-97c1-4535-917b-632fac75f98e)


Le noyau du logiciel est constitué de quatre dossiers : le logiciel au format JAR, ainsi qu'un exécutable au format BAT pour Windows et au format SH pour Linux.
Le dossier "certificate" contient un certificat auto-signé destiné à la création de serveurs HTTPS. À terme, il sera possible d'utiliser un certificat personnalisé pour des tests plus approfondis lors de la mise en place de connexions HTTPS.
Le dossier "data" est destiné à recevoir les fichiers de test au format YAML. L'utilitaire vérifie la présence de ces fichiers de test en se basant uniquement sur leur nom.
Le dossier "java" renferme une version Java 16. L'exécutable utilise cette version spécifique de Java, même si une autre version est installée sur la machine.
Le dossier "report" contient tous les rapports générés à la fin de chaque série de tests. Ces rapports offrent une vue claire des résultats des tests réussis et des échecs, sans nécessiter de recherche dans les journaux.
En ce qui concerne le fichier JAR, il constitue le cœur vital du logiciel. De plus, nous avons un fichier BAT pour Windows et un fichier SH pour Linux. Ces fichiers d'exécution permettent de lancer le logiciel en pointant vers la version intégrée de Java et en sollicitant cette dernière pour exécuter l'utilitaire.
Enfin, le fichier "spring-shell.log" enregistre l'historique de toutes les commandes lancées dans l'utilitaire.
Concernant la structure du nom d'archive, elle suit un modèle précis et automatisé. Ce format de nom permet d'identifier instantanément la version en cours. Par exemple, le format est "hygie-installer-<version_de_lutilitaire>-<os>.zip", ce qui donnerait comme exemple concret : "hygie-installer-1.1-SNAPSHOT-windows.zip".

## Utilisation des fiches de test yaml. 

Les fiches de test qu’utilise le logiciel sont à créer à la main en suivant l’exemple fourni. Le fichier peut ressembler à l’exemple ci-dessous.
```
[
  {
    "testType": "CREATE_TCP_SERVER",
    "description": "tcp server in port 1235",
    "args": ["1235"]
  },
  {
    "testType": "CREATE_TCP_SERVER",
    "description": "tcp server in port 1236 - echec",
    "args": ["1236"]
  },
  {
    "testType": "CREATE_HTTPS_SERVER",
    "description": "ckeck if port https 2020 is free",
    "args": ["2020"]
  }
]
```

Chaque test est représenté par le même modèle. 
```
{
    "testType": "LE_TEST_A_REALISER",
    "description": "ce que va tester le test",
    "args": ["1235"]
  }
```

- “testType” : correspond au code du test.
- “description” : texte libre correspondant au test effectué et sera affiché dans le document final.
- “args” : leur nombre est différent en fonction du type de test, permet de paramétrer le test correctement.



# test

## test sur le serveur tcp 

une fois le serveur tcp lancer veuiller vérifier (sur windows) si il se connecter bien

dans un cmd :

telnet <host_du_serveur> <port_du_serveur_tcp>

cette commende, si elle marche, devra faire afficher "new connection" sur la console du serveur hôte

