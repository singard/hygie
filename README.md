# hygie
Hygie, logiciel de vérification de près requis systhème et communication

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


# test

## test sur le serveur tcp 

une fois le serveur tcp lancer veuiller vérifier (sur windows) si il se connecter bien

dans un cmd :

telnet <host_du_serveur> <port_du_serveur_tcp>

cette commende, si elle marche, devra faire afficher "new connection" sur la console du serveur hôte

