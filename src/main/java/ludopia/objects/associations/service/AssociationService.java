package ludopia.objects.associations.service;

import ludopia.objects.associations.Association;
import java.util.List;

public interface AssociationService {
    /**
     * Crée une association
     * @param association un objet association
     * @return l'association crée en base de données
     */
    Association createAssociation(Association association);

    /**
     * Récupère une association par son ID
     * @param id l'id de l'association
     * @return un objet Optional contenant potentiellement une association
     */
    Association getAssoById(int id);

    /**
     * Récupère toutes les associations en base de données
     * @return un Iterable d'association
     */
    Iterable<Association> getAll();

    /**
     * Récupère toutes les associations contenant un jeu
     * @param gameId l'ID d'un jeu
     * @return la liste des associations ayant ce jeu
     */
    List<Association> findAssoHavingTheGame(int gameId);

    /**
     * Récupère toutes les associations dont un utilisateur est l'administrateur
     * @param userId l'id de l'utilisateur
     * @return la liste des associations d'un utilisateur
     */
    List<Association> findAssoFromUser(int userId);
}
