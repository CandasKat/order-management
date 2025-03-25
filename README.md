## Instructions pour exécution

1. **Cloner le dépôt :**  
   Clonez ce dépôt sur votre machine :
   ```bash
   git clone <URL_DU_DEPOT>

2. **Naviguer dans le répertoire du projet :**
    ```bash
    cd nom-du-projet

3. **Prérequis :** 
   Assurez-vous que les outils suivants sont installés sur votre machine :
   Java (version 17 ou supérieure recommandée)
   Gradle (le projet inclut le wrapper Gradle, ce qui permet d'utiliser la commande ./gradlew)

4. **Compiler et construire le projet :** Utilisez le wrapper Gradle pour nettoyer et construire le projet :
    ```bash
   ./gradlew clean build

(Sous Windows, utilisez gradlew.bat à la place de ./gradlew)

5. **Exécuter l'application :** Démarrez l'application avec la commande suivante :
    ```bash
   ./gradlew bootRun

L'application démarrera par défaut sur le port **8080**.

## URL d'accès à Swagger UI
Une fois l'application démarrée, vous pouvez accéder à l'interface Swagger UI via l'URL suivante :
[swagger ui](http://localhost:8080/swagger-ui/index.html)



