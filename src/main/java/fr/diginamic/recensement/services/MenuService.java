package fr.diginamic.recensement.services;

import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.exceptions.CodeDeptException;
import fr.diginamic.recensement.exceptions.NullChoiceException;
import fr.diginamic.recensement.exceptions.NumberException;
import fr.diginamic.recensement.exceptions.NumberNotGoodException;

/**
 * Classe représentant un service
 * 
 * @author DIGINAMIC
 *
 */
public abstract class MenuService {

	/**
	 * Méthode abstraite de traitement que doivent posséder toutes les méthodes de
	 * services
	 * 
	 * @param lignes  lignes du fichier
	 * @param scanner scanner
	 * @throws NumberException
	 * @throws NumberNotGoodException
	 */
	public abstract void traiter(Recensement recensement, Scanner scanner)
			throws NumberException, NumberNotGoodException, NullChoiceException, CodeDeptException;
}
