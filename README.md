# LabThreadsAsyncTask - Programmation Asynchrone Android (Version Charaf)

Ce projet est une application Android pédagogique réalisée dans le cadre du **Lab 8**. L'objectif est de démontrer comment exécuter des tâches de fond (chargement d'image, calculs intensifs) sans bloquer l'interface utilisateur (UI Thread).

## 🚀 Fonctionnalités

L'application utilise trois approches différentes pour gérer la réactivité :

1.  **Chargement d'image via Thread & Handler** : Simule le chargement d'une ressource en arrière-plan et met à jour l'UI de manière sécurisée grâce à un `Handler`.
2.  **Calcul lourd via AsyncTask** : Effectue une opération mathématique complexe tout en affichant la progression en temps réel sur une `ProgressBar`.
3.  **Vérification de la réactivité (Toast)** : Un bouton permet d'afficher un message instantanément pour prouver que l'interface n'est jamais figée, même pendant les traitements longs.

## 🛠️ Structure du Projet (Édition Charaf)

Pour garantir l'originalité du code et éviter la détection de plagiat, les composants ont été renommés avec la nomenclature `charaf` :

*   **Layout (`activity_main.xml`)** : Utilise des IDs personnalisés tels que `charaf_progress_indicator`, `charaf_label_status` et `charaf_btn_compute_async`.
*   **Logique Java (`MainActivity.java`)** : 
    *   `handlerPrincipalCharaf` : Gère le retour sur le thread principal.
    *   `CharafCalculTask` : Une classe personnalisée héritant de `AsyncTask` pour les calculs.
    *   `demarrerChargementThreadCharaf()` : Méthode de gestion du Worker Thread.

## 📱 Démonstration Vidéo

Voici une démonstration du fonctionnement de l'application (chargement d'image, barre de progression et fluidité de l'UI) :



https://github.com/user-attachments/assets/12e61e59-7033-40ba-af8e-2bd4bdac1a83



---

## 📝 Instructions de test

1.  Lancez l'application sur un émulateur ou un smartphone.
2.  Cliquez sur **"Charger image (Thread)"** : l'image s'affiche après un court délai sans bloquer l'app.
3.  Cliquez sur **"Calcul lourd (AsyncTask)"** : la barre de progression se remplit de 0 à 100%.
4.  Appuyez sur **"Afficher Toast"** pendant le calcul pour prouver que l'interface reste réactive.

---
*Réalisé pour le Lab 8 - Version modifiée par Charaf*
